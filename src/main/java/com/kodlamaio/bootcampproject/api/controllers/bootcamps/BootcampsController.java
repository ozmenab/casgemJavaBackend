package com.kodlamaio.bootcampproject.api.controllers.bootcamps;

import com.kodlamaio.bootcampproject.business.abstracts.BootcampService;
import com.kodlamaio.bootcampproject.business.requests.bootcamps.CreateBootcampRequest;
import com.kodlamaio.bootcampproject.business.requests.bootcamps.UpdateBootcampRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bootcamps")
@AllArgsConstructor
public class BootcampsController {
    private BootcampService bootcampService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(bootcampService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(bootcampService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid CreateBootcampRequest createBootcampRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(bootcampService.add(createBootcampRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody @Valid UpdateBootcampRequest updateBootcampRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bootcampService.update(id,updateBootcampRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(bootcampService.delete(id));
    }
}
