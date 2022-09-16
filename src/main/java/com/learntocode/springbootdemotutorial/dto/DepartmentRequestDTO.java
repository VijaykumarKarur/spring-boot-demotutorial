package com.learntocode.springbootdemotutorial.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentRequestDTO {
    @NotBlank(message = "Department Name is Mandatory")
    private String departmentName;

    @NotBlank(message = "Department Code is Mandatory")
    @Size(min = 3, max = 3, message = "Department Code should be of length 3")
    private String departmentCode;

    @NotBlank(message = "Department Address is Mandatory")
    private String departmentAddress;
}
