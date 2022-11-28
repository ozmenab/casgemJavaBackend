package com.kodlamaio.bootcampproject.business.concretes;

import com.kodlamaio.bootcampproject.business.abstracts.BootcampService;
import com.kodlamaio.bootcampproject.business.constants.Messages;
import com.kodlamaio.bootcampproject.business.requests.bootcamps.CreateBootcampRequest;
import com.kodlamaio.bootcampproject.business.requests.bootcamps.UpdateBootcampRequest;
import com.kodlamaio.bootcampproject.business.responses.bootcamps.CreateBootcampResponse;
import com.kodlamaio.bootcampproject.business.responses.bootcamps.GetAllBootcampResponse;
import com.kodlamaio.bootcampproject.business.responses.bootcamps.GetBootcampResponse;
import com.kodlamaio.bootcampproject.business.responses.bootcamps.UpdateBootcampResponse;
import com.kodlamaio.bootcampproject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampproject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampproject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.Result;
import com.kodlamaio.bootcampproject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampproject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampproject.dataAccess.abstracts.BootcampRepository;
import com.kodlamaio.bootcampproject.entities.Bootcamp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BootcampManager implements BootcampService {
    private BootcampRepository bootcampRepository;
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<List<GetAllBootcampResponse>> getAll() {
        List<Bootcamp> bootcamps = bootcampRepository.findAll();
        List<GetAllBootcampResponse> responseList =
                bootcamps.stream().map(bootcamp ->
                        modelMapperService
                                .forResponse()
                                .map(bootcamp,GetAllBootcampResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responseList);
    }

    @Override
    public DataResult<GetBootcampResponse> getById(int id) {
        Bootcamp bootcamp = checkIfExistsBootcampById(id);
        GetBootcampResponse response = modelMapperService.forResponse().map(bootcamp,GetBootcampResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<CreateBootcampResponse> add(CreateBootcampRequest createBootcampRequest) {
        checkIfBootcampEndDateIsBeforeStartDate(createBootcampRequest.getStartDate(),createBootcampRequest.getEndDate());
        Bootcamp bootcamp = modelMapperService.forRequest().map(createBootcampRequest,Bootcamp.class);
        bootcamp.setId(0);
        bootcampRepository.save(bootcamp);
        CreateBootcampResponse response = modelMapperService.forResponse().map(bootcamp,CreateBootcampResponse.class);
        return new SuccessDataResult<>(response,Messages.BootcampCreated);
    }

    @Override
    public DataResult<UpdateBootcampResponse> update(int id, UpdateBootcampRequest updateBootcampRequest) {
        checkIfExistsBootcampById(id);
        Bootcamp bootcamp = modelMapperService.forRequest().map(updateBootcampRequest,Bootcamp.class);
        bootcamp.setId(id);
        bootcampRepository.save(bootcamp);
        UpdateBootcampResponse response = modelMapperService.forResponse().map(bootcamp,UpdateBootcampResponse.class);
        return new SuccessDataResult<>(response,Messages.BootcampUpdated);
    }

    @Override
    public Result delete(int id) {
        checkIfExistsBootcampById(id);
        bootcampRepository.deleteById(id);
        return new SuccessResult(Messages.BootcampDeleted);
    }

    private Bootcamp checkIfExistsBootcampById(int id) {
       Bootcamp bootcamp = bootcampRepository.findById(id).orElse(null);
       if(bootcamp == null)
           throw new BusinessException(Messages.BootcampIfNotExists);
       return bootcamp;
    }

    private void checkIfBootcampEndDateIsBeforeStartDate(LocalDate startDate,LocalDate endDate){
        if(endDate.isBefore(startDate))
            throw new BusinessException(Messages.BootcampEndDateNotBeforeStartDate);
    }
}
