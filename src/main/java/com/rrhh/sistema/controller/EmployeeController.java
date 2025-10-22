package com.rrhh.sistema.controller;

import com.rrhh.sistema.model.Employee;
import com.rrhh.sistema.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/empleados")
    public List<Employee> getEmployee(){
        return employeeService.getEmployeeList();
    }

    @GetMapping("/empleados/{id}")
    public Employee findById(@PathVariable Integer id){
        return employeeService.findById(id);
    }

    @GetMapping("/empleados/nombre/{name}")
    public List<Employee> findName(@PathVariable String name){
        return employeeService.findName(name);
    }

    @PostMapping("/empelados")
    public Employee createEmpleado(@RequestBody Employee empleado){
        return employeeService.createEmployye(empleado);
    }

    @PutMapping("/empleados/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee empelado){
        return employeeService.updateEmployee(id, empelado);
    }

    @DeleteMapping("/empleados/{id}")
    public Employee delete(@PathVariable Integer id){
        return employeeService.delete(id);
    }
}
