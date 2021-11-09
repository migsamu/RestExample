package org.iesfm.restexample.dao;

import org.iesfm.restexample.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> list();

    Employee getEmployeeByNif(String nif);

    void insert(Employee employee);

    void delete(String nif);

}
/*
b. Controlador de empleados: nif, nombre, apellidos, nombreDepartamento, role

i. Pedir todos los empleados GET http://{host}:{puerto}/employee

ii. Pedir un empleado GET http://{host}:{puerto}/employees/{nif}

iii. Crear un empleado POST http://{host}:{puerto}/employees

1. JSON en el body

iv. Eliminar un empleado DELETE http://{host}:{puerto}/empoyees/{nif}

v. Mostrar los empleados de un departamento GET http://{host}:{puerto}/departments/{departmentName}/employees
 */