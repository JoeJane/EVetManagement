package com.vet.manage.controller;
import com.vet.manage.model.dto.*;
import com.vet.manage.model.entity.*;
import com.vet.manage.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerService ownerService;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private ReportService reportService;

    @MockBean
    private UserService userService;

    private User testUser;
    // private Owner testOwner;

    @BeforeEach
    public void setUp() {
        testUser = new Owner();
        testUser.setUserId(1);
        testUser.setUsername("testuser");
        testUser.setPassword("password");
    }

    @Test
    @WithMockCustomUser(username = "user1", authorities = {"VETERINARIAN"}, name = "First Last")
    public void testViewProfile() throws Exception {
        when(userService.findById(any(Integer.class))).thenReturn(Optional.of(testUser));

        mockMvc.perform(get("/user/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("/user/viewProfile"))
                .andExpect(model().attributeExists("userForm"));
    }

}