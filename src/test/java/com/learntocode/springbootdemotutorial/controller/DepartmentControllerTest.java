package com.learntocode.springbootdemotutorial.controller;

import com.learntocode.springbootdemotutorial.dto.DepartmentRequestDTO;
import com.learntocode.springbootdemotutorial.dto.DepartmentResponseDTO;
import com.learntocode.springbootdemotutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private DepartmentResponseDTO departmentResponseDTO;

    @BeforeEach
    void setUp(){
        departmentResponseDTO = DepartmentResponseDTO
                .builder()
                .departmentId(1L)
                .departmentName("Production Engineering")
                .departmentCode("PE")
                .departmentAddress("Bengaluru")
                .build();
    }

    @Test
    @Disabled
    @DisplayName("SaveDepartmentValidCase")
    void saveDepartment() throws Exception {
        DepartmentRequestDTO requestDTO = DepartmentRequestDTO
                .builder()
                .departmentName("Production Engineering")
                .departmentCode("PE")
                .departmentAddress("Bengaluru")
                .build();
        Mockito.when(departmentService.saveDepartment(requestDTO)).thenReturn(departmentResponseDTO);

        mockMvc.perform(post("/departments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\":\"Production Engineering\",\n" +
                        "    \"departmentCode\":\"PE\",\n" +
                        "    \"departmentAddress\":\"Bangaluru\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GetDepartmentByIdValidCase")
    void getDepartmentById() throws Exception {
        Mockito.when(departmentService.getDepartmentById(1L)).thenReturn(departmentResponseDTO);

        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(departmentResponseDTO.getDepartmentName()));
    }
}