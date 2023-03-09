package com.csi.controller;

import com.csi.constants.EndPointConstant;
import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;


    @PostMapping(EndPointConstant.SAVE_DATA)
    public Employee saveData(@RequestBody Employee employee) {

        return employeeServiceImpl.saveData(employee);
    }

    @GetMapping("/getdatabyid/{empId}")

    public Optional<Employee> getDataById(@PathVariable int empId) {

        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(() -> new RecordNotFoundException("Id does not foundd"));


        return employeeServiceImpl.getDataById(employee1.getEmpId());
    }

    @PutMapping("/updatedata/{empId}")

    public ResponseEntity<Employee> updateData(@PathVariable int empId, @RequestBody Employee employee) {


        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(() -> new RecordNotFoundException("Id does not foundd"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpDOB(employee.getEmpDOB());

        return ResponseEntity.ok(employeeServiceImpl.updateData(employee1));


    }

    @GetMapping(EndPointConstant.GET_ALL_DATA)


    public ResponseEntity<List<Employee>> getAllData() {

        return ResponseEntity.ok(employeeServiceImpl.getALlData());
    }

    @DeleteMapping("/deletebyid/{empId}")


    public ResponseEntity<String> deleteById(@PathVariable int empId) {
        //        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(() -> new RecordNotFoundException("Id does not foundd"));

        Employee employee = new Employee();

        if (employee.getEmpId() == empId) {

            employeeServiceImpl.deleteById(empId);
            return ResponseEntity.ok("deleted");
        } else {
            throw new RecordNotFoundException("Id Does not exittt");
        }
    }
}
