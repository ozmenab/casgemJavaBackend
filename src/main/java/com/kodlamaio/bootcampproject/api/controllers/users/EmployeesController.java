package com.kodlamaio.bootcampproject.api.controllers.users;

import com.kodlamaio.bootcampproject.business.abstracts.users.EmployeeService;
import com.kodlamaio.bootcampproject.business.requests.users.employees.CreateEmployeeRequest;
import com.kodlamaio.bootcampproject.business.requests.users.employees.UpdateEmployeeRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// http://localhost:8080/api/employees
@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeesController {
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.add(createEmployeeRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.update(id,updateEmployeeRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.delete(id));
    }
}
