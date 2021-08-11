package br.com.rstore.rent.Controller.DTO;

import br.com.rstore.rent.Models.Apartment;

import java.time.LocalDateTime;

public class ApartmentDetailsDTO {

    //Object Info//
    private Long id;
    private String ownerName;
    private String status;
    private String announcementTitle;
    private LocalDateTime announcementDate;

    //Commercial Info//
    private Double area;
    private Boolean forRent; // For rent = true, For sale = false
    private Double price;

    //Address Info//
    private String zipCode;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private Integer number;

    //Specific Apartment Info//
    private Double aptArea;
    private Double condomValue;
    private Integer roomsQuant;
    private Integer floor;
    private Integer garageSpots;

    public ApartmentDetailsDTO(Apartment apartment){

        this.id = apartment.getId();
        this.ownerName = "apartment.getOwner().getName()";
        this.status = apartment.getStatus().toString();
        this.announcementTitle = apartment.getAnnouncementTitle();
        this.announcementDate = apartment.getAnnouncementDate();
        this.area = apartment.getArea();
        this.forRent = apartment.getForRent();
        this.price = apartment.getPrice();
        this.zipCode = apartment.getZipCode();
        this.state = apartment.getState();
        this.city = apartment.getCity();
        this.neighborhood = apartment.getNeighborhood();
        this.street = apartment.getStreet();
        this.number = apartment.getNumber();
        this.aptArea = apartment.getAptArea();
        this.condomValue = apartment.getCondomValue();
        this.roomsQuant = apartment.getRoomsQuant();
        this.floor = apartment.getFloor();
        this.garageSpots = apartment.getGarageSpots();
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

    public Double getAptArea() {
        return aptArea;
    }

    public Double getCondomValue() {
        return condomValue;
    }

    public Integer getRoomsQuant() {
        return roomsQuant;
    }

    public Integer getFloor() {
        return floor;
    }

    public Integer getGarageSpots() {
        return garageSpots;
    }
}
