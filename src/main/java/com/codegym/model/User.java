package com.codegym.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
@Entity
@Table
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Size(min = 8,max = 45)
    private String firstName;
    @NotEmpty
    @Size(min = 8,max = 45)
    private String lastName;
    @Min(18)
    private int age;
    private String phoneNumber;
    private String email;

    public User( @NotEmpty @Size(min = 8, max = 45) String firstName, String lastName, @Min(18) int age, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user= (User) target;
        String phoneNumber= user.getPhoneNumber();
        String email= user.getEmail();
        ValidationUtils.rejectIfEmpty(errors,"phoneNumber","phoneNumber.empty");
        ValidationUtils.rejectIfEmpty(errors,"email","email.empty");
        if(phoneNumber.length()>11||phoneNumber.length()<10){
            errors.rejectValue("phoneNumber","phoneNumber.length");
        }
        if(!phoneNumber.startsWith("0")){
            errors.rejectValue("phoneNumber","phoneNumber.startWith");
        }
        if(!phoneNumber.matches("(^$|[0-9]*$)")){
            errors.rejectValue("phoneNumber","phoneNumber.matches");
        }
        if (!email.matches("^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")){
            errors.rejectValue("email","email.matches");
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
