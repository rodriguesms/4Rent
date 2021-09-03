package br.com.rstore.rent.Controller.DTO;

import org.springframework.security.core.Authentication;
import br.com.rstore.rent.Models.Owner;

public class OwnerDTO {

    private Long id;
    private String username;
    private String email;

    public OwnerDTO(Owner owner){
        this.id = owner.getId();
        this.username=owner.getName();
        this.email=owner.getEmail();
    }

    public OwnerDTO(Authentication authentication){
        Owner owner = (Owner)authentication.getPrincipal();
        this.id = owner.getId();
        this.username = owner.getName();
        this.email = owner.getEmail();
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
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
