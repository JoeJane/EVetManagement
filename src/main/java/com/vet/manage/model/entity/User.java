package com.vet.manage.model.entity;

import com.vet.manage.model.dto.Role;
import com.vet.manage.model.dto.VeterinarySpecialist;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

/**
 * Entity for User
 * @author Jane Aarthy Joseph
 */
@Entity
@Table(name = "users")
@Inheritance(strategy=InheritanceType.JOINED)
public class User<T> implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Integer userId;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid Email Address")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "User Name cannot be empty")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 4, message = "Password should be at least 4 characters long")
    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @NotBlank(message = "First Name cannot be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last Name cannot be empty")
    @Column(name = "last_name")
    private String lastName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Valid
    private Address address;

    @NotBlank(message = "Gender is mandatory")
    @Column(name = "gender")
    private String gender;

    @NotBlank(message = "Phone Number is mandatory")
    @Column(name = "phone_number")
    private String phoneNumber;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    private Boolean deleted = false;

    @NotNull(message = "Date of birth cannot be empty")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @NotNull(message = "Role is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role = Role.USER;

    @Transient
    private VeterinarySpecialist speciality;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    public User(){}

    public User(Integer id, String username, String password, Role role){
        this.userId = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return !deleted;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !deleted;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isNew() {
        return (this.userId == null);
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getIcon() {
        return (firstName.charAt(0) + "" + lastName.charAt(0)).toUpperCase();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public VeterinarySpecialist getSpeciality() {
        return speciality;
    }

    public void setSpeciality(VeterinarySpecialist speciality) {
        this.speciality = speciality;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
