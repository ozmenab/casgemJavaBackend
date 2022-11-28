package com.kodlamaio.bootcampproject.business.concretes.users;

import com.kodlamaio.bootcampproject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampproject.business.constants.Messages;
import com.kodlamaio.bootcampproject.business.requests.users.applicants.CreateApplicantRequest;
import com.kodlamaio.bootcampproject.business.requests.users.applicants.UpdateApplicantRequest;
import com.kodlamaio.bootcampproject.business.responses.users.applicants.CreateApplicantResponse;
import com.kodlamaio.bootcampproject.business.responses.users.applicants.GetAllApplicantsResponse;
import com.kodlamaio.bootcampproject.business.responses.users.applicants.GetApplicantResponse;
import com.kodlamaio.bootcampproject.business.responses.users.applicants.UpdateApplicantResponse;
import com.kodlamaio.bootcampproject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampproject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampproject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.Result;
import com.kodlamaio.bootcampproject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampproject.dataAccess.abstracts.users.ApplicantRepository;
import com.kodlamaio.bootcampproject.entities.users.Applicant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicantManager implements ApplicantService {
    private ModelMapperService modelMapperService;
    private ApplicantRepository applicantRepository;

    @Override
    public DataResult<List<GetAllApplicantsResponse>> getAll() {
        List<Applicant> applicants = applicantRepository.findAll();
        List<GetAllApplicantsResponse> responses =applicants.stream().map(applicant ->
                modelMapperService
                        .forResponse()
                        .map(applicant,GetAllApplicantsResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responses, Messages.ApplicantsListed);
    }

    @Override
    public DataResult<GetApplicantResponse> getById(int id) {
        checkIfExistsApplicantById(id);
        Applicant applicant = applicantRepository.findById(id).get();
        GetApplicantResponse response = modelMapperService.forResponse().map(applicant,GetApplicantResponse.class);
        return new SuccessDataResult<>(response);
    }


    @Override
    public DataResult<CreateApplicantResponse> add(CreateApplicantRequest createApplicantRequest) {
        checkIfExistsApplicantByNationalIdentity(createApplicantRequest.getNationalIdentity());
        Applicant applicant = modelMapperService.forRequest().map(createApplicantRequest,Applicant.class);
        Applicant savedApplicant = applicantRepository.save(applicant);
        CreateApplicantResponse response = modelMapperService.forResponse().map(savedApplicant,CreateApplicantResponse.class);
        return new SuccessDataResult<>(response,Messages.ApplicantCreated);
    }

    @Override
    public DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest updateApplicantRequest) {
        checkIfExistsApplicantById(updateApplicantRequest.getId());
        Applicant applicant = modelMapperService.forRequest().map(updateApplicantRequest,Applicant.class);
        Applicant updatedApplicant=applicantRepository.save(applicant);
        UpdateApplicantResponse response = modelMapperService.forResponse().map(updatedApplicant,UpdateApplicantResponse.class);
        return new SuccessDataResult<>(response,Messages.ApplicantUpdated);

    }

    @Override
    public Result delete(int id) {
        checkIfExistsApplicantById(id);
        applicantRepository.deleteById(id);
        return new SuccessResult(Messages.ApplicantDeleted);
    }

    @Override
    public boolean checkIsApplicant(int id) {
        Applicant applicant = applicantRepository.findById(id).orElse(null);
        if(applicant == null)
            return false;
        return true;
    }

    //Rules
    private void checkIfExistsApplicantByNationalIdentity(String nationalIdentity){
       Applicant applicant = applicantRepository.findByNationalIdentity(nationalIdentity);
       if(applicant != null)
           throw new BusinessException(Messages.ApplicantExists);
    }

    private void checkIfExistsApplicantById(int id) {
        Applicant applicant = applicantRepository.findById(id).orElse(null);
        if(applicant == null)
            throw new BusinessException(Messages.ApplicantIfNotExists);
    }

}
