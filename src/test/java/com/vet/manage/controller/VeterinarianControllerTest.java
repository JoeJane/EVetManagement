package com.vet.manage.controller;

import com.vet.manage.model.dto.Role;
import com.vet.manage.model.entity.Pet;
import com.vet.manage.model.entity.User;
import com.vet.manage.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VeterinarianController.class)
class VeterinarianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerService ownerService;

    @MockBean
    private PetService petService;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private UserService userService;

    @MockBean
    private ClinicService clinicService;

    @MockBean
    private ReportService reportService;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Role.VETERINARIAN.name());
        return Collections.singletonList(authority);
    }

    @Test
    @WithMockCustomUser(username = "user1", authorities = {"VETERINARIAN"}, name = "First Last")
    void veterinarianHome() throws Exception {
        // Prepare test data
        User testUser = new User(1, "user1", "password", Role.VETERINARIAN);

        Pet pet1 = new Pet();
        pet1.setId(1);

        Pet pet2 = new Pet();
        pet2.setId(2);

        // Mock service calls
        when(petService.findPetByVeterinarian(any(Integer.class))).thenReturn(Arrays.asList(pet1, pet2));

        // Perform the request and verify the results
        mockMvc.perform(get("/veterinarian/home").principal(new UsernamePasswordAuthenticationToken(testUser, "password")))
                .andExpect(status().isOk())
                .andExpect(view().name("/veterinarian/home"))
                .andExpect(model().attributeExists("searchterm", "bulkAction", "pets", "vetId", "message"));
    }
}
