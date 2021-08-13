package br.com.rstore.rent.Controller.DTO;

import br.com.rstore.rent.Models.Owner;

public class OwnerDTO {

    private String username;
    private String email;

    public OwnerDTO(Owner owner){
        this.username=owner.getName();
        this.email=owner.getEmail();
    }

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
}
