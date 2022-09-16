package com.learntocode.springbootdemotutorial.service;

import com.learntocode.springbootdemotutorial.dto.DepartmentAllResponseDTO;
import com.learntocode.springbootdemotutorial.dto.DepartmentRequestDTO;
import com.learntocode.springbootdemotutorial.dto.DepartmentResponseDTO;
import com.learntocode.springbootdemotutorial.dto.DepartmentUpdateRequestDTO;
import com.learntocode.springbootdemotutorial.exception.DepartmentNotFoundException;

public interface DepartmentService {
    DepartmentResponseDTO saveDepartment(DepartmentRequestDTO departmentRequestDTO);
    DepartmentAllResponseDTO getDepartments();
    DepartmentResponseDTO getDepartmentById(Long departmentId) throws DepartmentNotFoundException;
    void deleteDepartmentById(Long departmentId) throws DepartmentNotFoundException;
    DepartmentResponseDTO updateDepartment(DepartmentUpdateRequestDTO requestDTO) throws DepartmentNotFoundException;
    DepartmentResponseDTO getDepartmentByName(String departmentName) throws DepartmentNotFoundException;
}
