package com.learntocode.springbootdemotutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDTO {
    private Long departmentId;

    private String departmentName;

    private String departmentCode;

    private String departmentAddress;
}
