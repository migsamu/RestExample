package org.iesfm.restexample.controller;

import org.iesfm.restexample.Employee;
import org.iesfm.restexample.dao.EmployeeDAO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeDAO employeeDAO;

    public EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/employees")
    public List<Employee> list() {
        return employeeDAO.list();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/employees/{nif}")
    public Employee getEmployee(@PathVariable("nif") String nif) {

        try {
            Employee employee = employeeDAO.getEmployeeByNif(nif);
            return employee;
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/employees")
    public void insert(@RequestBody Employee employee) {
        employeeDAO.insert(employee);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/employees/{nif}")
    public void delete(@PathVariable("nif") String nif) {
        getEmployee(nif);
        employeeDAO.delete(nif);
    }

}
