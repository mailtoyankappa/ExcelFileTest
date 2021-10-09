package com.bezkoder.spring.files.excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.files.excel.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
