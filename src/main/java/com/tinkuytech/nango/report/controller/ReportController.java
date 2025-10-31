package com.tinkuytech.nango.report.controller;

import com.tinkuytech.nango.report.dto.ReportRequestDTO;
import com.tinkuytech.nango.report.dto.ReportResponseDTO;
import com.tinkuytech.nango.report.exception.ReportNotFoundException;
import com.tinkuytech.nango.report.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReportResponseDTO createReport(@RequestBody ReportRequestDTO request) {
        try {
            return reportService.createReport(request);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ReportResponseDTO getReportById(@PathVariable Long id) {
        return reportService.getReportById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }
}
