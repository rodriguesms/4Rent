package br.com.rstore.rent.Controller.DTO;

import java.util.List;
import java.util.stream.Collectors;
import br.com.rstore.rent.Models.Apartment;

public class ApartmentDTO {

    private Long id;
    private String announcementTitle;
    private String city;
    private String state;
    private Double price;
    private Boolean forRent;

    public ApartmentDTO(Apartment apartment){
        this.id = apartment.getId();
        this.announcementTitle = apartment.getAnnouncementTitle();
        this.city = apartment.getCity();
        this.state = apartment.getState();
        this.price = apartment.getPrice();
        this.forRent = apartment.getForRent();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getForRent() {
        return forRent;
    }

    public void setForRent(Boolean forRent) {
        this.forRent = forRent;
    }

    public static List<ApartmentDTO> Convert(List<Apartment> apartments){
        return apartments.stream().map(ApartmentDTO::new).collect(Collectors.toList());
    }
}
