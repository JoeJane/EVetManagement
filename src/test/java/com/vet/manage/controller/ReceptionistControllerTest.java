package com.vet.manage.controller;

import com.vet.manage.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ReceptionistControllerTest starter class
 */
@WebMvcTest(ReceptionistController.class)
public class ReceptionistControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private OwnerService ownerService;

    @MockBean
    private PetService petService;

    @MockBean
    private VeterinaryService veterinaryService;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private UserService userService;

    @MockBean
    private ClinicService clinicService;

    @MockBean
    private ReportService reportService;

    @Test
    @DisplayName("Test login page Success")
    public void loginShouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @DisplayName("Test logout page Success")
    public void logoutShouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/logout")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

}
