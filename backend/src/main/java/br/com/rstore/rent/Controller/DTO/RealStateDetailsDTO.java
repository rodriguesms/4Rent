package br.com.rstore.rent.Controller.DTO;

import br.com.rstore.rent.Models.RealState;

import java.time.LocalDateTime;

public class RealStateDetailsDTO {
    private Long id;
    private String ownerName;
    private String status;
    private String announcementTitle;
    private LocalDateTime announcementDate;

    //Product main info
    private Double area;
    private Boolean forRent; // For rent = true, For sale = false
    private Double price;

    // Address Info
    private String zipCode;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private Integer number;

    public RealStateDetailsDTO(RealState realState){
        this.id = realState.getId();
        this.ownerName = "realState.getOwner().getName()";
        this.status = realState.getStatus().toString();
        this.announcementTitle = realState.getAnnouncementTitle();
        this.announcementDate = realState.getAnnouncementDate();

        this.area = realState.getArea();
        this.forRent = realState.getForRent();
        this.price = realState.getPrice();

        this.zipCode = realState.getZipCode();
        this.state = realState.getState();
        this.city = realState.getCity();
        this.neighborhood = realState.getNeighborhood();
        this.street = realState.getStreet();
        this.number = realState.getNumber();
    }

    public Long getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public LocalDateTime getAnnouncementDate() {
        return announcementDate;
    }

    public String getStatus() {
        return status;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public Double getArea() {
        return area;
    }

    public Boolean getForRent() {
        return forRent;
    }

    public Double getPrice() {
        return price;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }
}
