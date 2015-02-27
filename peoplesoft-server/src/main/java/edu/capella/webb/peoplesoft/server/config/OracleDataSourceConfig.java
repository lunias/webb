package edu.capella.webb.peoplesoft.server.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import edu.capella.webb.peoplesoft.server.util.Constant;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "edu.capella.webb.peoplesoft.server.domain")
@EnableJpaRepositories(basePackages = "edu.capella.webb.peoplesoft.server.repository")
@Profile({Constant.SPRING_PROFILE_STAGE, Constant.SPRING_PROFILE_QA, Constant.SPRING_PROFILE_PRODUCTION})
public class OracleDataSourceConfig implements EnvironmentAware {

	private final Logger log = LoggerFactory.getLogger(OracleDataSourceConfig.class);
	
    private RelaxedPropertyResolver propertyResolver;
	
	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
	}
	
	@Bean
	public Hibernate4Module hibernate4Module() {
		
		return new Hibernate4Module();
	}	
	
	@Bean
	public DataSource dataSource() {

		log.debug("Configuring PeopleSoft Datasource");
		return configureDataSource();
	}			
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        
        lef.setDataSource(dataSource());
        lef.setJpaVendorAdapter(jpaVendorAdapter());
        lef.setJpaProperties(customJpaProperties());
        lef.setPackagesToScan("edu.capella.webb.peoplesoft.server.domain");
        lef.afterPropertiesSet();
        
        return lef;
	}	
	
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    	    	
        return new JpaTransactionManager(emf);
    }	
	
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
    	
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setDatabase(Database.ORACLE);        
        
        return jpaVendorAdapter;
    }
    
    private Properties customJpaProperties() {
    	
    	Properties props = new Properties();
    	
    	props.setProperty("hibernate.hbm2ddl.auto", "none");
    	props.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        props.setProperty("hibernate.cache.use_second_level_cache", "true");
        props.setProperty("hibernate.cache.use_query_cache", "false");
        props.setProperty("hibernate.generate_statistics", "false");
//        props.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
    	
    	return props;
    }         
	
	private DataSource configureDataSource() {
		
		HikariConfig config = new HikariConfig();
		
		config.setDataSourceClassName(propertyResolver.getProperty("dataSourceClassName"));

		if (propertyResolver.getProperty("url") == null || "".equals(propertyResolver.getProperty("url"))) {
			config.addDataSourceProperty("databaseName", propertyResolver.getProperty("databaseName"));
			config.addDataSourceProperty("serverName", propertyResolver.getProperty("serverName"));
		} else {
			config.addDataSourceProperty("url", propertyResolver.getProperty("url"));
		}
		config.addDataSourceProperty("user", propertyResolver.getProperty("username"));
		config.addDataSourceProperty("password", propertyResolver.getProperty("password"));		

		return new HikariDataSource(config);		
	}	

}
