package com.learntocode.springbootdemotutorial.service;

import com.learntocode.springbootdemotutorial.dto.DepartmentAllResponseDTO;
import com.learntocode.springbootdemotutorial.dto.DepartmentRequestDTO;
import com.learntocode.springbootdemotutorial.dto.DepartmentResponseDTO;
import com.learntocode.springbootdemotutorial.dto.DepartmentUpdateRequestDTO;
import com.learntocode.springbootdemotutorial.entity.DepartmentEntity;
import com.learntocode.springbootdemotutorial.exception.DepartmentNotFoundException;
import com.learntocode.springbootdemotutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;
    private Optional<DepartmentEntity> mecDeptEntity;
    private DepartmentRequestDTO mecDepartmentRequestDTO;

    private DepartmentUpdateRequestDTO eeUpdateRequestDTO;
    private DepartmentEntity mecRequestEntity;
    private DepartmentEntity mecResponseEntity;
    private DepartmentEntity eeResponseEntity;
    private DepartmentEntity eeUpdateResponseEntity;
    private List<DepartmentEntity> deptEntityList;
    private Long departmentId = 1L;

    private Long eeDepartmentId = 2L;
    private Long departmentIdNotFound = 3L;
    private String departmentName = "mechanical engineering";
    private String departmentNameNotFound = "production engineering";

    @BeforeEach
    void setUp() {
        deptEntityList = new ArrayList<>();
         mecDeptEntity = Optional.ofNullable(DepartmentEntity
                 .builder()
                 .departmentId(1L)
                 .departmentName("Mechanical Engineering")
                 .departmentCode("MEC")
                 .departmentAddress("Bengaluru")
                 .build());
        mecDepartmentRequestDTO = DepartmentRequestDTO
                .builder()
                .departmentName("Mechanical Engineering")
                .departmentCode("MEC")
                .departmentAddress("Bengaluru")
                .build();
        mecRequestEntity = DepartmentEntity
                .builder()
                .departmentName("Mechanical Engineering")
                .departmentCode("MEC")
                .departmentAddress("Bengaluru")
                .build();
        mecResponseEntity = DepartmentEntity
                .builder()
                .departmentId(1L)
                .departmentName("Mechanical Engineering")
                .departmentCode("MEC")
                .departmentAddress("Bengaluru")
                .build();
        eeResponseEntity = DepartmentEntity
                .builder()
                .departmentId(2L)
                .departmentName("Electrical Engineering")
                .departmentCode("EEE")
                .departmentAddress("Bengaluru")
                .build();
        eeUpdateRequestDTO = DepartmentUpdateRequestDTO
                .builder()
                .departmentId(2L)
                .departmentCode("EEN")
                .build();
        eeUpdateResponseEntity = DepartmentEntity
                .builder()
                .departmentId(2L)
                .departmentName("Electrical Engineering")
                .departmentCode("EEN")
                .departmentAddress("Bengaluru")
                .build();
        deptEntityList.add(mecRequestEntity);
        deptEntityList.add(eeResponseEntity);
        Mockito.when(departmentRepository.save(mecRequestEntity)).thenReturn(mecResponseEntity);
        Mockito.when(departmentRepository.findAll()).thenReturn(deptEntityList);
        Mockito.when(departmentRepository.findById(departmentId)).thenReturn(mecDeptEntity);
        Mockito.when(departmentRepository.findById(eeDepartmentId)).thenReturn(Optional.of(eeResponseEntity));
        Mockito.when(departmentRepository.findById(departmentIdNotFound))
                .thenReturn(Optional.empty());
        Mockito.doNothing().when(departmentRepository).deleteById(departmentId);
        Mockito.doNothing().when(departmentRepository).deleteById(departmentIdNotFound);
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase(departmentName))
                .thenReturn(mecDeptEntity);
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase(departmentNameNotFound))
                        .thenReturn(Optional.empty());
        Mockito.when(departmentRepository.save(eeUpdateResponseEntity)).thenReturn(eeUpdateResponseEntity);
    }


    @Test
    @DisplayName("Save Department")
    void saveDepartment() {
        DepartmentResponseDTO mecResponseDTO = departmentService.saveDepartment(mecDepartmentRequestDTO);
        assertEquals(mecResponseEntity.getDepartmentId(), mecResponseDTO.getDepartmentId());
    }

    @Test
    @DisplayName("Get All Departments")
    void getDepartments() {
        DepartmentAllResponseDTO deptAllResponseDTO = departmentService.getDepartments();
        assertEquals(deptEntityList.size(), deptAllResponseDTO.getDepartments().size());
    }

    @Test
    @DisplayName("Get Department By Id")
    void getDepartmentById() throws DepartmentNotFoundException {
        DepartmentResponseDTO responseDTO = departmentService.getDepartmentById(departmentId);
        assertEquals(mecDeptEntity.get().getDepartmentName(), responseDTO.getDepartmentName());

        assertThrows(DepartmentNotFoundException.class,
                () -> {departmentService.deleteDepartmentById(departmentIdNotFound);});
    }

    @Test
    @DisplayName("Delete Department by Id")
    void deleteDepartmentById() throws DepartmentNotFoundException {
        assertDoesNotThrow(() -> {departmentService.deleteDepartmentById(departmentId);});
        assertThrows(DepartmentNotFoundException.class,
                () -> {departmentService.deleteDepartmentById(departmentIdNotFound);});
    }

    @Test
    void updateDepartment() throws DepartmentNotFoundException {
        DepartmentResponseDTO responseDTO = departmentService.updateDepartment(eeUpdateRequestDTO);
        assertEquals(eeUpdateResponseEntity.getDepartmentCode(), responseDTO.getDepartmentCode());
    }

    @Test
    @DisplayName("Get Department By Name")
    void getDepartmentByName() throws DepartmentNotFoundException {
        DepartmentResponseDTO responseDTO = departmentService.getDepartmentByName(departmentName);
        assertEquals(mecDeptEntity.get().getDepartmentId(), responseDTO.getDepartmentId());
        assertThrows(DepartmentNotFoundException.class,
                () -> {departmentService.getDepartmentByName(departmentNameNotFound);});
    }
}