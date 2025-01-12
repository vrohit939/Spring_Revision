package com.example.config;

import com.example.aspect.LoggingAspect;
import com.example.dao.EmployeeDAOImpl;
import com.example.dao.UserDAOImpl;
import com.example.service.EmployeeServiceImpl;
import com.example.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.example") // Automatically scans for components, services, etc.
@EnableAspectJAutoProxy // Enables AOP support
@PropertySource("classpath:db.properties") // Loads database properties
public class AppConfig {

    // Database Properties
    @Value("${db.driverClassName}")
    private String driverClassName;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    /*
     *
     *   Singleton (default): One instance for the whole application context.
     *   Prototype: A new instance every time the bean is requested.
     *   Request: One instance per HTTP request.
     *   Session: One instance per HTTP session.
     *   Global Session: One instance per global HTTP session (only for portlet environments).
     *   Custom Scope: If you need a completely custom scope.
     *
     * */

    // DataSource Bean
    @Bean
    @Scope("Singleton")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    // JdbcTemplate Bean
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // EmployeeDAO Bean
    @Bean
    @Scope("prototype")
    public EmployeeDAOImpl employeeDAO(JdbcTemplate jdbcTemplate) {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        employeeDAO.setJdbcTemplate(jdbcTemplate);
        return employeeDAO;
    }

    // EmployeeService Bean
    @Bean
    public EmployeeServiceImpl employeeService(EmployeeDAOImpl employeeDAO) {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        employeeService.setEmployeeDAO(employeeDAO);
        return employeeService;
    }

    // UserDAO Bean
    @Bean
    public UserDAOImpl userDAO(JdbcTemplate jdbcTemplate) {
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setJdbcTemplate(jdbcTemplate);
        return userDAO;
    }

    // UserService Bean
    @Bean
    public UserServiceImpl userService(UserDAOImpl userDAO) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDAO(userDAO);
        return userService;
    }

    // LoggingAspect Bean
    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
