package com.tinkuytech.nango.report.application.service;

import com.tinkuytech.nango.report.dto.ReportRequestDTO;
import com.tinkuytech.nango.report.dto.ReportResponseDTO;
import com.tinkuytech.nango.report.model.Report;
import com.tinkuytech.nango.report.repository.ReportRepository;
import com.tinkuytech.nango.report.service.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createReport_shouldPersist_andReturnDTO() {
        ReportRequestDTO req = new ReportRequestDTO(123L, "Conductor llegó tarde");

        when(reportRepository.save(any(Report.class))).thenAnswer(inv -> {
            Report r = inv.getArgument(0);
            r.setId(1L);
            return r;
        });

        ReportResponseDTO res = reportService.createReport(req);

        assertNotNull(res);
        assertEquals(1L, res.reportId());
        assertEquals(123L, res.rideId());
        assertEquals("Conductor llegó tarde", res.reason());
        assertNotNull(res.reportDate());
        verify(reportRepository, times(1)).save(any(Report.class));
    }

    @Test
    void createReport_shouldThrow_whenInvalidInput() {
        assertThrows(IllegalArgumentException.class,
                () -> reportService.createReport(new ReportRequestDTO(null, "motivo")));
        assertThrows(IllegalArgumentException.class,
                () -> reportService.createReport(new ReportRequestDTO(1L, " ")));
    }

    @Test
    void getReportById_shouldReturnDTO_whenFound() {
        Report r = new Report();
        r.setId(7L);
        r.setRideId(55L);
        r.setReason("Prueba");
        when(reportRepository.findById(7L)).thenReturn(Optional.of(r));

        Optional<ReportResponseDTO> out = reportService.getReportById(7L);

        assertTrue(out.isPresent());
        assertEquals(7L, out.get().reportId());
        assertEquals(55L, out.get().rideId());
        assertEquals("Prueba", out.get().reason());
    }

    @Test
    void getReportById_shouldReturnEmpty_whenNotFound() {
        when(reportRepository.findById(99L)).thenReturn(Optional.empty());
        assertTrue(reportService.getReportById(99L).isEmpty());
    }
}
