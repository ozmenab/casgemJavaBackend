package com.kodlamaio.bootcampproject.api.controllers.users;

import com.kodlamaio.bootcampproject.business.abstracts.users.InstructorService;
import com.kodlamaio.bootcampproject.business.requests.users.instructors.CreateInstructorRequest;
import com.kodlamaio.bootcampproject.business.requests.users.instructors.UpdateInstructorRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/instructors")
@AllArgsConstructor
public class InstructorsController {
    private InstructorService instructorService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(instructorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(instructorService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateInstructorRequest createInstructorRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.add(createInstructorRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody @Valid UpdateInstructorRequest updateInstructorRequest){
        updateInstructorRequest.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(instructorService.update(updateInstructorRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(instructorService.delete(id));
    }
}
