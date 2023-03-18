package com.vet.manage.service.impl;

import com.vet.manage.exception.FileStorageException;
import com.vet.manage.model.entity.*;
import com.vet.manage.repository.ReportRepository;
import com.vet.manage.service.AppointmentService;
import com.vet.manage.service.PetService;
import com.vet.manage.service.ReportService;
import com.vet.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * ServiceImpl for ReportService
 * @author Sinthuvarsini
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    PetService petService;

    @Autowired
    UserService userService;


    /**
     * save report file for a given appointment
     * @param file
     * @param appointmentId
     * @param labAssistantID
     * @return
     */
    public Report storeFileByAppointment(MultipartFile file, Integer appointmentId, Integer labAssistantID) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Appointment appointment = appointmentService.findById(appointmentId).orElseThrow();
        LabAssistant labAssistant = (LabAssistant) userService.findById(labAssistantID).orElseThrow();

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Report report = new Report(file.getBytes(), fileName, file.getContentType());
            report.setLabAssistant(labAssistant);
            report.setPet(appointment.getPet());

            appointment.setReport(report);

            return reportRepository.save(report);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }


    /**
     * get file by file id
     * @param fileId
     * @return
     */
    public Report getFile(Integer fileId) {
        return reportRepository.findById(fileId)
                .orElseThrow(() -> new FileStorageException("File not found with id " + fileId));
    }
}
