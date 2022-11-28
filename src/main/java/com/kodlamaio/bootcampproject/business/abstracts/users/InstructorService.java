package com.kodlamaio.bootcampproject.business.abstracts.users;

import com.kodlamaio.bootcampproject.business.requests.users.instructors.CreateInstructorRequest;
import com.kodlamaio.bootcampproject.business.requests.users.instructors.UpdateInstructorRequest;
import com.kodlamaio.bootcampproject.business.responses.users.instructors.CreateInstructorResponse;
import com.kodlamaio.bootcampproject.business.responses.users.instructors.GetAllInstructorsResponse;
import com.kodlamaio.bootcampproject.business.responses.users.instructors.GetInstructorResponse;
import com.kodlamaio.bootcampproject.business.responses.users.instructors.UpdateInstructorResponse;
import com.kodlamaio.bootcampproject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.Result;

import java.util.List;

public interface InstructorService {
    DataResult<List<GetAllInstructorsResponse>> getAll();
    DataResult<GetInstructorResponse> getById(int id);
    DataResult<CreateInstructorResponse> add(CreateInstructorRequest createInstructorRequest);
    DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest updateInstructorRequest);
    Result delete(int id);
}
