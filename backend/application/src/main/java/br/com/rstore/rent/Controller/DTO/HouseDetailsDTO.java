package br.com.rstore.rent.Controller.DTO;

import br.com.rstore.rent.Models.House;

import java.time.LocalDateTime;

public class HouseDetailsDTO {
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

    //Specific House Info
    private Integer roomsQuant;
    private Integer floor;
    private Double builtArea;

    public HouseDetailsDTO(House house){
        this.id = house.getId();
        this.ownerName = house.getOwner().getName();
        this.status = house.getStatus().toString();
        this.announcementTitle = house.getAnnouncementTitle();
        this.announcementDate = house.getAnnouncementDate();

        this.area = house.getArea();
        this.forRent = house.getForRent();
        this.price = house.getPrice();

        this.zipCode = house.getZipCode();
        this.state = house.getState();
        this.city = house.getCity();
        this.neighborhood = house.getNeighborhood();
        this.street = house.getStreet();
        this.number = house.getNumber();

        this.roomsQuant = house.getRoomsQuant();
        this.floor = house.getFloor();
        this.builtArea = house.getBuiltArea();
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

    public Integer getRoomsQuant() {
        return roomsQuant;
    }

    public Integer getFloor() {
        return floor;
    }

    public Double getBuiltArea() {
        return builtArea;
    }
}
