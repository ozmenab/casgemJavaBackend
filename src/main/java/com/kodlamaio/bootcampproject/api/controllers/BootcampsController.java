package com.kodlamaio.bootcampproject.api.controllers;

import com.kodlamaio.bootcampproject.business.abstracts.BootcampService;
import com.kodlamaio.bootcampproject.business.requests.bootcamps.CreateBootcampRequest;
import com.kodlamaio.bootcampproject.business.requests.bootcamps.UpdateBootcampRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/bootcamps")
@AllArgsConstructor
public class BootcampsController {
    private BootcampService bootcampService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(bootcampService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(bootcampService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateBootcampRequest createBootcampRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(bootcampService.add(createBootcampRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody @Valid UpdateBootcampRequest updateBootcampRequest){
        updateBootcampRequest.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bootcampService.update(id,updateBootcampRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(bootcampService.delete(id));
    }
}
