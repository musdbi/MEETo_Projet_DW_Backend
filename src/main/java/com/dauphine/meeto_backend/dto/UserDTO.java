package com.dauphine.meeto_backend.dto;

import java.util.Date;
import java.util.UUID;

public class UserDTO {

    private UUID userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String contactNo;
    private Date dateOfBirth;
    private Date registrationDate;

    public UserDTO(UUID userId, String firstName, String lastName, String userName, String email, String pswd, String contactNo, Date dateOfBirth, Date registrationDate) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = pswd;
        this.contactNo = contactNo;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
    }

    public UserDTO() {}

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pswd) {
        this.password = pswd;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
