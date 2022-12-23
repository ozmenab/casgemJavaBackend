package com.kodlamaio.bootcampproject.api.controllers.users;

import com.kodlamaio.bootcampproject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampproject.business.requests.users.applicants.CreateApplicantRequest;
import com.kodlamaio.bootcampproject.business.requests.users.applicants.UpdateApplicantRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// http://localhost:8080/api/applicants
@RestController
@RequestMapping("/api/v1/applicants")
@AllArgsConstructor
public class ApplicantsController {
    private ApplicantService applicantService;

    // http://localhost:8080/api/v1/applicants
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(applicantService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(applicantService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid CreateApplicantRequest createApplicantRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(applicantService.add(createApplicantRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody @Valid UpdateApplicantRequest updateApplicantRequest){
        updateApplicantRequest.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(applicantService.update(updateApplicantRequest));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(applicantService.delete(id));
    }
}
