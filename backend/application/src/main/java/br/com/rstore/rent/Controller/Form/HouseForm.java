package br.com.rstore.rent.Controller.Form;

import br.com.rstore.rent.Models.House;
import br.com.rstore.rent.Models.Owner;
import br.com.rstore.rent.Models.Status;
import br.com.rstore.rent.Repository.HouseRepository;
import br.com.rstore.rent.Repository.OwnerRepository;

import javax.validation.constraints.*;
import java.util.Optional;

public class HouseForm {

    @NotNull @NotEmpty
    private String announcementTitle;
    @NotNull @Digits(integer = 9, fraction = 2) @PositiveOrZero
    private Double price;
    @NotNull
    private Boolean forRent;
    @NotNull @Digits(integer=9, fraction=2) @PositiveOrZero
    private Double area;
    @NotNull @NotEmpty
    private String zipCode;
    @NotNull @NotEmpty
    private String state;
    @NotNull @NotEmpty
    private String city;
    @NotNull @NotEmpty
    private String neighborhood;
    @NotNull @NotEmpty
    private String street;
    @NotNull @PositiveOrZero
    private Integer number;
    @NotNull @PositiveOrZero
    private Integer roomsQuant;
    @NotNull @PositiveOrZero
    private Integer floor;
    @NotNull @Digits(integer = 9, fraction = 2) @PositiveOrZero
    private Double builtArea;
    @NotNull @Email @NotEmpty
    private String ownerEmail;

    public House Convert(OwnerRepository ownerRepository) {
        Optional<Owner> owner = ownerRepository.findByEmail(ownerEmail);
        if(owner.isPresent())
            return new House(announcementTitle, area, forRent, price, zipCode, state, city, neighborhood, street, number, roomsQuant, floor, builtArea, owner.get());
        else
            return new House(announcementTitle, area, forRent, price, zipCode, state, city, neighborhood, street, number, roomsQuant, floor, builtArea, null);
    }

    public House Update(Long id, HouseRepository houseRepository){
        House house = houseRepository.getById(id);

        house.setAnnouncementTitle(this.announcementTitle);
        house.setArea(this.area);
        house.setForRent(this.forRent);
        house.setPrice(this.price);
        house.setZipCode(this.zipCode);
        house.setState(this.state);
        house.setCity(this.city);
        house.setNeighborhood(this.neighborhood);
        house.setStreet(this.street);
        house.setNumber(this.number);
        house.setRoomsQuant(this.roomsQuant);
        house.setFloor(this.floor);
        house.setBuiltArea(this.builtArea);

        return house;
    }

    public House changeAdStatus(Long id, HouseRepository houseRepository){
        House house = houseRepository.getById(id);
        if(house.getStatus().equals(Status.AVAILABLE)){
            house.setStatus(Status.AVAILABLE);
        }else if(house.getStatus().equals(Status.PAUSED)){
            house.setStatus(Status.AVAILABLE);
        }
        return house;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
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

    public Double getBuiltArea() {
        return builtArea;
    }

    public void setBuiltArea(Double builtArea) {
        this.builtArea = builtArea;
    }
}
