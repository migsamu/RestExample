package org.iesfm.restexample.dao.jdbc;

import org.iesfm.restexample.Employee;
import org.iesfm.restexample.dao.EmployeeDAO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcEmployeeDAO implements EmployeeDAO {


    private static final String SELECT_EMPLOYEES = "SELECT * FROM Employee";
    private static final String DELETE_EMPLOYEE_BY_NIF = "SELECT * FROM Employee WHERE nif";
    private static final String SELECT_EMPLOYEE_BY_NIF = "SELECT * FROM Employee WHERE nif = :nif";
    private static final String INSERT_EMPLOYEE = "INSERT INTO Employee(" +
            "nif," +
            "name," +
            "surname," +
            "role," +
            "department_name) " +
            "VALUES(" +
            ":nif," +
            ":name," +
            ":surname," +
            ":role," +
            ":departmentName)";

    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER =
            (rs, n) ->
                    new Employee(
                            rs.getString("nif"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("role"),
                            rs.getString("department_name")
                    );


    private NamedParameterJdbcTemplate jdbc;

    public JdbcEmployeeDAO(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Employee> list() {
        return jdbc.query(SELECT_EMPLOYEES, EMPLOYEE_ROW_MAPPER);
    }

    @Override
    public Employee getEmployeeByNif(String nif) {
        Map<String, Object> params = new HashMap<>();
        params.put("nif", nif);

        return jdbc.queryForObject(SELECT_EMPLOYEE_BY_NIF, params, EMPLOYEE_ROW_MAPPER);

    }

    @Override
    public boolean insert(Employee employee) {

        Map<String, Object> params = new HashMap<>();

        params.put("nif", employee.getNif());
        params.put("name", employee.getName());
        params.put("surname", employee.getSurname());
        params.put("role", employee.getRole());
        params.put("departmentName", employee.getDepartmentName());
        return jdbc.update(INSERT_EMPLOYEE, params) == 1;
    }


    @Override
    public boolean delete(String nif) {
        return false;
    }
}
