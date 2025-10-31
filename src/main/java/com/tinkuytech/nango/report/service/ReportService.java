// service/ReportService.java
package com.tinkuytech.nango.report.service;

import com.tinkuytech.nango.report.dto.ReportRequestDTO;
import com.tinkuytech.nango.report.dto.ReportResponseDTO;

import java.util.Optional;

public interface ReportService {
    ReportResponseDTO createReport(ReportRequestDTO request);
    Optional<ReportResponseDTO> getReportById(Long id);
}
