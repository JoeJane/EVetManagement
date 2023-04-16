package com.vet.manage.service;

import com.vet.manage.model.entity.Appointment;
import com.vet.manage.model.entity.LabAssistant;
import com.vet.manage.model.entity.Report;
import com.vet.manage.repository.ReportRepository;
import com.vet.manage.service.impl.ReportServiceImpl;
import com.vet.manage.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {

    @InjectMocks
    private ReportServiceImpl reportService;

    @Mock
    private ReportRepository reportRepository;

    private Report testReport;

    private Appointment testAppointment;
    private LabAssistant labAssistant;

    @Mock
    AppointmentService appointmentService;

    @Mock
    UserService userService;


    @BeforeEach
    public void setUp() {
        testReport = new Report();
        testReport.setId(1);
        testReport.setFileName("test_report.pdf");
        testReport.setFileType("application/pdf");
        testReport.setData(new byte[]{1, 2, 3, 4});

        testAppointment = new Appointment();
        labAssistant = new LabAssistant();
    }

    @Test
    public void testStoreFileByAppointment() throws IOException {
        MultipartFile file = new MockMultipartFile("file", "test_report.pdf", "application/pdf", new byte[]{1, 2, 3, 4});
        when(reportRepository.save(any(Report.class))).thenReturn(testReport);
        when(appointmentService.findById(any(Integer.class))).thenReturn(Optional.ofNullable(testAppointment));
        when(userService.findById(any(Integer.class))).thenReturn(Optional.ofNullable(labAssistant));


        Report result = reportService.storeFileByAppointment(file, 1, 1);

        assertNotNull(result);
        assertEquals(testReport.getFileName(), result.getFileName());
        assertEquals(testReport.getFileType(), result.getFileType());
    }

    @Test
    public void testGetFile() {
        when(reportRepository.findById(any(Integer.class))).thenReturn(Optional.of(testReport));

        Report result = reportService.getFile(1);

        assertNotNull(result);
        assertEquals(testReport.getFileName(), result.getFileName());
        assertEquals(testReport.getFileType(), result.getFileType());
    }
}