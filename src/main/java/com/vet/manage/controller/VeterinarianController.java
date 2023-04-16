package com.vet.manage.controller;

import com.vet.manage.model.dto.SearchTerm;
import com.vet.manage.model.dto.Status;
import com.vet.manage.model.entity.*;
import com.vet.manage.service.*;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.vet.manage.util.MasterData.getCountries;
import static com.vet.manage.util.MasterData.getProvinces;


/**
 * Controller for Lab Assistant
 */
@Controller
@RequestMapping("veterinarian")
public class VeterinarianController {

	@Autowired
	private OwnerService ownerService;

	@Autowired
	private PetService petService;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private ClinicService clinicService;

	@Autowired
	private ReportService reportService;


	/**
	 * Home page for admin user
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param principal Principal attribute, which is wired automatically by spring framework
	 * @return admin home page
	 */
	@GetMapping("/home")
	public String veterinarianHome(Model model, Principal principal) {
		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		List<Pet> pets = petService.findPetByVeterinarian(currentUser.getUserId());


		model.addAttribute("searchterm", new SearchTerm());
		model.addAttribute("bulkAction", new SearchTerm());
		model.addAttribute("pets", pets);
		model.addAttribute("vetId", currentUser.getUserId());
		model.addAttribute("message", "Welcome " + currentUser.getFullName());

		return "/veterinarian/home";
	}


	/**
	 * Search users based on @SearchTerm
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param searchTerm Search parameters which is passed by frontend
	 * @return admin home page with search result
	 */
	@PostMapping("/search")
	public String search(Model model, @ModelAttribute("searchterm") SearchTerm searchTerm, Principal principal) {
		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		List<Pet> pets = petService.findPetByVeterinarian(searchTerm.getValue(), currentUser.getUserId());

		model.addAttribute("searchterm", searchTerm);
		model.addAttribute("bulkAction", searchTerm);
		model.addAttribute("pets", pets);

		return "/veterinarian/home";
	}


	/**
	 * View user profile based on User ID
	 * @param petOwnerId Target Pet Owner ID
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @return User profile view page
	 */
	@GetMapping("/view/{id}")
	public String viewPetOwner(@PathVariable("id") Integer petOwnerId, Model model) {
		Owner owner = ownerService.findById(petOwnerId).orElse(new Owner());

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

		return "/veterinarian/viewForm";
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
	 * Populate default values for checkbox and radios
	 * @param model Model attribute, which is wired automatically by spring framework
	 */
	private void populateDefaultCheckBoxesAndRadios(Model model) {
		model.addAttribute("provinces", getProvinces());
		model.addAttribute("countries", getCountries());
	}

	/**
	 * Get upload form
	 * @param appointmentId Appointment ID
	 * @param model
	 * @return labassistnat register page
	 */
	@GetMapping("/writeprescription/{petId}")
	public String writeprescription(@PathVariable("petId") Integer petId, Model model, Principal principal) {
		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		Appointment currentAppointment = appointmentService.findAppointmentByPetIdAndVetId(petId, currentUser.getUserId()).orElseThrow();

		model.addAttribute("appointment", currentAppointment);
		model.addAttribute("diagnosis", new Diagnosis());
		model.addAttribute("userForm", currentAppointment!=null?currentAppointment.getPet().getOwner():new Pet());

		return "/veterinarian/registerForm";
	}


	/**
	 * Upload reports for a given appointment
	 * @param appointmentId Appointment ID
	 * @param file Report either jpeg or pdf
	 * @param model
	 * @param redirectAttributes
	 * @param principal
	 * @return returns file name
	 */
	@PostMapping("/savePrescription/{appointmentId}")
	public String uploadFile(@PathVariable("appointmentId") Integer appointmentId, @ModelAttribute("diagnosis") @Valid Diagnosis diagnosis, Model model, final RedirectAttributes redirectAttributes, Principal principal) {
		appointmentService.saveDiagnosisAndPrescription(diagnosis, appointmentId);
		return "redirect:/veterinarian/home";
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

		model.addAttribute("clinics", getClinics());
		model.addAttribute("userForm", user);
		populateDefaultCheckBoxesAndRadios(model);

		return "/veterinarian/editProfile";
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

		return "/veterinarian/viewProfile";
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
		Veterinarian currentUser  = (Veterinarian) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		Optional<User> optionalVeterinarian = userService.findById(currentUser.getUserId());

		if(!optionalVeterinarian.isPresent()){
			bindingResult.rejectValue("username", "invalid.user");
		}

		if (bindingResult.hasErrors()) {
			populateDefaultCheckBoxesAndRadios(model);
			return "veterinarian/editProfile";
		}

		user.setUserId(optionalVeterinarian.get().getUserId());

		redirectAttributes.addFlashAttribute("alert", "success");
		redirectAttributes.addFlashAttribute("msg", "User updated successfully!");

		userService.saveOrUpdate(user);

		return "redirect:/veterinarian/profile";
	}


	/**
	 * Load all the clinics
	 * @return list of clinics
	 */
	private List<Clinic> getClinics(){
		List<Clinic> clinics = clinicService.findAll();
		Iterator<Clinic> iterator = clinics.listIterator();

		while(iterator.hasNext()){
			Clinic clinic = iterator.next();
			if(clinic.getId()==1){
				iterator.remove();
				break;
			}
		}

		return clinics;
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

		return "/veterinarian/resetPassword";
	}


	/**
	 * Reset user password
	 * @param user user pojo
	 * @param bindingResult
	 * @param model
	 * @param principal
	 * @return veterinarian home page
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
			return "/veterinarian/resetPassword";
		}

		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		currentUser = userService.findById(currentUser.getUserId()).get();

		currentUser.setPassword(user.getPassword());

		userService.updatePassword(currentUser);

		return "redirect:/veterinarian/home";
	}


	@GetMapping("/view-prescription/{appointmentId}")
	public String viewPetPrescription(@PathVariable("appointmentId") Integer appointmentId, Model model) {

		Appointment appointment = appointmentService.findById(appointmentId).orElseThrow();


		model.addAttribute("userForm", appointment.getPet().getOwner());
		model.addAttribute("pet", appointment.getPet());
		model.addAttribute("appointment", appointment);
		model.addAttribute("diagnoses", appointment.getDiagnosis());
		model.addAttribute("prescriptionList", appointment.getDiagnosis().getPrescription());

		return "/veterinarian/viewPrescription";
	}

}
