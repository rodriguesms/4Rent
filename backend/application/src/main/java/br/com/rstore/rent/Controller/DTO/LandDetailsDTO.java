package br.com.rstore.rent.Controller.DTO;

import br.com.rstore.rent.Models.Land;

import java.time.LocalDateTime;

public class LandDetailsDTO {
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

    public LandDetailsDTO(Land land){
        this.id = land.getId();
        this.ownerName = land.getOwner().getName();
        this.status = land.getStatus().toString();
        this.announcementTitle = land.getAnnouncementTitle();
        this.announcementDate = land.getAnnouncementDate();

        this.area = land.getArea();
        this.forRent = land.getForRent();
        this.price = land.getPrice();

        this.zipCode = land.getZipCode();
        this.state = land.getState();
        this.city = land.getCity();
        this.neighborhood = land.getNeighborhood();
        this.street = land.getStreet();
        this.number = land.getNumber();
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
