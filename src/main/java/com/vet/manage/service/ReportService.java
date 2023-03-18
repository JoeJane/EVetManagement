package com.vet.manage.service;

import com.vet.manage.model.entity.Report;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service for Report
 * @author Josef
 */
public interface ReportService {
    Report storeFileByAppointment(MultipartFile file, Integer appointmentId, Integer labAssistantID);

    public Report getFile(Integer fileId);
}
