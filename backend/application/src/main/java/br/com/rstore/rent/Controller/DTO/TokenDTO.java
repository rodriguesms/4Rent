package br.com.rstore.rent.Controller.DTO;

public class TokenDTO {

    private String token;
    private String type;
    private OwnerDTO authenticated;

    public TokenDTO(String token, String type, OwnerDTO authenticated) {
        this.token = token;
        this.type = type;
        this.authenticated = authenticated;
    }

    public OwnerDTO getOwner() {
        return authenticated;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
