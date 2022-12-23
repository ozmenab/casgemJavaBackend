package com.kodlamaio.bootcampproject.business.concretes.users;

import com.kodlamaio.bootcampproject.business.abstracts.users.EmployeeService;
import com.kodlamaio.bootcampproject.business.constants.Messages;
import com.kodlamaio.bootcampproject.business.requests.users.employees.CreateEmployeeRequest;
import com.kodlamaio.bootcampproject.business.requests.users.employees.UpdateEmployeeRequest;
import com.kodlamaio.bootcampproject.business.responses.users.employees.CreateEmployeeResponse;
import com.kodlamaio.bootcampproject.business.responses.users.employees.GetAllEmployeesResponse;
import com.kodlamaio.bootcampproject.business.responses.users.employees.GetEmployeeResponse;
import com.kodlamaio.bootcampproject.business.responses.users.employees.UpdateEmployeeResponse;
import com.kodlamaio.bootcampproject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampproject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampproject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.Result;
import com.kodlamaio.bootcampproject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampproject.dataAccess.abstracts.users.EmployeeRepository;
import com.kodlamaio.bootcampproject.entities.users.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<List<GetAllEmployeesResponse>> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<GetAllEmployeesResponse> responses =employees.stream()
                .map(employee -> modelMapperService
                        .forResponse()
                        .map(employee,GetAllEmployeesResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responses, Messages.EmployeesListed);
    }

    @Override
    public DataResult<GetEmployeeResponse> getById(int id) {
        checkIfExistsEmployeeById(id);
        Employee employee = employeeRepository.findById(id).get();
        GetEmployeeResponse response = modelMapperService.forResponse().map(employee, GetEmployeeResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest createEmployeeRequest) {
        checkIfExistsEmployeeByNationalIdentity(createEmployeeRequest.getNationalIdentity());
        Employee employee = modelMapperService.forRequest().map(createEmployeeRequest,Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        CreateEmployeeResponse response = modelMapperService.forResponse().map(savedEmployee,CreateEmployeeResponse.class);
        return new SuccessDataResult<>(response,Messages.EmployeeCreated);
    }

    @Override
    public DataResult<UpdateEmployeeResponse> update(int id,UpdateEmployeeRequest updateEmployeeRequest) {
        checkIfExistsEmployeeById(id);
        Employee employee = modelMapperService.forRequest().map(updateEmployeeRequest,Employee.class);
        employee.setId(id);
        Employee updatedEmployee=employeeRepository.save(employee);
        UpdateEmployeeResponse response = modelMapperService.forResponse().map(updatedEmployee,UpdateEmployeeResponse.class);
        return new SuccessDataResult<>(response,Messages.EmployeeUpdated);
    }

    @Override
    public Result delete(int id) {
        checkIfExistsEmployeeById(id);
        employeeRepository.deleteById(id);
        return new SuccessResult(Messages.EmployeeDeleted);
    }

    private void checkIfExistsEmployeeByNationalIdentity(String nationalIdentity){
        Employee employee = employeeRepository.findByNationalIdentity(nationalIdentity);
        if(employee != null)
            throw new BusinessException(Messages.EmployeeExists);
    }

    private void checkIfExistsEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee == null)
            throw new BusinessException(Messages.EmployeeIfNotExists);
    }
}
