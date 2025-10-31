package com.tinkuytech.nango.report.domain.aggregates;

import com.tinkuytech.nango.report.model.Report;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    void newReport_shouldHoldGivenValues_andSetCreatedAt() {
        Report r = new Report();
        r.setRideId(123L);
        r.setReason("Conductor llegó tarde");

        assertNull(r.getId(), "id debe ser null antes de persistir");
        assertEquals(123L, r.getRideId());
        assertEquals("Conductor llegó tarde", r.getReason());
        assertNotNull(r.getCreatedAt(), "createdAt debe inicializarse por defecto");
        assertTrue(r.getCreatedAt().isBefore(LocalDateTime.now().plusSeconds(2)));
    }

    @Test
    void setters_shouldUpdateFields() {
        Report r = new Report();
        r.setRideId(1L);
        r.setReason("A");

        r.setRideId(2L);
        r.setReason("B");

        assertEquals(2L, r.getRideId());
        assertEquals("B", r.getReason());
    }

    @Test
    void createdAt_canBeOverridden_ifNeeded() {
        Report r = new Report();
        LocalDateTime custom = LocalDateTime.of(2025, 1, 1, 12, 0);
        r.setCreatedAt(custom);
        assertEquals(custom, r.getCreatedAt());
    }
}
