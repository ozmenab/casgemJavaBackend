package com.kodlamaio.bootcampproject.business.concretes.users;

import com.kodlamaio.bootcampproject.business.constants.Messages;
import com.kodlamaio.bootcampproject.business.responses.users.instructors.CreateInstructorResponse;
import com.kodlamaio.bootcampproject.business.abstracts.users.InstructorService;
import com.kodlamaio.bootcampproject.business.requests.users.instructors.CreateInstructorRequest;
import com.kodlamaio.bootcampproject.business.requests.users.instructors.UpdateInstructorRequest;
import com.kodlamaio.bootcampproject.business.responses.users.instructors.GetAllInstructorsResponse;
import com.kodlamaio.bootcampproject.business.responses.users.instructors.GetInstructorResponse;
import com.kodlamaio.bootcampproject.business.responses.users.instructors.UpdateInstructorResponse;
import com.kodlamaio.bootcampproject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampproject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampproject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.Result;
import com.kodlamaio.bootcampproject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampproject.dataAccess.abstracts.users.InstructorRepository;
import com.kodlamaio.bootcampproject.entities.users.Instructor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InstructorManager implements InstructorService {
    private InstructorRepository instructorRepository;
    private ModelMapperService modelMapperService;
    @Override
    public DataResult<List<GetAllInstructorsResponse>> getAll() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<GetAllInstructorsResponse> responses = instructors.stream().map(instructor ->
                modelMapperService
                        .forResponse()
                        .map(instructor,GetAllInstructorsResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responses,Messages.InstructorsListed);
    }

    @Override
    public DataResult<GetInstructorResponse> getById(int id) {
        checkIfExistsEmployeeById(id);
        Instructor instructor = instructorRepository.findById(id).get();
        GetInstructorResponse response = modelMapperService.forResponse().map(instructor,GetInstructorResponse.class);
        DataResult<GetInstructorResponse> result = new DataResult<>(response,true);
        return result;
    }

    @Override
    public DataResult<CreateInstructorResponse> add(CreateInstructorRequest createInstructorRequest) {
        checkIfExistsInstructorByNationalIdentity(createInstructorRequest.getNationalIdentity());
        Instructor instructor = modelMapperService.forRequest().map(createInstructorRequest, Instructor.class);
        instructorRepository.save(instructor);
        CreateInstructorResponse createInstructorResponse = modelMapperService.forResponse().map(instructor, CreateInstructorResponse.class);
        return new SuccessDataResult<CreateInstructorResponse>(createInstructorResponse,Messages.InstructorCreated);
    }

    @Override
    public DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest updateInstructorRequest) {
        checkIfExistsEmployeeById(updateInstructorRequest.getId());
        Instructor instructor = modelMapperService.forRequest().map(updateInstructorRequest,Instructor.class);
        Instructor updatedInstructor = instructorRepository.save(instructor);
        UpdateInstructorResponse response = modelMapperService.forResponse().map(updatedInstructor,UpdateInstructorResponse.class);
        DataResult<UpdateInstructorResponse> result = new DataResult<>(response,true,Messages.InstructorUpdated);
        return result;
    }

    @Override
    public Result delete(int id) {
        checkIfExistsEmployeeById(id);
        instructorRepository.deleteById(id);
        return new SuccessResult(Messages.InstructorDeleted);
    }

    private void checkIfExistsInstructorByNationalIdentity(String nationalIdentity){
        Instructor instructor = instructorRepository.findByNationalIdentity(nationalIdentity);
        if(instructor != null)
            throw new BusinessException(Messages.EmployeeExists);
    }

    private void checkIfExistsEmployeeById(int id) {
        Instructor instructor = instructorRepository.findById(id).orElse(null);
        if(instructor == null)
            throw new BusinessException(Messages.EmployeeIfNotExists);
    }
}
