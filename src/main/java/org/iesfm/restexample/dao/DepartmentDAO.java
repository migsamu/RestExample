package org.iesfm.restexample.dao;

import org.iesfm.restexample.Department;

import java.util.List;

public interface DepartmentDAO {

    List<Department> list();

    void insert(Department department);

    Department get(String departmentName);

    void deleteDepartment(String department);
}
