package es.code.urjc.BiblioBookingApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

@SpringBootApplication
@EnableCaching
@EnableHazelcastHttpSession
public class BiblioBookingApplication {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String dll;

	@Value("${mail.host}")
	private String host;

	@Value("${mail.port}")
	private int port;

	public static void main(String[] args) {
		SpringApplication.run(BiblioBookingApplication.class, args);
	}

	@Bean
	public void logMessage(){
		final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
		LOGGER.info("spring.jpa.hibernate.ddl-auto="+dll);
		LOGGER.info("mail.host="+host);
		LOGGER.info("mail.port="+port);
	}

	@Bean
	 public Config config() {
		
		Config config = new Config();
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		joinConfig.getMulticastConfig().setEnabled(true);
		
		//esto es solo para pruebas en localhost
		//tambien poner multicast config a false
		//joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.singletonList("127.0.0.1"));
		
	 return config;
	 }

	@Bean
	public CacheManager cacheManager() {
		final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
		LOGGER.info("Activating cache...");
		return new ConcurrentMapCacheManager("users","reservas","salas");
	}
}
