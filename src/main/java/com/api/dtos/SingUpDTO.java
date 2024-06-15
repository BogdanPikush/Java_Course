package com.api.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class SingUpDTO{

    @NotEmpty @Email @Length(min = 5, max = 50)
    private String email;

    @NotEmpty @Length(min = 5, max = 40)
    private String password;
    @NotEmpty @Length(min = 2, max = 100)
    private String firstName;

    @NotEmpty @Length(min = 2, max = 100)
    private String lastName;

    @NotEmpty @Length(min = 10, max = 13)
    private String phone;

    private int numberNewPost;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNumberNewPost() {
        return numberNewPost;
    }

    public void setNumberNewPost(int numberNewPost) {
        this.numberNewPost = numberNewPost;
    }
}
