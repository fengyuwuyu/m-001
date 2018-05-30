package com.ll.medical.data.jpa;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class JpaConfig{
	
	@Value("${spring.datasource.username}")
	private String user;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.driver-class-name}")
	private String driver;
	@Value("${spring.jpa.hibernate.naming.physical-strategy}")
	private String strategy;
	@Value("${spring.jpa.database-platform}")
	private String dialect;
	@Value("${spring.jpa.show-sql}")
	private String showSql;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	
   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
       LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
       em.setDataSource(dataSource());
       em.setPackagesToScan(new String[] { "com.ll.medical" });
 
       JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
       em.setJpaVendorAdapter(vendorAdapter);
       em.setJpaProperties(additionalProperties());
       
       return em;
   }
 
   @Bean
   public DataSource dataSource() throws PropertyVetoException{
	   ComboPooledDataSource dataSource = new ComboPooledDataSource();//new DriverManagerDataSource();
	   
	   dataSource.setDriverClass(driver);
	   dataSource.setJdbcUrl(url);
	   dataSource.setUser(user);
	   dataSource.setPassword(password);
	   dataSource.setMinPoolSize(16);
	   dataSource.setMaxPoolSize(32);
	   dataSource.setIdleConnectionTestPeriod(3000);
	   dataSource.setMaxIdleTime(3000);
	   dataSource.setTestConnectionOnCheckin(true);
	   return dataSource;
   }
 
   @Bean
   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
	   JpaTransactionManager transactionManager = new JpaTransactionManager();
	   transactionManager.setEntityManagerFactory(emf);
 
	   return transactionManager;
   }
 
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }
 
   Properties additionalProperties() {
      Properties properties = new Properties();
      properties.setProperty("hibernate.physical_naming_strategy", strategy);
      properties.setProperty("hibernate.dialect", dialect);
      properties.setProperty("hibernate.show_sql", showSql);
      properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
      return properties;
   }
}