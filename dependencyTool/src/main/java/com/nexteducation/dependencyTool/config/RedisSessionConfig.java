package com.nexteducation.dependencyTool.config;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author yashbanwani
 * @author ashishpratap
 *
 */
//@EnableRedisHttpSession
@Configuration
public class RedisSessionConfig {
	// @Bean
	// public JedisConnectionFactory connectionFactory() {
	// return new JedisConnectionFactory();
	// }

	@Value("${spring.redis.host}")
	String host="192.168.10.9";

	@Value("${spring.redis.port}")
	String port="6379";

	//@Value("${spring.redis.password}")
	String password=null;

	@Bean
	public RedissonClient redisson() {
		Config config = new Config();
		SingleServerConfig serverConfig = config.useSingleServer().setAddress(host + ":" + port);
		if (password != null) {
			serverConfig.setPassword(password);
		}
		return Redisson.create(config);

	}

}