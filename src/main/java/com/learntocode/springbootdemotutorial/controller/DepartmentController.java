package com.learntocode.springbootdemotutorial.controller;

import com.learntocode.springbootdemotutorial.dto.*;
import com.learntocode.springbootdemotutorial.entity.DepartmentEntity;
import com.learntocode.springbootdemotutorial.exception.DepartmentNotFoundException;
import com.learntocode.springbootdemotutorial.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/")
    public DepartmentResponseDTO saveDepartment(@Valid @RequestBody DepartmentRequestDTO departmentRequestDTO){
        LOGGER.info("Inside saveDepartment");
        DepartmentResponseDTO responseDTO = departmentService.saveDepartment(departmentRequestDTO);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Department Details Saved");
        return responseDTO;
    }

    @GetMapping("/")
    public DepartmentAllResponseDTO getDepartments(){
        LOGGER.info("Inside getDepartments");
        DepartmentAllResponseDTO responseDTO = departmentService.getDepartments();
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Department Details");
        return responseDTO;
    }

    @GetMapping("/{id}")
    public DepartmentResponseDTO getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        LOGGER.info("Inside getDepartmentById");
        DepartmentResponseDTO responseDTO = departmentService.getDepartmentById(departmentId);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Department Found");
        return responseDTO;
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException{
        LOGGER.info("Inside deleteDepartmentById");
        departmentService.deleteDepartmentById(departmentId);
        BaseResponse response = new BaseResponse();
        response.setStatus(HttpStatus.OK);
        response.setMessage("Department Deleted Successfully");
        return response;
    }

    @GetMapping("/name/{name}")
    public DepartmentResponseDTO getDepartmentByName(@PathVariable("name") String departmentName) throws DepartmentNotFoundException{
        LOGGER.info("Inside getDepartmentByName");
        DepartmentResponseDTO responseDTO = departmentService.getDepartmentByName(departmentName);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Department Found");
        return responseDTO;
    }

    @PatchMapping("/")
    public DepartmentResponseDTO updateDepartment(@RequestBody DepartmentUpdateRequestDTO requestDTO) throws DepartmentNotFoundException {
        LOGGER.info("Inside updateDepartment");
        DepartmentResponseDTO responseDTO = departmentService.updateDepartment(requestDTO);
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Department Details Updated");
        return responseDTO;
    }
}
