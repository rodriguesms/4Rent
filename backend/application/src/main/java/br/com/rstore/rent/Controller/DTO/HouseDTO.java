package br.com.rstore.rent.Controller.DTO;

import java.time.LocalDateTime;
import br.com.rstore.rent.Models.House;
import org.springframework.data.domain.Page;

public class HouseDTO {

    private Long id;
    private String announcementTitle;
    private LocalDateTime announcementDate;
    private String city;
    private String state;
    private Double price;
    private Boolean forRent;
    private String status;

    public HouseDTO(House house){
        this.id = house.getId();
        this.announcementTitle = house.getAnnouncementTitle();
        this.city = house.getCity();
        this.state = house.getState();
        this.price = house.getPrice();
        this.forRent = house.getForRent();
        this.status = house.getStatus().toString();
        this.announcementDate = house.getAnnouncementDate();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDateTime getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(LocalDateTime announcementDate) {
        this.announcementDate = announcementDate;
    }

    public static Page<HouseDTO> Convert(Page<House> houses){
        return houses.map(HouseDTO::new);
    }


}
