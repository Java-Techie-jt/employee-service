package com.javatechie.util;

import com.javatechie.dto.EmployeeDTO;
import com.javatechie.entity.Employee;

import java.util.Arrays;
import java.util.List;

public class EmployeeBuilder {

    public static List<EmployeeDTO> getListDTO() {
        return Arrays.asList(new EmployeeDTO(1,"Basant","DEV",50000),
                new EmployeeDTO(2,"Santosh","QA",80000));
    }

    public static List<Employee> getListEntities() {
        return Arrays.asList(new Employee(1,"Basant","DEV",50000),
                new Employee(2,"Santosh","QA",80000));
    }

    public static EmployeeDTO getDTO() {
        return new EmployeeDTO(1,"Basant","DEV",50000);
    }

    public static Employee getEntity() {
        return new Employee(1,"Basant","DEV",50000);
    }
}
