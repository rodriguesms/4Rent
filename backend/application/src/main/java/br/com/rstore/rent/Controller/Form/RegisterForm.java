package br.com.rstore.rent.Controller.Form;

import javax.validation.constraints.*;

public class RegisterForm {

    @NotNull @NotEmpty
    private String username;
    @NotNull @NotEmpty @Email
    private String email;
    @NotNull @NotEmpty @Size(min = 8)
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
