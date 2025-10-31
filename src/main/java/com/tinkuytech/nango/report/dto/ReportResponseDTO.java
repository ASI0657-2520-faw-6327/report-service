// dto/ReportResponseDTO.java
package com.tinkuytech.nango.report.dto;

import java.time.LocalDateTime;

public record ReportResponseDTO(Long reportId, Long rideId, LocalDateTime reportDate, String reason) {}
