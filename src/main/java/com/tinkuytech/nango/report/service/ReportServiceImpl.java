// service/ReportServiceImpl.java
package com.tinkuytech.nango.report.service;

import com.tinkuytech.nango.report.dto.ReportRequestDTO;
import com.tinkuytech.nango.report.dto.ReportResponseDTO;
import com.tinkuytech.nango.report.exception.ReportNotFoundException;
import com.tinkuytech.nango.report.model.Report;
import com.tinkuytech.nango.report.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public ReportResponseDTO createReport(ReportRequestDTO request) {
        if (request.rideId() == null || request.reason() == null || request.reason().isBlank()) {
            throw new IllegalArgumentException("rideId y reason son obligatorios");
        }

        Report report = new Report();
        report.setRideId(request.rideId());
        report.setReason(request.reason());

        Report saved = reportRepository.save(report);

        return new ReportResponseDTO(
                saved.getId(),
                saved.getRideId(),
                saved.getCreatedAt(),
                saved.getReason()
        );
    }

    @Override
    public Optional<ReportResponseDTO> getReportById(Long id) {
        return reportRepository.findById(id)
                .map(r -> new ReportResponseDTO(
                        r.getId(),
                        r.getRideId(),
                        r.getCreatedAt(),
                        r.getReason()
                ));
    }
}
