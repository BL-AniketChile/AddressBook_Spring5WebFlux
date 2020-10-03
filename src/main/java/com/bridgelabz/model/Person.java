package com.bridgelabz.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Document
public class Person implements Serializable {

    @Id
    private String id;

    @NotBlank(message = "First name cannot be blank")
    @Length(min = 3, max = 30, message = "First name should be in between 3 to 30 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Length(min = 3, max = 30, message = "Last name should be in between 3 to 30 characters")
    private String lastName;

    @DBRef
    private AddressBook addressBook;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
}
