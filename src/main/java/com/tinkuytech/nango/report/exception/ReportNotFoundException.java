package com.tinkuytech.nango.report.exception;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(Long id) {
        super("❌ Report no encontrado con ID: " + id);
    }
}
