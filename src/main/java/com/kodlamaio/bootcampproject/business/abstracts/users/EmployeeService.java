package com.kodlamaio.bootcampproject.business.abstracts.users;

import com.kodlamaio.bootcampproject.business.requests.users.employees.CreateEmployeeRequest;
import com.kodlamaio.bootcampproject.business.requests.users.employees.UpdateEmployeeRequest;
import com.kodlamaio.bootcampproject.business.responses.users.employees.CreateEmployeeResponse;
import com.kodlamaio.bootcampproject.business.responses.users.employees.GetAllEmployeesResponse;
import com.kodlamaio.bootcampproject.business.responses.users.employees.GetEmployeeResponse;
import com.kodlamaio.bootcampproject.business.responses.users.employees.UpdateEmployeeResponse;
import com.kodlamaio.bootcampproject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.Result;

import java.util.List;

public interface EmployeeService {
    DataResult<List<GetAllEmployeesResponse>> getAll();
    DataResult<GetEmployeeResponse> getById(int id);
    DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest createEmployeeRequest);
    DataResult<UpdateEmployeeResponse> update(int id,UpdateEmployeeRequest updateEmployeeRequest);
    Result delete(int id);
}
