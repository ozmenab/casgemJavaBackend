package com.kodlamaio.bootcampproject.business.concretes;

import com.kodlamaio.bootcampproject.business.abstracts.BlackListService;
import com.kodlamaio.bootcampproject.business.constants.Messages;
import com.kodlamaio.bootcampproject.business.requests.blacklists.CreateBlackListRequest;
import com.kodlamaio.bootcampproject.business.requests.blacklists.UpdateBlackListRequest;
import com.kodlamaio.bootcampproject.business.responses.blacklists.CreateBlackListResponse;
import com.kodlamaio.bootcampproject.business.responses.blacklists.GetAllBlackListsResponse;
import com.kodlamaio.bootcampproject.business.responses.blacklists.GetBlackListResponse;
import com.kodlamaio.bootcampproject.business.responses.blacklists.UpdateBlackListResponse;
import com.kodlamaio.bootcampproject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampproject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampproject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.Result;
import com.kodlamaio.bootcampproject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampproject.dataAccess.abstracts.BlackListRepository;
import com.kodlamaio.bootcampproject.entities.BlackList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BlackListManager implements BlackListService {
    private BlackListRepository blackListRepository;
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<List<GetAllBlackListsResponse>> getAll() {
        List<BlackList> blackLists = blackListRepository.findAll();
        List<GetAllBlackListsResponse> responses =
                blackLists.stream()
                        .map(blackList -> modelMapperService
                                .forResponse()
                                .map(blackList,GetAllBlackListsResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responses);
    }

    @Override
    public DataResult<GetBlackListResponse> getById(int id) {
        BlackList blackList = checkIfBlackListExists(id);
        GetBlackListResponse response = modelMapperService.forResponse().map(blackList,GetBlackListResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<CreateBlackListResponse> add(CreateBlackListRequest createBlackListRequest) {
        checkIfBlackListExistsByApplicantId(createBlackListRequest.getApplicantId());
        BlackList blackList = modelMapperService.forRequest().map(createBlackListRequest,BlackList.class);
        blackList.setId(0);
        blackListRepository.save(blackList);
        CreateBlackListResponse response = modelMapperService.forResponse().map(blackList,CreateBlackListResponse.class);
        response.setId(blackList.getId());
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<UpdateBlackListResponse> update(int id,UpdateBlackListRequest updateBlackListRequest) {
        checkIfBlackListExists(id);
        BlackList blackList = modelMapperService.forRequest().map(updateBlackListRequest,BlackList.class);
        blackListRepository.save(blackList);
        UpdateBlackListResponse response = modelMapperService.forResponse().map(blackList,UpdateBlackListResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public Result delete(int id) {
        checkIfBlackListExists(id);
        blackListRepository.deleteById(id);
        return new SuccessResult(Messages.BlackListDeleted);
    }

    @Override
    public void IsBlackListByApplicantId(int applicantId) {
        checkIfBlackListExistsByApplicantId(applicantId);
    }

    private void checkIfBlackListExistsByApplicantId(int applicantId){
        BlackList blackList = blackListRepository.findByApplicantId(applicantId);
        if(blackList != null)
            throw new BusinessException(Messages.BlackListExistsApplicantById);
    }
    private BlackList checkIfBlackListExists(int id){
        BlackList blackList = blackListRepository.findById(id).orElse(null);
        if(blackList == null)
            throw new BusinessException(Messages.BlackListNotExists);
        return blackList;
    }
}
