package com.learntocode.springbootdemotutorial.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentUpdateRequestDTO {
    @NotBlank(message = "Department Id is Mandatory")
    private Long departmentId;

    private String departmentName;

    private String departmentCode;

    private String departmentAddress;
}
