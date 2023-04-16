package com.vet.manage.service.impl;

import com.vet.manage.exception.FileStorageException;
import com.vet.manage.model.dto.Status;
import com.vet.manage.model.entity.*;
import com.vet.manage.repository.AppointmentRepository;
import com.vet.manage.service.AppointmentService;
import com.vet.manage.service.PetService;
import com.vet.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * ServiceImpl for Appointment
 * @author Jane Aarthy Joseph
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    /**
     * find latest Appointment by pet id
     * @param petID pet id
     * @return Appointment
     */
    public Optional<Appointment> findLatestAppointment(Integer petID){
        return appointmentRepository.findLatestAppointment(petID);
    }

    /**
     * find appointment by id
     * @param id
     * @return
     */
    @Override
    public Optional<Appointment> findById(Integer id) {
        return appointmentRepository.findById(id);
    }

    /**
     * change appointment status
     * @param id appointment id
     * @param status status
     */
    @Override
    public void changeStatus(Integer id, Status status){
        Pet pet = petService.findById(id).orElseThrow();
        Appointment appointment = pet.getLatestAppointment();
        if(appointment!=null)
            appointment.setStatus(status);
        appointmentRepository.save(appointment);
    }

    /**
     * change or update appiontment
     * @param appointment
     */
    @Override
    public void saveOrUpdate(Appointment appointment){
        Integer appointmentId = appointment.getId();

        Receptionist submittedBy = (Receptionist)userService.findById(appointment.getReceptionist().getUserId()).orElseThrow();
        appointment.setReceptionist(submittedBy);

        Optional<Appointment> appointmentData = appointmentId == null ? Optional.empty() : findById(appointmentId);
        appointment.setStatus(Status.NEW);
        if (appointmentData.isPresent()) {
            Appointment appointmentTemp = appointmentData.get();

            appointmentTemp.setAppointmentDate(appointment.getAppointmentDate());
            appointmentTemp.setAppointmentTime(appointment.getAppointmentTime());
            appointmentTemp.setReason(appointment.getReason());
            appointmentTemp.setVeterinarian(appointment.getVeterinarian());

            appointmentRepository.save(appointmentTemp);
        } else {
            Pet pet = petService.findById(appointment.getPet().getId()).orElseThrow();
            appointment.setPet(pet);
            appointmentRepository.save(appointment);
        }
    }


    /**
     * save report
     * @param file report
     * @param appointmentId appointment id
     * @param labAssistantID lab assistant id
     * @return return appointment
     */
    public Appointment storeFileByAppointment(MultipartFile file, Integer appointmentId, Integer labAssistantID) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        LabAssistant labAssistant = (LabAssistant) userService.findById(labAssistantID).orElseThrow();

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Report existingReport = appointment.getReport();
            if(existingReport == null) {
                existingReport = new Report();
                existingReport.setPet(appointment.getPet());
                existingReport.setAppointment(appointment);

            }
            existingReport.setFileName(fileName);
            existingReport.setFileType(file.getContentType());
            existingReport.setData(file.getBytes());

            existingReport.setLabAssistant(labAssistant);

            appointment.setReport(existingReport);

            return appointmentRepository.save(appointment);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Appointment saveDiagnosisAndPrescription(Diagnosis diagnosis, Integer appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        appointment.setStatus(Status.COMPLETED);
        appointment.setDiagnosis(diagnosis);
        diagnosis.setAppointment(appointment);
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> findAppointmentByPetIdAndVetId(Integer petId, Integer userId){
        return appointmentRepository.findAppointmentByPetIdAndVetId(petId, userId, Status.NEW);
    }
}
