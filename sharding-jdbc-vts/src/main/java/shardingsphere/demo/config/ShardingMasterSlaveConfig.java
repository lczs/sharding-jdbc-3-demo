package shardingsphere.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import io.shardingsphere.core.api.config.MasterSlaveRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "sharding.jdbc")
public class ShardingMasterSlaveConfig {

    private Map<String, HikariDataSource> dataSources = new HashMap<String, HikariDataSource>();

    private MasterSlaveRuleConfiguration masterSlaveRule;

}
