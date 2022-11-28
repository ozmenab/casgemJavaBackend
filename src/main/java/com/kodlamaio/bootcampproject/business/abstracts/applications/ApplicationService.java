package com.kodlamaio.bootcampproject.business.abstracts.applications;

import com.kodlamaio.bootcampproject.business.requests.applications.CreateApplicationRequest;
import com.kodlamaio.bootcampproject.business.requests.applications.UpdateApplicationRequest;
import com.kodlamaio.bootcampproject.business.responses.applications.CreateApplicationResponse;
import com.kodlamaio.bootcampproject.business.responses.applications.GetAllApplicationsResponse;
import com.kodlamaio.bootcampproject.business.responses.applications.GetApplicationResponse;
import com.kodlamaio.bootcampproject.business.responses.applications.UpdateApplicationResponse;
import com.kodlamaio.bootcampproject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.Result;
import com.kodlamaio.bootcampproject.entities.applications.Application;

import java.util.List;

public interface ApplicationService {
    DataResult<List<GetAllApplicationsResponse>> getAll();
    DataResult<GetApplicationResponse> getById(int id);
    DataResult<CreateApplicationResponse> add(CreateApplicationRequest createApplicationRequest);
    DataResult<UpdateApplicationResponse> update(int id,UpdateApplicationRequest updateApplicationRequest);
    Result delete(int id);
}
