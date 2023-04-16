package com.vet.manage.controller;

import com.vet.manage.controller.AdminController;
import com.vet.manage.service.ClinicService;
import com.vet.manage.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * E-vet starter class
 */
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ClinicService clinicService;

    @Test
    @DisplayName("Test unauthorized access")
    public void unauthorizedAccess() throws Exception {
        this.mockMvc.perform(get("/admin/home")).andDo(print()).andExpect(status().isUnauthorized());
    }
}
