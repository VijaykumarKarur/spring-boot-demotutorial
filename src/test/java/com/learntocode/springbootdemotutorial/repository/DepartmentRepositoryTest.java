package com.learntocode.springbootdemotutorial.repository;

import com.learntocode.springbootdemotutorial.dto.DepartmentResponseDTO;
import com.learntocode.springbootdemotutorial.entity.DepartmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        DepartmentEntity departmentEntity = DepartmentEntity
                .builder()
                .departmentName("Instrumentation Engineering")
                .departmentCode("IE")
                .departmentAddress("Bengaluru")
                .build();
        entityManager.persist(departmentEntity);
    }

    @Test
    @DisplayName("findByIdValidCase")
    public void whenValidDepartmentIdThenDepartmentFound(){
        DepartmentEntity departmentEntity = departmentRepository.findById(1L).get();
        assertEquals("Instrumentation Engineering", departmentEntity.getDepartmentName());
    }
}