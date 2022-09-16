package com.learntocode.springbootdemotutorial.repository;

import com.learntocode.springbootdemotutorial.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Optional<DepartmentEntity> findByDepartmentNameIgnoreCase(String departmentName);
}
