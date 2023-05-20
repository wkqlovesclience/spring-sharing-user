package com.sc.api.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.provider.AbstractDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

/**
 * <p>
 * 服务配置项
 * </p>
 *
 * @author wkqsclience118706@163.com
 * @date 2021/3/14 9:26 PM
 * @since 1.0
 */
@Configuration
@ComponentScan("com.sc.api")
@MapperScan("com.sclience.sharding.dao.mapper")
@AutoConfigureBefore({DynamicDataSourceAutoConfiguration.class, SpringBootConfiguration.class})
public class ShardingConfig {

    /**
     * 分表数据源名称
     */
    private static final String SHARDING_DATASOURCE_NAME = "sharding";

    /**
     * 动态数据源配置项
     */
    @Autowired
    private DynamicDataSourceProperties properties;

    /**
     * shardingjdbc有四种数据源，需要根据业务注入不同的数据源
     * 1. 未使用分片, 脱敏的名称(默认): shardingDataSource;
     * 2. 主从数据源: masterSlaveDataSource;
     * 3. 脱敏数据源：encryptDataSource;
     * 4. 影子数据源：shadowDataSource
     */
    @Lazy
    @Resource(name = "shardingDataSource")
    DataSource shardingDataSource;

    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        Map<String, DataSourceProperty> datasourceMap = properties.getDatasource();
        return new AbstractDataSourceProvider() {
            @Override
            public Map<String, DataSource> loadDataSources() {
                Map<String, DataSource> map = createDataSourceMap(datasourceMap);
                /// 这里将shardingjdbc管理的数据源交给dynamic-datasource动态数据源去管理
                map.put(SHARDING_DATASOURCE_NAME, shardingDataSource);
                return map;
            }
        };
    }

    /**
     * 将dynamic-datasource动态数据源设置为首选的
     * 当spring存在多个数据源的时候，自动注入的是首选数据源
     * 这样之后可以支持sharding-jdbc原生的配置方式
     * @param dynamicDataSourceProvider
     * @return
     */
    @Primary
    @Bean
    public DataSource dataSource(DynamicDataSourceProvider dynamicDataSourceProvider) {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setPrimary(properties.getPrimary());
        dataSource.setStrict(properties.getStrict());
        dataSource.setStrategy(properties.getStrategy());
        dataSource.setProvider(dynamicDataSourceProvider);
        dataSource.setP6spy(properties.getP6spy());
        dataSource.setSeata(properties.getSeata());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager(final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
