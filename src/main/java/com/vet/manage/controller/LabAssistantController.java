package com.vet.manage.controller;

import com.vet.manage.model.dto.SearchTerm;
import com.vet.manage.model.entity.*;
import com.vet.manage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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

import org.springframework.core.io.Resource;

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
@RequestMapping("labassistant")
public class LabAssistantController {

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
	public String labassistantHome(Model model, Principal principal) {
		List<Pet> pets = petService.findAll();

		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		model.addAttribute("searchterm", new SearchTerm());
		model.addAttribute("bulkAction", new SearchTerm());
		model.addAttribute("pets", pets);
		model.addAttribute("message", "Welcome " + currentUser.getFullName());

		return "/labassistant/home";
	}


	/**
	 * Search users based on @SearchTerm
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param searchTerm Search parameters which is passed by frontend
	 * @return admin home page with search result
	 */
	@PostMapping("/search")
	public String search(Model model, @ModelAttribute("searchterm") SearchTerm searchTerm) {

		List<Pet> pets = petService.findPetBy(searchTerm.getValue());

		model.addAttribute("searchterm", searchTerm);
		model.addAttribute("bulkAction", searchTerm);
		model.addAttribute("pets", pets);

		return "/labassistant/home";
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

		model.addAttribute("userForm", owner);
		model.addAttribute("bookings", owner.getPet().getAppointments());

		return "/labassistant/viewForm";
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
	@GetMapping("/uploadreport/{appointmentId}")
	public String bookAppointment(@PathVariable("appointmentId") Integer appointmentId, Model model) {
		Appointment appointment = appointmentService.findById(appointmentId).orElseThrow();

		model.addAttribute("appointment", appointment);
		model.addAttribute("userForm", appointment.getPet().getOwner());

		return "/labassistant/registerForm";
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
	@PostMapping("/uploadreport/{appointmentId}")
	@ResponseBody
	public String uploadFile(@PathVariable("appointmentId") Integer appointmentId, @RequestParam("file") MultipartFile file, Model model, final RedirectAttributes redirectAttributes, Principal principal) {
		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		Report report = appointmentService.storeFileByAppointment(file, appointmentId, currentUser.getUserId()).getReport();
		model.addAttribute("report", report);
		return report.getFileName();
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

		return "/labassistant/editProfile";
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

		return "/labassistant/viewProfile";
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
		LabAssistant currentUser  = (LabAssistant)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		Optional<User> optionalLabAssistant = userService.findById(currentUser.getUserId());

		if(!optionalLabAssistant.isPresent()){
			bindingResult.rejectValue("username", "invalid.user");
		}

		if (bindingResult.hasErrors()) {
			populateDefaultCheckBoxesAndRadios(model);
			return "labassistant/editProfile";
		}

		user.setUserId(optionalLabAssistant.get().getUserId());

		redirectAttributes.addFlashAttribute("alert", "success");
		redirectAttributes.addFlashAttribute("msg", "User updated successfully!");

		userService.saveOrUpdate(user);

		return "redirect:/labassistant/profile";
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

		return "/labassistant/resetPassword";
	}


	/**
	 * Reset user password
	 * @param user user pojo
	 * @param bindingResult
	 * @param model
	 * @param principal
	 * @return labassistant home page
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
			return "/labassistant/resetPassword";
		}

		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		currentUser = userService.findById(currentUser.getUserId()).get();

		currentUser.setPassword(user.getPassword());

		userService.updatePassword(currentUser);

		return "redirect:/labassistant/home";
	}

}
