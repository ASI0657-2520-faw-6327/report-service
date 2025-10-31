package com.tinkuytech.nango.report.repository;

import com.tinkuytech.nango.report.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {}
