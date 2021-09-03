package br.com.rstore.rent.Controller.DTO;

import java.time.LocalDateTime;
import br.com.rstore.rent.Models.RealState;
import org.springframework.data.domain.Page;

public class RealStateDTO {

    private Long id;
    private String announcementTitle;
    private LocalDateTime announcementDate;
    private String city;
    private String state;
    private Double price;
    private Boolean forRent;
    private String status;
    private String type;

    public RealStateDTO(RealState realState){
        this.id = realState.getId();
        this.announcementTitle = realState.getAnnouncementTitle();
        this.city = realState.getCity();
        this.state = realState.getState();
        this.price = realState.getPrice();
        this.forRent = realState.getForRent();
        this.status = realState.getStatus().toString();
        this.announcementDate = realState.getAnnouncementDate();
        this.type = realState.getType().toString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public static Page<RealStateDTO> Convert(Page<RealState> realStates){
        return realStates.map(RealStateDTO::new);
    }
}
