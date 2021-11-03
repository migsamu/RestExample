package org.iesfm.restexample.controller;

import org.iesfm.restexample.Department;
import org.iesfm.restexample.dao.DepartmentDAO;
import org.iesfm.restexample.dao.jdbc.JdbcDepartmentDAO;
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

    @RequestMapping(method = RequestMethod.GET, path = "/department")
    public List<Department> list() {
        return departmentDAO.list();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/department")
    public void createDepartment(@RequestBody Department department) {
        departmentDAO.insert(department);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/department/{departmentName}")
    public Department getDepartment(@PathVariable("departmentName") String departmentName) {
        Department department = departmentDAO.get(departmentName);

        if (department == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "department not found");
        } else {
            return department;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "department/{departmentName}")
    public void deleteDepartment(@PathVariable("departmentName") String departmentName) {
        getDepartment(departmentName);
        departmentDAO.deleteDepartment(departmentName);

    }
}
