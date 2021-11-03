package org.iesfm.restexample.dao;

import org.iesfm.restexample.inmemory.InMemoryDepartmentDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DAOConfiguration {

    @Bean
    public DepartmentDAO departmentDAO() {
        return new InMemoryDepartmentDAO();
    }
}
