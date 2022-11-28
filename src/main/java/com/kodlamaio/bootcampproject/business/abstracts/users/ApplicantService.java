package com.kodlamaio.bootcampproject.business.abstracts.users;

import com.kodlamaio.bootcampproject.business.requests.users.applicants.CreateApplicantRequest;
import com.kodlamaio.bootcampproject.business.requests.users.applicants.UpdateApplicantRequest;
import com.kodlamaio.bootcampproject.business.responses.users.applicants.CreateApplicantResponse;
import com.kodlamaio.bootcampproject.business.responses.users.applicants.GetAllApplicantsResponse;
import com.kodlamaio.bootcampproject.business.responses.users.applicants.GetApplicantResponse;
import com.kodlamaio.bootcampproject.business.responses.users.applicants.UpdateApplicantResponse;
import com.kodlamaio.bootcampproject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.Result;

import java.util.List;

public interface ApplicantService {
    DataResult<List<GetAllApplicantsResponse>> getAll();
    DataResult<GetApplicantResponse> getById(int id);
    DataResult<CreateApplicantResponse> add(CreateApplicantRequest createApplicantRequest);
    DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest updateApplicantRequest);
    Result delete(int id);

    boolean checkIsApplicant(int id);
}
