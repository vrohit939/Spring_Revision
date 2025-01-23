package com.example.annotation.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.example.annotation") // Replaces <context:component-scan>
@EnableTransactionManagement // Replaces <tx:annotation-driven>
public class AppConfig {

    // Define EntityManagerFactory (Replaces <bean id="entityManagerFactory">)
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitName("myPersistenceUnit");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        Properties properties = new Properties();
        properties.setProperty("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/employee_db");
        properties.setProperty("jakarta.persistence.jdbc.user", "root");
        properties.setProperty("jakarta.persistence.jdbc.password", "root1");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        emf.setJpaProperties(properties);

        return emf;
    }

    // Define Transaction Manager (Replaces <bean id="transactionManager">)
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
