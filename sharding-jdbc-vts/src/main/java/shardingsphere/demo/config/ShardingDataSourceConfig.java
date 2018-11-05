package shardingsphere.demo.config;


import io.shardingsphere.core.api.MasterSlaveDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableConfigurationProperties(ShardingMasterSlaveConfig.class)
@ConditionalOnProperty({"sharding.jdbc.data-sources.ds_master.url", "sharding.jdbc.master-slave-rule.master-data-source-name"})
public class ShardingDataSourceConfig {


        @Autowired(required = false)
        private ShardingMasterSlaveConfig shardingMasterSlaveConfig;

        @Bean("dataSource")
        public DataSource masterSlaveDataSource() throws SQLException {
            shardingMasterSlaveConfig.getDataSources().forEach((k, v) -> configDataSource(v));
            Map<String, DataSource> dataSourceMap = new HashMap<>(3);
            dataSourceMap.putAll(shardingMasterSlaveConfig.getDataSources());


            DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, shardingMasterSlaveConfig.getMasterSlaveRule(),  new HashMap<String, Object>(), new Properties());
            System.out.println("masterSlaveDataSource config complete");
            return dataSource;
        }

        private void configDataSource(HikariDataSource hikariDataSource) {
            hikariDataSource.setReadOnly(false);
            hikariDataSource.setConnectionTimeout(30000);
            hikariDataSource.setIdleTimeout(600000);
            hikariDataSource.setMaxLifetime(1800000);
            hikariDataSource.setMaximumPoolSize(15);
        }


}
