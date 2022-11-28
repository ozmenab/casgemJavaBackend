package com.kodlamaio.bootcampproject.business.abstracts;

import com.kodlamaio.bootcampproject.business.requests.bootcamps.CreateBootcampRequest;
import com.kodlamaio.bootcampproject.business.requests.bootcamps.UpdateBootcampRequest;
import com.kodlamaio.bootcampproject.business.responses.bootcamps.CreateBootcampResponse;
import com.kodlamaio.bootcampproject.business.responses.bootcamps.GetAllBootcampResponse;
import com.kodlamaio.bootcampproject.business.responses.bootcamps.GetBootcampResponse;
import com.kodlamaio.bootcampproject.business.responses.bootcamps.UpdateBootcampResponse;
import com.kodlamaio.bootcampproject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.Result;

import java.util.List;

public interface BootcampService {
    DataResult<List<GetAllBootcampResponse>> getAll();
    DataResult<GetBootcampResponse> getById(int id);
    DataResult<CreateBootcampResponse> add(CreateBootcampRequest createBootcampRequest);
    DataResult<UpdateBootcampResponse> update(int id,UpdateBootcampRequest updateBootcampRequest);
    Result delete(int id);
}
