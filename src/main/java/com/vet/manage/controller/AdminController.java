package com.vet.manage.controller;

import com.vet.manage.model.dto.Province;
import com.vet.manage.model.dto.Role;
import com.vet.manage.model.dto.SearchTerm;
import com.vet.manage.model.dto.VeterinarySpecialist;
import com.vet.manage.model.entity.Clinic;
import com.vet.manage.model.entity.User;
import com.vet.manage.service.ClinicService;
import com.vet.manage.service.UserService;
import com.vet.manage.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;


/**
 * Controller for Admin
 * @author Jane Aarthy Joseph
 * 
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private ClinicService clinicService;


	/**
	 * Home page for admin user
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param principal Principal attribute, which is wired automatically by spring framework
	 * @return admin home page
	 */
	@GetMapping("/home")
	public String adminHome(Model model, Principal principal) {
		List<User> users = userService.findByRoleNot(Role.ADMIN);

		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		model.addAttribute("searchterm", new SearchTerm());
		model.addAttribute("bulkAction", new SearchTerm());
		model.addAttribute("users", users);
		model.addAttribute("roles", getRoles());
		model.addAttribute("message", "Welcome " + currentUser.getFullName());
		
		return "/admin/home";
	}


	/**
	 *
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param principal Principal attribute, which is wired automatically by spring framework
	 * @return clinic home page
	 */
	@GetMapping("/clinic/home")
	public String clinicHome(Model model, Principal principal) {
		List<Clinic> clinics = getClinics();

		model.addAttribute("searchterm", new SearchTerm());
		model.addAttribute("bulkAction", new SearchTerm());
		model.addAttribute("clinics", clinics);
		model.addAttribute("roles", getRoles());

		return "/admin/clinic/home";
	}


	/**
	 * Search users based on @SearchTerm
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param searchTerm Search parameters which is passed by frontend
	 * @return admin home page with search result
	 */
	@PostMapping("/search")
	public String search(Model model, @ModelAttribute("searchterm") SearchTerm searchTerm) {

		List<User> users = userService.searchUsersForAdminRole(searchTerm);

		model.addAttribute("searchterm", searchTerm);
		model.addAttribute("bulkAction", searchTerm);
		model.addAttribute("users", users);
		model.addAttribute("roles", getRoles());

		return "/admin/home";
	}


	/**
	 * Perform bulk Activate/ Deactivate for multiple users
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param searchTerm Search parameters which is passed by frontend
	 * @return admin home page with search result
	 */
	@PostMapping("/bulkAction")
	public String bulkAction(Model model, @ModelAttribute("bulkAction") SearchTerm searchTerm) {

		List<Integer> ids = Arrays.asList(searchTerm.getIds()).stream().map(s-> Integer.parseInt(s)).collect(Collectors.toList());

		userService.saveAllUser(ids, searchTerm.getStatus());

		List<User> users = userService.findByRoleNot(Role.ADMIN);

		model.addAttribute("searchterm", new SearchTerm());
		model.addAttribute("bulkAction", new SearchTerm());
		model.addAttribute("users", users);

		return "/admin/home";
	}


	/**
	 * User registration page
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @return User registration page to add
	 */
	@GetMapping("/clinic/add")
	public String addClinic(Model model) {
		model.addAttribute("clinicForm", new Clinic());
		populateDefaultCheckBoxesAndRadios(model);
		return "/admin/clinic/registerForm";
	}


	/**
	 * Create or update user
	 * @param clinic Clinic model
	 * @param bindingResult Binding Result
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param redirectAttributes Redirect Attributes
	 * @return User profile view page
	 */
	@PostMapping("/clinic/save")
	public String addClinic(@ModelAttribute("clinicForm") @Valid Clinic clinic, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			populateDefaultCheckBoxesAndRadios(model);
			return "admin/clinic/registerForm";
		}

		redirectAttributes.addFlashAttribute("alert", "success");
		if (clinic.isNew()) {
			redirectAttributes.addFlashAttribute("msg", "User added successfully!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
		}

		clinicService.saveOrUpdate(clinic);

		return "redirect:/admin/clinic/view/" + clinic.getId();
	}


	/**
	 * User edit page based on userID
	 * @param id Target User ID
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @return User registration page to edit
	 */
	@GetMapping("/clinic/edit/{id}")
	public String editClinic(@PathVariable("id") Integer id, Model model) {
		Clinic clinic = clinicService.findById(id).orElse(new Clinic());

		model.addAttribute("clinicForm", clinic);
		populateDefaultCheckBoxesAndRadios(model);

		return "/admin/clinic/registerForm";
	}


	/**
	 * View user profile based on User ID
	 * @param id Target User ID
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @return User profile view page
	 */
	@GetMapping("/clinic/view/{id}")
	public String viewClinic(@PathVariable("id") Integer id, Model model) {
		Clinic clinic = clinicService.findById(id).orElse(new Clinic());
		model.addAttribute("clinicForm", clinic);

		return "/admin/clinic/viewForm";
	}


	/**
	 * User registration page
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @return User registration page to add
	 */
	@GetMapping("/user/add")
	public String addUser(Model model) {
		model.addAttribute("userForm", new User());
		populateDefaultCheckBoxesAndRadios(model);
		return "/admin/registerForm";
	}


	/**
	 * User edit page based on userID
	 * @param id Target User ID
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @return User registration page to edit
	 */
	@GetMapping("/user/edit/{id}")
	public String editUser(@PathVariable("id") Integer id, Model model) {
		User user = userService.findById(id).orElse(new User());

		model.addAttribute("userForm", user);
		populateDefaultCheckBoxesAndRadios(model);

		return "/admin/registerForm";
	}


	/**
	 * Create or update user
	 * @param user User model
	 * @param bindingResult Binding Result
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param redirectAttributes Redirect Attributes
	 * @return User profile view page
	 */
	@PostMapping("/user/save")
	public String addUser(@ModelAttribute("userForm") @Valid User user, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {

		if(user.getRole() == Role.VETERINARIAN){
			if(user.getSpeciality() == null){
				bindingResult.rejectValue("speciality", "required.speciality");
			}
		}

		if(user.getUserId()==null && userService.findUserByUsername(user.getUsername()).isPresent()){
			bindingResult.rejectValue("username", "Duplicate.userForm.username");
		}


		if (bindingResult.hasErrors()) {
			populateDefaultCheckBoxesAndRadios(model);
			return "admin/registerForm";
		}

		redirectAttributes.addFlashAttribute("alert", "success");
		if (user.isNew()) {
			redirectAttributes.addFlashAttribute("msg", "User added successfully!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
		}

		User newUser = Converter.convert(user);
		userService.saveOrUpdate(newUser);

		return "redirect:/admin/user/view/" + newUser.getUserId();
	}


	/**
	 * View user profile based on User ID
	 * @param id Target User ID
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @return User profile view page
	 */
	@GetMapping("/user/view/{id}")
	public String viewUser(@PathVariable("id") Integer id, Model model) {
		User user = userService.findById(id).orElse(new User());
		model.addAttribute("userForm", user);

		return "/admin/viewForm";
	}


	/**
	 * REST method to activate user based on User ID
	 * @param id Target User ID
	 * @return Return Success or Fail
	 */
	@GetMapping(value="/activate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> activateUser(@PathVariable("id") Integer id) {
		userService.changeStatusById(id, false);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}


	/**
	 *
	 * @param id clinic id
	 * @return success reponse
	 */
	@GetMapping(value="/clinic/activate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> activateClinic(@PathVariable("id") Integer id) {
		clinicService.changeStatusById(id, false);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}


	/**
	 * Rest method to delete user based on User ID
	 * @param id Target User ID
	 * @return Return Success or Fail
	 */
	@GetMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> deleteUser(@PathVariable("id") Integer id) {
		userService.changeStatusById(id, true);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}


	/**
	 *
	 * @param id clinic id
	 * @return success reponse
	 */
	@GetMapping(value="/clinic/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> deleteClinic(@PathVariable("id") Integer id) {
		clinicService.changeStatusById(id, true);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}


	/**
	 * Load rest user password page
	 * @param id Target User ID
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @return Return user reset password page
	 */
	@GetMapping("/user/resetPassword/{id}")
	public String resetUserPassword(@PathVariable("id") Integer id, Model model) {
		User user = userService.findById(id).get();
		user.setPassword("");
		model.addAttribute("userForm", user);

		return "/admin/resetPassword";
	}


	/**
	 * Perform user rest password
	 * @param user User model
	 * @param bindingResult Binding result
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param redirectAttributes Redirect attributes
	 * @return Admin home page
	 */
	@PostMapping("/user/resetPassword")
	public String resetUserPassword(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {

		if(user.getPassword() == null || user.getPassword().equals("")){
			bindingResult.rejectValue("password", "required.password");
		}

		if(!user.getPassword().equals(user.getConfirmPassword())){
			bindingResult.rejectValue("confirmPassword", "notmatch.confirmPassword");
		}

		if(bindingResult.hasErrors()){
			model.addAttribute("userForm", user);
			return "/admin/resetPassword";
		}

		userService.updatePassword(user);

		return "redirect:/admin/home";
	}


	/**
	 * Load admin's rest password page
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param principal Principal attribute, which is wired automatically by spring framework
	 * @return Admin reset password page
	 */
	@GetMapping("/resetPassword")
	public String resetAdminPassword(Model model, Principal principal) {
		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		User user = userService.findById(currentUser.getUserId()).get();
		user.setPassword("");
		model.addAttribute("userForm", user);

		return "/admin/resetAdminPassword";
	}


	/**
	 * Perform admin's rest password
	 * @param user User model
	 * @param bindingResult Binding result
	 * @param model Model attribute, which is wired automatically by spring framework
	 * @param principal Principal attribute, which is wired automatically by spring framework
	 * @return Admin's home page
	 */
	@PostMapping("/resetPassword")
	public String resetAdminPassword(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model, Principal principal) {
		if(user.getPassword() == null || user.getPassword().equals("")){
			bindingResult.rejectValue("password", "required.password");
		}

		if(!user.getPassword().equals(user.getConfirmPassword())){
			bindingResult.rejectValue("confirmPassword", "notmatch.confirmPassword");
		}

		if(bindingResult.hasErrors()){
			model.addAttribute("userForm", user);
			return "/admin/resetAdminPassword";
		}

		User currentUser  = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		currentUser = userService.findById(currentUser.getUserId()).get();

		currentUser.setPassword(user.getPassword());

		userService.updatePassword(currentUser);

		return "redirect:/admin/home";
	}


	/**
	 * Populate default values for checkbox and radios
	 * @param model Model attribute, which is wired automatically by spring framework
	 */
	private void populateDefaultCheckBoxesAndRadios(Model model) {
		Map<Role, String > roles = getRoles();

		model.addAttribute("roles", roles);
		model.addAttribute("clinics", getClinics());
		model.addAttribute("provinces", getProvinces());
		model.addAttribute("countries", getCountries());
		model.addAttribute("veterinarianSpecialities", getVeterinarySpecialities());
	}


	/**
	 * Load country list master data
	 * @return map of country code and country name
	 */
	private static Map<String, String> getCountries(){
		Map<String, String> countries = new TreeMap<>();
		countries.put("CA", "Canada");
		countries.put("US", "America");

		return countries;
	}


	/**
	 * Load roles master data
	 * @return map of @Role and description
	 */
	private static Map<Role, String> getRoles(){
		Map<Role, String> roles = Arrays.stream(Role.values()).filter(role->role != Role.ADMIN).collect(Collectors.toMap(e->e, e->e.getValue(), (oldValue, newValue) -> oldValue, TreeMap::new));
		return roles;
	}


	/**
	 * Load provinces master data
	 * @return map of @Province master data
	 */
	private static Map<Province, String> getProvinces(){
		return Arrays.stream(Province.values()).collect(Collectors.toMap(e->e, e->e.getValue(), (oldValue, newValue) -> oldValue, TreeMap::new));
	}


	/**
	 * Get all clinics
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
	 * Load Veterinary specialty types master data
	 * @return map of @VeterinarySpecialist master data
	 */
	private static Map<VeterinarySpecialist, String> getVeterinarySpecialities(){
		return Arrays.stream(VeterinarySpecialist.values()).collect(Collectors.toMap(e->e, e->e.getValue(), (oldValue, newValue) -> oldValue, TreeMap::new));
	}

}
