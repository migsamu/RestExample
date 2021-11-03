package org.iesfm.restexample.inmemory;

import org.iesfm.restexample.Department;
import org.iesfm.restexample.dao.DepartmentDAO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InMemoryDepartmentDAO implements DepartmentDAO {

    private Map<String, Department> departments = new HashMap<>();

    @Override
    public List<Department> list() {
        return new LinkedList<>(departments.values());
    }

    @Override
    public void insert(Department department) {
        departments.put(department.getName(), department);
    }

    @Override
    public Department get(String departmentName) {
        return departments.get(departmentName);
    }

    @Override
    public void deleteDepartment(String department) {
        departments.remove(department);
    }
}
