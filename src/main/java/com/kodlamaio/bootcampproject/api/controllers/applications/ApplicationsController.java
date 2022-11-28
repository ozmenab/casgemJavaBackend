package com.kodlamaio.bootcampproject.api.controllers.applications;

import com.kodlamaio.bootcampproject.business.abstracts.applications.ApplicationService;
import com.kodlamaio.bootcampproject.business.requests.applications.CreateApplicationRequest;
import com.kodlamaio.bootcampproject.business.requests.applications.UpdateApplicationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/applications")
@AllArgsConstructor
public class ApplicationsController {
    private ApplicationService applicationService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(applicationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(applicationService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateApplicationRequest createApplicationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.add(createApplicationRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody UpdateApplicationRequest updateApplicationRequest){
        updateApplicationRequest.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(applicationService.update(id,updateApplicationRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(applicationService.delete(id));
    }
}
