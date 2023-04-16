package com.vet.manage.controller;

import com.vet.manage.model.dto.SearchTerm;
import com.vet.manage.model.dto.SelectOption;
import com.vet.manage.model.dto.Status;
import com.vet.manage.model.entity.*;
import com.vet.manage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import java.util.*;

import static com.vet.manage.util.MasterData.getCountries;
import static com.vet.manage.util.MasterData.getProvinces;


/**
 * Controller for Receptionist
 * @author Josef
 */
@Controller
@RequestMapping("receptionist")
public class ReceptionistController {

	@Autowired
	private OwnerService ownerService;

	@Autowired
	private PetService petService;

	@Autowired
	private VeterinaryService veterinaryService;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private ClinicService clinicService;

	@Autowired
	private ReportService reportService;


	/**
	 * Home page for receptionist user
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param principal Principal attribute, which is wired automatically by spring framework
	 * @return receptionist home page
	 */
	@GetMapping("/home")
	public String receptionistHome(Model model, Principal principal) {
		List<Pet> pets = petService.findAll();

		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		model.addAttribute("searchterm", new SearchTerm());
		model.addAttribute("bulkAction", new SearchTerm());
		model.addAttribute("pets", pets);
		model.addAttribute("message", "Welcome " + currentUser.getFullName());

		return "/receptionist/home";
	}


	/**
	 * Search users based on @SearchTerm
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param searchTerm Search parameters which is passed by frontend
	 * @return receptionist home page with search result
	 */
	@PostMapping("/search")
	public String search(Model model, @ModelAttribute("searchterm") SearchTerm searchTerm) {

		List<Pet> pets = petService.findPetBy(searchTerm.getValue());

		model.addAttribute("searchterm", searchTerm);
		model.addAttribute("bulkAction", searchTerm);
		model.addAttribute("pets", pets);

		return "/receptionist/home";
	}


	/**
	 * User registration page
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @return User registration page to add
	 */
	@GetMapping("/petowner/add")
	public String addUser(Model model) {
		Owner owner = new Owner();
		owner.setPassword("****");
		model.addAttribute("userForm", owner);
		populateDefaultCheckBoxesAndRadios(model);
		return "/receptionist/petowner/registerForm";
	}


	/**
	 * User edit page based on userID
	 * @param id Target User ID
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @return User registration page to edit
	 */
	@GetMapping("/petowner/edit/{id}")
	public String editUser(@PathVariable("id") Integer id, Model model) {
		Owner user = ownerService.findById(id).orElse(new Owner());

		model.addAttribute("userForm", user);
		populateDefaultCheckBoxesAndRadios(model);

		return "/receptionist/petowner/registerForm";
	}


	/**
	 * Create or update user
	 * @param user User model
	 * @param bindingResult Binding Result
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param redirectAttributes Redirect Attributes
	 * @return User profile view page
	 */
	@PostMapping("/petowner/save")
	public String addPetOwner(@ModelAttribute("userForm") @Valid Owner user, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {

		if(user.getUserId()==null && ownerService.existsByFirstNameAndLastName(user.getFirstName(), user.getLastName())){
			bindingResult.rejectValue("firstName", "Duplicate.userForm.username");
		}
		if(user.getUserId()==null && ownerService.existsByEmail(user.getEmail())){
			bindingResult.rejectValue("email", "Duplicate.userForm.emailid");
		}
		if(user.isNew())
			user.setPassword("12345");


		if (bindingResult.hasErrors()) {
			populateDefaultCheckBoxesAndRadios(model);
			return "receptionist/petowner/registerForm";
		}

		redirectAttributes.addFlashAttribute("alert", "success");
		if (user.isNew()) {
			redirectAttributes.addFlashAttribute("msg", "User added successfully!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
		}

		ownerService.saveOrUpdate(user);

		return "redirect:/receptionist/petowner/view/" + user.getUserId();
	}


	/**
	 * View user profile based on User ID
	 * @param petOwnerId Target Pet Owner ID
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @return User profile view page
	 */
	@GetMapping("/petowner/view/{id}")
	public String viewPetOwner(@PathVariable("id") Integer petOwnerId, Model model) {
		Owner owner = ownerService.findById(petOwnerId).orElse(new Owner());

		model.addAttribute("userForm", owner);
		model.addAttribute("bookings", owner.getPet().getAppointments());

		return "/receptionist/petowner/viewForm";
	}


	/**
	 * REST method to activate user based on User ID
	 * @param petOwnerId Target Pet Owner ID
	 * @return Return Success or Fail
	 */
	@GetMapping(value="/petowner/activate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> activateUser(@PathVariable("id") Integer petOwnerId) {
		ownerService.changeStatusById(petOwnerId, false);
		return new ResponseEntity<>(petOwnerId, HttpStatus.OK);
	}


	/**
	 * Rest method to delete user based on User ID
	 * @param petOwnerId Target Pet Owner ID
	 * @return Return Success or Fail
	 */
	@GetMapping(value="/petowner/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> deleteUser(@PathVariable("id") Integer petOwnerId) {
		ownerService.changeStatusById(petOwnerId, true);
		return new ResponseEntity<>(petOwnerId, HttpStatus.OK);
	}


	/**
	 * Cancel Appointment
	 * @param petId
	 * @return
	 */
	@GetMapping(value="/appointment/cancel/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> cancelAppointment(@PathVariable("id") Integer petId) {
		appointmentService.changeStatus(petId, Status.CANCELED);
		return new ResponseEntity<>(petId, HttpStatus.OK);
	}


	/**
	 * Mark appointment as complete
	 * @param petId
	 * @return
	 */
	@GetMapping(value="/appointment/complete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> completeAppointment(@PathVariable("id") Integer petId) {
		appointmentService.changeStatus(petId, Status.COMPLETED);
		return new ResponseEntity<>(petId, HttpStatus.OK);
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
	 * Get book appointment page
	 * @param petId Pet ID
	 * @param model
	 * @return
	 */
	@GetMapping("/appointment/book/{petId}")
	public String bookAppointment(@PathVariable("petId") Integer petId, Model model) {
		Pet pet = petService.findById(petId).orElseThrow(()->new RuntimeException("Invalid pet id: " + petId));
		List<Appointment> appointments = pet.getAppointments();
		Appointment lastAppointment = appointments != null && appointments.size()>0 ? appointments.get(0) : new Appointment();

		model.addAttribute("bookingForm", new Appointment());
		model.addAttribute("pet", pet);
		model.addAttribute("owner", pet.getOwner());
		populateDefaultCheckBoxesAndRadios(model);
		return "/receptionist/bookingForm";
	}


	/**
	 * Book an appointment
	 * @param appointment Appointment pojo
	 * @param petId Pet ID
	 * @param bindingResult
	 * @param model
	 * @param redirectAttributes
	 * @param principal
	 * @return receptionist page
	 */
	@PostMapping("/appointment/book/save/{petId}")
	public String bookAppointment(@ModelAttribute("bookingForm") @Valid Appointment appointment, @PathVariable("petId") Integer petId, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes, Principal principal) {
		Appointment latestAppointment = appointmentService.findLatestAppointment(petId).orElse(null);
		// Pet pet = petService.findById(petId).orElseThrow();
		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		appointment.setReceptionist((Receptionist) currentUser);

		if(latestAppointment!=null && latestAppointment.getStatus()==Status.NEW){
			bindingResult.rejectValue("message", "inprogress.appointment");
		}

		if (bindingResult.hasErrors()) {
			populateDefaultCheckBoxesAndRadios(model);
			return "receptionist/bookingForm";
		}

		appointmentService.saveOrUpdate(appointment);
		return "redirect:/receptionist/home";
	}


	/**
	 * Get Veterinarians
	 * @param veterinaryName Veterinarian's name
	 * @return List of select option
	 */
	@GetMapping("/veterinary/options")
	@ResponseBody
	public List<SelectOption> getVeterinarians(@RequestParam(required = false) String veterinaryName) {
		List<Veterinarian> options = veterinaryService.findByVeterinaryNameContaining(veterinaryName);

		List<SelectOption> selectOptions = new ArrayList<>();

		for(Veterinarian veterinarian : options){
			selectOptions.add(new SelectOption(veterinarian.getUserId(), veterinarian.getFullName()));
		}
		return selectOptions;
	}

	/**
	 * Get edit user
	 * @param model
	 * @param principal
	 * @return return receptionist profile page
	 */
	@GetMapping("/edit")
	public String editUser(Model model, Principal principal) {
		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		User user = userService.findById(currentUser.getUserId()).orElse(new User());

		model.addAttribute("clinics", getClinics());
		model.addAttribute("userForm", user);
		populateDefaultCheckBoxesAndRadios(model);

		return "/receptionist/editProfile";
	}


	/**
	 * Get profile
	 * @param model
	 * @param principal
	 * @return receptionist's view profile page
	 */
	@GetMapping("/profile")
	public String viewProfile(Model model, Principal principal) {
		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		User user = userService.findById(currentUser.getUserId()).orElse(new User());

		model.addAttribute("userForm", user);

		return "/receptionist/viewProfile";
	}


	/**
	 * Save user
	 * @param user user pojo
	 * @param bindingResult
	 * @param model
	 * @param redirectAttributes
	 * @param principal
	 * @return return receptionist profile page
	 */
	@PostMapping("/save")
	public String addUser(@ModelAttribute("userForm") @Valid User user, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes, Principal principal) {
		Receptionist currentUser  = (Receptionist)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		Optional<User> optionalReceptionist = userService.findById(currentUser.getUserId());

		if(!optionalReceptionist.isPresent()){
			bindingResult.rejectValue("username", "invalid.user");
		}

		if (bindingResult.hasErrors()) {
			populateDefaultCheckBoxesAndRadios(model);
			return "receptionist/editProfile";
		}

		user.setUserId(optionalReceptionist.get().getUserId());

		redirectAttributes.addFlashAttribute("alert", "success");
		redirectAttributes.addFlashAttribute("msg", "User updated successfully!");

		userService.saveOrUpdate(user);

		return "redirect:/receptionist/profile";
	}


	/**
	 * get all clinics
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
	 * Get reset password page
	 * @param model
	 * @param principal
	 * @return return reset password page
	 */
	@GetMapping("/resetPassword")
	public String resetPassword(Model model, Principal principal) {
		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		User user = userService.findById(currentUser.getUserId()).get();
		user.setPassword("");
		model.addAttribute("userForm", user);

		return "/receptionist/resetPassword";
	}


	/**
	 * Reset user password
	 * @param user user pojo
	 * @param bindingResult
	 * @param model
	 * @param principal
	 * @return return receptionist home page
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
			return "/receptionist/resetPassword";
		}

		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		currentUser = userService.findById(currentUser.getUserId()).get();

		currentUser.setPassword(user.getPassword());

		userService.updatePassword(currentUser);

		return "redirect:/receptionist/home";
	}


	/**
	 * download report file
	 * @param reportId report id
	 * @return file object
	 */
	@GetMapping("/downloadFile/{reportId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("reportId") Integer reportId) {
		Report report = reportService.getFile(reportId);

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(report.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + report.getFileName() + "\"")
				.body(new ByteArrayResource(report.getData()));
	}


	@GetMapping("/view-prescription/{appointmentId}")
	public String viewPetPrescription(@PathVariable("appointmentId") Integer appointmentId, Model model) {

		Appointment appointment = appointmentService.findById(appointmentId).orElseThrow();


		model.addAttribute("userForm", appointment.getPet().getOwner());
		model.addAttribute("pet", appointment.getPet());
		model.addAttribute("appointment", appointment);
		model.addAttribute("diagnoses", appointment.getDiagnosis());
		model.addAttribute("prescriptionList", appointment.getDiagnosis().getPrescription());

		return "/receptionist/viewPrescription";
	}

}
