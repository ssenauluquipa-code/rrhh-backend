package com.rrhh.sistema.service;

import com.rrhh.sistema.model.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private List<Employee> employeeList = new ArrayList<>();

    @PostConstruct
    private void init(){
        employeeList = new ArrayList<>(List.of(
                new Employee(
                        9, "Fernando", "Cruz", LocalDate.of(1987, 9, 15), "Male", "Bolivian",
                        "78901234", "+591 7123 4575", "fernando.cruz@empresa.com", "Calle del Mar 456, Cochabamba",
                        "url_to_Photo_9", false, LocalDate.of(2015, 6, 30), LocalDate.of(2015, 6, 30), LocalDate.of(2023, 1, 10)
                ),
                new Employee(
                        10, "Gabriela", "Salazar", LocalDate.of(1992, 1, 20), "Female", "Bolivian",
                        "89012345", "+591 7123 4576", "gabriela.salazar@empresa.com", "Calle de la Unión 789, Sucre",
                        "url_to_Photo_10", true, LocalDate.of(2021, 4, 25), LocalDate.of(2021, 4, 25), LocalDate.of(2023, 1, 10)
                ),
                new Employee(
                        11, "Javier", "Mendoza", LocalDate.of(1983, 5, 5), "Male", "Bolivian",
                        "90123456", "+591 7123 4577", "javier.mendoza@empresa.com", "Calle de la Libertad 321, Tarija",
                        "url_to_Photo_11", true, LocalDate.of(2019, 8, 15), LocalDate.of(2019, 8, 15), LocalDate.of(2023, 1, 10)
                ),
                new Employee(
                        12, "Claudia", "Paredes", LocalDate.of(1991, 10, 10), "Female", "Bolivian",
                        "01234567", "+591 7123 4578", "claudia.paredes@empresa.com", "Calle de la Esperanza 654, Potosí",
                        "url_to_Photo_12", false, LocalDate.of(2020, 12, 1), LocalDate.of(2020, 12, 1), LocalDate.of(2023, 1, 10)
                ),
                new Employee(
                        13, "Andrés", "Vargas", LocalDate.of(1986, 3, 3), "Male", "Bolivian",
                        "12345678", "+591 7123 4579", "andres.vargas@empresa.com", "Calle del Sol 456, Cochabamba",
                        "url_to_Photo_13", true, LocalDate.of(2018, 7, 20), LocalDate.of(2018, 7, 20), LocalDate.of(2023, 1, 10)
                ),
                new Employee(
                        14, "Patricia", "Soto", LocalDate.of(1994, 11, 11), "Female", "Bolivian",
                        "23456789", "+591 7123 4580", "patricia.soto@empresa.com", "Calle de la Paz 789, La Paz",
                        "url_to_Photo_14", false, LocalDate.of(2021, 3, 1), LocalDate.of(2021, 3, 1), LocalDate.of(2023, 1, 10)
                ),
                new Employee(
                        15, "Ricardo", "Gutiérrez", LocalDate.of(1989, 8, 20), "Male", "Bolivian",
                        "34567890", "+591 7123 4581", "ricardo.gutierrez@empresa.com", "Calle del Río 321, Tarija",
                        "url_to_Photo_15", true, LocalDate.of(2017, 9, 12), LocalDate.of(2017, 9, 12), LocalDate.of(2023, 1, 10)
                )
        ));
    }

    public List<Employee> getEmployeeList(){
        return new ArrayList<>(employeeList);
    }

    public Employee findById(Integer Id){
        return employeeList.stream()
                .filter(e -> e.getEmployeeId() != null && e.getEmployeeId() == Id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + Id));
    }

    public List<Employee> findName(String name){
        return employeeList.stream()
                .filter(e ->name !=null && name.equals(e.getFirstName()))
                .collect(Collectors.toList());
    }

    public synchronized Employee createEmployye(Employee employee){
        //asugnamos nuevo Id
        int maxId = employeeList.stream()
                .mapToInt(e -> e.getEmployeeId() !=null ? e.getEmployeeId() : 0)
                .max()
                .orElse(8);
        employee.setEmployeeId(maxId + 1);
        employee.setCreatedAt(LocalDate.now());
        employee.setUpdatedAt(LocalDate.now());
        employeeList.add(employee);
        return  employee;
    }

    public synchronized Employee updateEmployee(Integer Id, Employee employee){
        Employee existente = findById(Id);
        existente.setFirstName(employee.getFirstName());
        existente.setLastName(employee.getLastName());
        existente.setDateOfBirth(employee.getDateOfBirth());
        existente.setGender(employee.getGender());
        existente.setNationality(employee.getNationality());
        existente.setDocumentId(employee.getDocumentId());
        existente.setPhone(employee.getPhone());
        existente.setEmail(employee.getEmail());
        existente.setAddress(employee.getAddress());
        existente.setPhoto(employee.getPhoto());
        existente.setStatus(employee.getStatus());
        existente.setHireDate(employee.getHireDate());
        existente.setUpdatedAt(LocalDate.now());

        return existente;
    }

    public synchronized Employee delete(Integer Id){
        AtomicReference<Employee> employee = new AtomicReference<>();
        employeeList.removeIf(e -> {
            if(e.getEmployeeId() != null && e.getEmployeeId() == Id){
                employee.set(e);
                return  true;
            }
            return false;
        });
        if(employee.get() == null){
            throw new RuntimeException("Empleado no encontrado con ID: " + Id);
        }
        return employee.get();
    }
}
