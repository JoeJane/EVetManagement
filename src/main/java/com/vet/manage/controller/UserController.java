package com.vet.manage.controller;

import com.vet.manage.model.dto.SearchTerm;
import com.vet.manage.model.dto.Status;
import com.vet.manage.model.entity.*;
import com.vet.manage.service.AppointmentService;
import com.vet.manage.service.OwnerService;
import com.vet.manage.service.ReportService;
import com.vet.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static com.vet.manage.util.MasterData.getCountries;
import static com.vet.manage.util.MasterData.getProvinces;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String veterinarianHome(Model model, Principal principal) {
        User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        Owner owner = ownerService.findById(currentUser.getUserId()).orElse(new Owner());

        Appointment lastCompletedAppointment = null;

        for(Appointment appointment: owner.getPet().getAppointments()){
            if(appointment.getStatus() == Status.COMPLETED){
                lastCompletedAppointment = appointment;
                break;
            }
        }

        model.addAttribute("userForm", owner);
        model.addAttribute("lastappointment", lastCompletedAppointment);
        model.addAttribute("bookings", owner.getPet().getAppointments());

        return "/user/viewForm";
    }

    @GetMapping("/view-prescription/{appointmentId}")
    public String viewPetPrescription(@PathVariable("appointmentId") Integer appointmentId, Model model) {

        Appointment appointment = appointmentService.findById(appointmentId).orElseThrow();


        model.addAttribute("userForm", appointment.getPet().getOwner());
        model.addAttribute("pet", appointment.getPet());
        model.addAttribute("appointment", appointment);
        model.addAttribute("diagnoses", appointment.getDiagnosis());
        model.addAttribute("prescriptionList", appointment.getDiagnosis().getPrescription());

        return "/user/viewPrescription";
    }

    @GetMapping("/downloadFile/{reportId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("reportId") Integer reportId) {
        Report report = reportService.getFile(reportId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(report.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + report.getFileName() + "\"")
                .body(new ByteArrayResource(report.getData()));
    }

    /**
     * View user profile
     * @param model
     * @param principal
     * @return lab assistant view profile page
     */
    @GetMapping("/profile")
    public String viewProfile(Model model, Principal principal) {
        User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        User user = userService.findById(currentUser.getUserId()).orElse(new User());

        model.addAttribute("userForm", user);

        return "/user/viewProfile";
    }

    /**
     * Save users to database
     * @param user user pojo
     * @param bindingResult
     * @param model
     * @param redirectAttributes
     * @param principal
     * @return lab assistant profile page
     */
    @PostMapping("/save")
    public String addUser(@ModelAttribute("userForm") @Valid User user, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes, Principal principal) {
        Owner currentUser  = (Owner)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Optional<User> optionalOwner = userService.findById(currentUser.getUserId());

        if(!optionalOwner.isPresent()){
            bindingResult.rejectValue("username", "invalid.user");
        }

        if (bindingResult.hasErrors()) {
            populateDefaultCheckBoxesAndRadios(model);
            return "user/editProfile";
        }

        user.setUserId(optionalOwner.get().getUserId());

        redirectAttributes.addFlashAttribute("alert", "success");
        redirectAttributes.addFlashAttribute("msg", "User updated successfully!");

        userService.saveOrUpdate(user);

        return "redirect:/user/profile";
    }

    /**
     * Populate default values for checkbox and radios
     * @param model Model attribute, which is wired automatically by spring framework
     */
    private void populateDefaultCheckBoxesAndRadios(Model model) {
        model.addAttribute("provinces", getProvinces());
        model.addAttribute("countries", getCountries());
    }

    /**
     * Edit user form
     * @param model
     * @param principal
     * @return lab assistant editprofile page
     */
    @GetMapping("/edit")
    public String editUser(Model model, Principal principal) {
        User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        User user = userService.findById(currentUser.getUserId()).orElse(new User());

        model.addAttribute("userForm", user);
        populateDefaultCheckBoxesAndRadios(model);

        return "/user/editProfile";
    }

    /**
     * Reset user password
     * @param model
     * @param principal
     * @return lab assistant resetpassword page
     */
    @GetMapping("/resetPassword")
    public String resetPassword(Model model, Principal principal) {
        User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        User user = userService.findById(currentUser.getUserId()).get();
        user.setPassword("");
        model.addAttribute("userForm", user);

        return "/user/resetPassword";
    }


    /**
     * Reset user password
     * @param user user pojo
     * @param bindingResult
     * @param model
     * @param principal
     * @return user home page
     */
    @PostMapping("/resetPassword")
    public String resetPassword(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model, Principal principal) {
        if(user.getPassword() == null || user.getPassword().equals("")){
            bindingResult.rejectValue("password", "required.password");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword", "notmatch.confirmPassword");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("userForm", user);
            return "/user/resetPassword";
        }

        User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        currentUser = userService.findById(currentUser.getUserId()).get();

        currentUser.setPassword(user.getPassword());

        userService.updatePassword(currentUser);

        return "redirect:/user/home";
    }
}
