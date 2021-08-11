package br.com.rstore.rent.Models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Apartment {

    //Commercial info
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Owner owner;
    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;
    private String announcementTitle;
    private LocalDateTime announcementDate = LocalDateTime.now();

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

    //Specific Apartment Info
    private Double aptArea;
    private Double condomValue;
    private Integer roomsQuant;
    private Integer floor;
    private Integer garageSpots;

    public Apartment(String announcementTitle,  Double area, Boolean forRent, Double price, String zipCode, String state, String city, String neighborhood, String street, Integer number, Double aptArea, Double condomValue, Integer roomsQuant, Integer floor, Integer garageSpots) {
        this.announcementTitle = announcementTitle;
        this.area = area;
        this.forRent = forRent;
        this.price = price;
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.aptArea = aptArea;
        this.condomValue = condomValue;
        this.roomsQuant = roomsQuant;
        this.floor = floor;
        this.garageSpots = garageSpots;
    }



    public Apartment(){  }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(LocalDateTime announcementDate) {
        this.announcementDate = announcementDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Boolean getForRent() {
        return forRent;
    }

    public void setForRent(Boolean forRent) {
        this.forRent = forRent;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getAptArea() {
        return aptArea;
    }

    public void setAptArea(Double aptArea) {
        this.aptArea = aptArea;
    }

    public Double getCondomValue() {
        return condomValue;
    }

    public void setCondomValue(Double condomValue) {
        this.condomValue = condomValue;
    }

    public Integer getRoomsQuant() {
        return roomsQuant;
    }

    public void setRoomsQuant(Integer roomsQuant) {
        this.roomsQuant = roomsQuant;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getGarageSpots() {
        return garageSpots;
    }

    public void setGarageSpots(Integer garageSpots) {
        this.garageSpots = garageSpots;
    }
}
