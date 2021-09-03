package br.com.rstore.rent.Controller.DTO;

import org.springframework.security.core.Authentication;

import br.com.rstore.rent.Models.Owner;

public class RetrievalDTO {
    
    OwnerDTO owner;

    public RetrievalDTO(OwnerDTO owner){
        this.owner = owner;
    }

    public OwnerDTO getOwner(){
        return owner;
    }

    public void setOwner(OwnerDTO owner){
        this.owner = owner;
    }
}
