package br.com.rstore.rent.Controller.DTO;

import java.util.List;
import java.util.stream.Collectors;
import br.com.rstore.rent.Models.RealState;

public class RealStateDTO {

    private Long id;
    private String announcementTitle;
    private String city;
    private String state;
    private Double price;
    private Boolean forRent;

    public RealStateDTO(RealState realState){
        this.id = realState.getId();
        this.announcementTitle = realState.getAnnouncementTitle();
        this.city = realState.getCity();
        this.state = realState.getState();
        this.price = realState.getPrice();
        this.forRent = realState.getForRent();
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

    public static List<RealStateDTO> convert(List<RealState> realStates){
        return realStates.stream().map(RealStateDTO::new).collect(Collectors.toList());
    }
}
