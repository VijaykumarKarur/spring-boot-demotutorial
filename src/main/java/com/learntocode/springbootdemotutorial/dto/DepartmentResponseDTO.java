package com.learntocode.springbootdemotutorial.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentResponseDTO extends BaseResponse{
    private Long departmentId;

    private String departmentName;

    private String departmentCode;

    private String departmentAddress;
}
