package org.iesfm.restexample.dao.jdbc;

import org.iesfm.restexample.Department;
import org.iesfm.restexample.dao.DepartmentDAO;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcDepartmentDAO implements DepartmentDAO {

    private static final String DELETE = "DELETE FROM Department where name=:name";
    private static final String SELECT_DEPARTMENTS = "SELECT * FROM Department";
    private static final String SELECT_DEPARTMENT = "SELECT * FROM Department WHERE name=:name";
    private static final String INSERT_DEPARTMENT =
            "INSERT INTO Department(name, description) " +
                    "VALUES (:name,:description)";

    private NamedParameterJdbcTemplate jdbc;

    public JdbcDepartmentDAO(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Department department) {

        Map<String, Object> params = new HashMap<>();

        params.put("name", department.getName());
        params.put("description", department.getDescription());

        jdbc.update(INSERT_DEPARTMENT, params);
    }

    @Override
    public void deleteDepartment(String departmentName) {
        Map<String, Object> params = new HashMap<>();

        params.put("name", departmentName);

        jdbc.update(DELETE, params);
    }

    @Override
    public List<Department> list() {
        Map<String, Object> params = new HashMap<>();

        return jdbc.query(SELECT_DEPARTMENTS, params, (rs, n) ->
                new Department(
                        rs.getString("name"),
                        rs.getString("description")
                ));
    }

    @Override
    public Department get(String departmentName) {
        Map<String, Object> params = new HashMap<>();

        params.put("name", departmentName);

        return jdbc.queryForObject(SELECT_DEPARTMENT, params, (rs, n) ->
                new Department(
                        rs.getString("name"),
                        rs.getString("description")
                ));
    }
}
