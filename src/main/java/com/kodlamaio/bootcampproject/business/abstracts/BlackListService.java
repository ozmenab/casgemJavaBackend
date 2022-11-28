package com.kodlamaio.bootcampproject.business.abstracts;

import com.kodlamaio.bootcampproject.business.requests.blacklists.CreateBlackListRequest;
import com.kodlamaio.bootcampproject.business.requests.blacklists.UpdateBlackListRequest;
import com.kodlamaio.bootcampproject.business.responses.blacklists.CreateBlackListResponse;
import com.kodlamaio.bootcampproject.business.responses.blacklists.GetAllBlackListsResponse;
import com.kodlamaio.bootcampproject.business.responses.blacklists.GetBlackListResponse;
import com.kodlamaio.bootcampproject.business.responses.blacklists.UpdateBlackListResponse;
import com.kodlamaio.bootcampproject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.Result;

import java.util.List;

public interface BlackListService {
    DataResult<List<GetAllBlackListsResponse>> getAll();
    DataResult<GetBlackListResponse> getById(int id);
    DataResult<CreateBlackListResponse> add(CreateBlackListRequest createBlackListRequest);
    DataResult<UpdateBlackListResponse> update(UpdateBlackListRequest updateBlackListRequest);
    Result delete(int id);
    void IsBlackListByApplicantId(int applicantId);

}
