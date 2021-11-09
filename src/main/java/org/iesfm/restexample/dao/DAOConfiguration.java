package org.iesfm.restexample.dao;


import org.iesfm.restexample.dao.jdbc.JdbcDepartmentDAO;
import org.iesfm.restexample.dao.jdbc.JdbcEmployeeDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("application.properties")
public class DAOConfiguration {

    @Bean
    public NamedParameterJdbcTemplate jdbc(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource(

            @Value("${database.url}") String url,
            @Value("${database.user}") String user,
            @Value("${database.password}") String password) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }


    @Bean
    public DepartmentDAO departmentDAO(NamedParameterJdbcTemplate jdbc) {
        return new JdbcDepartmentDAO(jdbc);
    }

    @Bean
    public JdbcDepartmentDAO jdbcDepartmentDAO(NamedParameterJdbcTemplate jdbc) {
        return new JdbcDepartmentDAO(jdbc);
    }

    @Bean
    public EmployeeDAO employeeDAO(NamedParameterJdbcTemplate jdbc) {
        return new JdbcEmployeeDAO(jdbc);
    }

}
