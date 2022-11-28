package com.kodlamaio.bootcampproject.api.controllers;

import com.kodlamaio.bootcampproject.business.abstracts.BlackListService;
import com.kodlamaio.bootcampproject.business.requests.blacklists.CreateBlackListRequest;
import com.kodlamaio.bootcampproject.business.requests.blacklists.UpdateBlackListRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/blacklists")
@AllArgsConstructor
public class BlackListsController {
    private BlackListService blackListService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(blackListService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(blackListService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid CreateBlackListRequest createBlackListRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(blackListService.add(createBlackListRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody @Valid UpdateBlackListRequest updateBlackListRequest){
        updateBlackListRequest.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(blackListService.update(updateBlackListRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(blackListService.delete(id));
    }
}
