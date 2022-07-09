package com.example.enigmalibrary.controllers;

import com.example.enigmalibrary.constant.ApiURLConstant;

import com.example.enigmalibrary.constant.ResponseMessage;
import com.example.enigmalibrary.dto.ReturnRecordSearchDTO;
import com.example.enigmalibrary.entities.ReturnRecord;
import com.example.enigmalibrary.services.ReturnRecordService;
import com.example.enigmalibrary.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURLConstant.RECORD)
public class ReturnRecordController {

    @Autowired
    ReturnRecordService returnRecordService;

    // Method For Insert new data Return
    @PostMapping("/return")
    public ResponseEntity<Response<ReturnRecord>> saveReturnRecord(@RequestBody ReturnRecord returnRecord){

        Response<ReturnRecord> response =new Response<>();

        String message = String.format(ResponseMessage.DATA_INSERT, "Book Return Record");
        response.setMessage(message);

        response.setData(returnRecordService.saveReturnRecord(returnRecord));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // Method For Update Data Return
    @PutMapping("/return")
    public ResponseEntity<Response<ReturnRecord>> updateReturnRecord(@RequestBody ReturnRecord returnRecord){
        Response<ReturnRecord> response =new Response<>();

        String message = String.format(ResponseMessage.DATA_UPDATE, "Book Return Record", returnRecord.getId());
        response.setMessage(message);

        response.setData(returnRecordService.saveReturnRecord(returnRecord));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // Method For Get return By id
    @GetMapping("/return/{id}")
    public ReturnRecord getReturnRecordById(@PathVariable String id){return returnRecordService.getReturnRecordById(id);}

    // Method For Get All Return Per Page
    @GetMapping("/return")
    public Page<ReturnRecord> getBookPerPage(
            @RequestBody ReturnRecordSearchDTO returnRecordSearchDTO,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer sizePerPage,
            Sort sort
    ){

        Pageable pageable = PageRequest.of(page,sizePerPage,sort);

        return returnRecordService.getReturnRecordPerPage(pageable,returnRecordSearchDTO);
    }
}

