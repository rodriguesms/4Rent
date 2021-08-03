package br.com.rstore.rent.Controller.DTO;

import java.util.List;
import java.util.stream.Collectors;
import br.com.rstore.rent.Models.House;

public class HouseDTO {

    private Long id;
    private String announcementTitle;
    private String city;
    private String state;
    private Double price;
    private Boolean forRent;

    public HouseDTO(House house){
        this.id = house.getId();
        this.announcementTitle = house.getAnnouncementTitle();
        this.city = house.getCity();
        this.state = house.getState();
        this.price = house.getPrice();
        this.forRent = house.getForRent();
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

    public static List<HouseDTO> Convert(List<House> houses){
        return houses.stream().map(HouseDTO::new).collect(Collectors.toList());
    }


}
