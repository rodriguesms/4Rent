package br.com.rstore.rent.Controller.Form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    private String email;
    private String password;

    public UsernamePasswordAuthenticationToken Convert(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
