package com.vet.manage.controller;

import com.vet.manage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Controller for Login related task
 * @author Abasibiangake
 */
@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    /**
     * Handle request for login page
     * @param model Model attribute, which is wired automatically by spring framework
     * @param error Error message
     * @param logout Logout message
     * @return Load login page
     */
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Username or password is incorrect!");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }


    /**
     * Handle request for logout
     * @param request HttpServletRequest
     * @param redirectAttributes RedirectAttributes
     * @return Redirect to login page
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        request.getSession().invalidate();
        redirectAttributes.addFlashAttribute("message", "You have successfully logged out !");

        return "redirect:/login";
    }
}
