package com.vet.manage.model.entity;

import com.vet.manage.model.dto.Province;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Entity for Address
 * @author Jane Aarthy Joseph
 */
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Address cannot be empty")
    @Column(name = "street")
    private String street;

    @Column(name = "address_no")
    @NotBlank(message = "Address NO cannot be empty")
    private String addressNo;

    @NotBlank(message = "City cannot be empty")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "Postal Code cannot be empty")
    @Column(name = "postal_code")
    private String postalCode;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Province is mandatory")
    @Column(name = "province")
    private Province province;

    @NotBlank(message = "Country is mandatory")
    @Column(name = "country")
    private String country;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private User user;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Clinic clinic;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Owner owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
