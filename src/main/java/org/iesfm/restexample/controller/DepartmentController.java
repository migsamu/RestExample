package org.iesfm.restexample.controller;

import org.iesfm.restexample.Department;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class DepartmentController {

    private List<Department> departments = Arrays.asList(
            new Department("Informatica", "cosas de ordenadores"),
            new Department("Ventas", "cosas de ventas"));

    @RequestMapping(method = RequestMethod.GET, path = "/department")
    public List<Department> list() {
        return departments;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/department")
    public void createDepartment(@RequestBody Department department) {
        departments.add(department);
    }
}
