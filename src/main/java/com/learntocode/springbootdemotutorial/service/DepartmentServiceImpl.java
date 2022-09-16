package com.learntocode.springbootdemotutorial.service;

import com.learntocode.springbootdemotutorial.dto.*;
import com.learntocode.springbootdemotutorial.entity.DepartmentEntity;
import com.learntocode.springbootdemotutorial.exception.DepartmentNotFoundException;
import com.learntocode.springbootdemotutorial.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Override
    public DepartmentResponseDTO saveDepartment(DepartmentRequestDTO departmentRequestDTO) {
        LOGGER.info("Inside saveDepartment");
        DepartmentEntity department = DepartmentEntity
                .builder()
                .departmentName(departmentRequestDTO.getDepartmentName())
                .departmentAddress(departmentRequestDTO.getDepartmentAddress())
                .departmentCode(departmentRequestDTO.getDepartmentCode())
                .build();
        department = departmentRepository.save(department);
        DepartmentResponseDTO responseDTO = DepartmentResponseDTO
                .builder()
                .departmentId(department.getDepartmentId())
                .departmentAddress((department.getDepartmentAddress()))
                .departmentCode(department.getDepartmentCode())
                .departmentName((department.getDepartmentName()))
                .build();
        return responseDTO;
    }

    @Override
    public DepartmentAllResponseDTO getDepartments() {
        LOGGER.info("Inside getDepartments");
        List<DepartmentEntity> departmentList;
        departmentList = departmentRepository.findAll();
        DepartmentAllResponseDTO responseDTO = new DepartmentAllResponseDTO();
        for(int index = 0; index < departmentList.size(); index++){
            DepartmentEntity department = departmentList.get(index);
            DepartmentDTO departmentDTO = DepartmentDTO
                    .builder()
                    .departmentId(department.getDepartmentId())
                    .departmentName(department.getDepartmentName())
                    .departmentCode(department.getDepartmentCode())
                    .departmentAddress(department.getDepartmentAddress())
                    .build();
            responseDTO.getDepartments().add(departmentDTO);
        }
        return responseDTO;
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        LOGGER.info("Inside getDepartmentById");
        Optional<DepartmentEntity> departmentOptional = departmentRepository.findById(departmentId);
        if(!departmentOptional.isPresent()){
            throw new DepartmentNotFoundException("Department : " + departmentId + " Not Found");
        }
        DepartmentEntity department = departmentOptional.get();
        DepartmentResponseDTO responseDTO = DepartmentResponseDTO
                .builder()
                .departmentId(department.getDepartmentId())
                .departmentAddress((department.getDepartmentAddress()))
                .departmentCode(department.getDepartmentCode())
                .departmentName((department.getDepartmentName()))
                .build();
        return responseDTO;
    }

    @Override
    public void deleteDepartmentById(Long departmentId) throws DepartmentNotFoundException{
        LOGGER.info("Inside deleteDepartmentById");
        Optional<DepartmentEntity> departmentOptional = departmentRepository.findById(departmentId);
        if(!departmentOptional.isPresent()){
            throw new DepartmentNotFoundException("Department : " + departmentId + " Not Found");
        }
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(DepartmentUpdateRequestDTO requestDTO) throws DepartmentNotFoundException {
        LOGGER.info("Inside updateDepartment");
        Optional<DepartmentEntity> departmentOptional = departmentRepository.findById(requestDTO.getDepartmentId());
        if(!departmentOptional.isPresent()){
            throw new DepartmentNotFoundException("Department : " + requestDTO.getDepartmentId() + " Not Found");
        }
        DepartmentEntity department = departmentOptional.get();
        if(Objects.nonNull(requestDTO.getDepartmentName()) &&
                !"".equalsIgnoreCase(requestDTO.getDepartmentName())){
            department.setDepartmentName(requestDTO.getDepartmentName());
        }

        if(Objects.nonNull(requestDTO.getDepartmentCode()) &&
                !"".equalsIgnoreCase(requestDTO.getDepartmentCode())){
            department.setDepartmentCode(requestDTO.getDepartmentCode());
        }

        if(Objects.nonNull(requestDTO.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(requestDTO.getDepartmentAddress())){
            department.setDepartmentAddress(requestDTO.getDepartmentAddress());
        }

        department = departmentRepository.save(department);
        DepartmentResponseDTO responseDTO = DepartmentResponseDTO
                .builder()
                .departmentId(department.getDepartmentId())
                .departmentAddress((department.getDepartmentAddress()))
                .departmentCode(department.getDepartmentCode())
                .departmentName((department.getDepartmentName()))
                .build();
        return responseDTO;

    }

    @Override
    public DepartmentResponseDTO getDepartmentByName(String departmentName) throws DepartmentNotFoundException{
        LOGGER.info("Inside getDepartmentByName");
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
        if(!departmentEntity.isPresent()){
            throw new DepartmentNotFoundException("Department : " + departmentName + " not found");
        }
        DepartmentEntity department = departmentEntity.get();
        DepartmentResponseDTO responseDTO = DepartmentResponseDTO
                .builder()
                .departmentId(department.getDepartmentId())
                .departmentAddress((department.getDepartmentAddress()))
                .departmentCode(department.getDepartmentCode())
                .departmentName((department.getDepartmentName()))
                .build();
        return responseDTO;
    }
}
