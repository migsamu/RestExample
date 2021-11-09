package org.iesfm.restexample.controller;

import org.iesfm.restexample.Department;
import org.iesfm.restexample.dao.DepartmentDAO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class DepartmentController {

    private DepartmentDAO departmentDAO;

    public DepartmentController(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/departments")
    public List<Department> list() {
        return departmentDAO.list();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/departments")
    public void createDepartment(@RequestBody Department department) {
        departmentDAO.insert(department);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/departments/{departmentName}")
    public Department getDepartment(@PathVariable("departmentName") String departmentName) {

        try {
            Department department = departmentDAO.get(departmentName);
            return department;
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "department not found");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "departments/{departmentName}")
    public void deleteDepartment(@PathVariable("departmentName") String departmentName) {
        getDepartment(departmentName);
        departmentDAO.deleteDepartment(departmentName);

    }
}
