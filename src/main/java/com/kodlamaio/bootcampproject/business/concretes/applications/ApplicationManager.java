package com.kodlamaio.bootcampproject.business.concretes.applications;

import com.kodlamaio.bootcampproject.business.abstracts.BlackListService;
import com.kodlamaio.bootcampproject.business.abstracts.applications.ApplicationService;
import com.kodlamaio.bootcampproject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampproject.business.constants.Messages;
import com.kodlamaio.bootcampproject.business.requests.applications.CreateApplicationRequest;
import com.kodlamaio.bootcampproject.business.requests.applications.UpdateApplicationRequest;
import com.kodlamaio.bootcampproject.business.responses.applications.*;
import com.kodlamaio.bootcampproject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampproject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampproject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.Result;
import com.kodlamaio.bootcampproject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampproject.dataAccess.abstracts.applications.ApplicationRepository;
import com.kodlamaio.bootcampproject.entities.applications.Application;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationManager implements ApplicationService {
    private ApplicationRepository applicationRepository;
    private ModelMapperService modelMapperService;
    private ApplicantService applicantService;
    private BlackListService blackListService;

    @Override
    public DataResult<List<GetAllApplicationsResponse>> getAll() {
        List<Application> applications = applicationRepository.findAll();
        List<GetAllApplicationsResponse> responseList =
                applications.stream().map(bootcamp ->
                        modelMapperService
                                .forResponse()
                                .map(bootcamp,GetAllApplicationsResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responseList);
    }

    @Override
    public DataResult<GetApplicationResponse> getById(int id) {
        Application application = checkIfExistsApplicationById(id);
        GetApplicationResponse response = modelMapperService.forResponse().map(application,GetApplicationResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<CreateApplicationResponse> add(CreateApplicationRequest createApplicationRequest) {
        checkIsApplicantBlackList(createApplicationRequest.getApplicantId());
        checkIsApplicant(createApplicationRequest.getApplicantId());
        Application application = modelMapperService.forRequest().map(createApplicationRequest,Application.class);
        applicationRepository.save(application);
        CreateApplicationResponse response = modelMapperService.forResponse().map(application,CreateApplicationResponse.class);
        return new SuccessDataResult<>(response,Messages.ApplicationCreated);
    }

    @Override
    public DataResult<UpdateApplicationResponse> update(int id,UpdateApplicationRequest updateApplicationRequest) {
        checkIfExistsApplicationById(id);
        Application application = modelMapperService.forRequest().map(updateApplicationRequest,Application.class);
        application.setId(id);
        applicationRepository.save(application);
        UpdateApplicationResponse response = modelMapperService.forResponse().map(application,UpdateApplicationResponse.class);
        return new SuccessDataResult<>(response,Messages.ApplicationUpdated);
    }

    @Override
    public Result delete(int id) {
        checkIfExistsApplicationById(id);
        applicationRepository.deleteById(id);
        return new SuccessResult(Messages.ApplicationDeleted);
    }

    private Application checkIfExistsApplicationById(int id) {
        Application application = applicationRepository.findById(id).orElse(null);
        if(application == null)
            throw new BusinessException(Messages.BootcampIfNotExists);
        return application;
    }

    private void checkIsApplicant(int id) {
        boolean result = applicantService.checkIsApplicant(id);
        if(result == false)
            throw new BusinessException(Messages.ApplicantIfNotExists);
    }
    private void checkIsApplicantBlackList(int applicantId) {
        blackListService.IsBlackListByApplicantId(applicantId);
    }
}
