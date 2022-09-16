package com.learntocode.springbootdemotutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentAllResponseDTO extends BaseResponse{
    List<DepartmentDTO> departments = new ArrayList<DepartmentDTO>();
}
