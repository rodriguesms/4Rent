package br.com.rstore.rent.Controller.Form;

import br.com.rstore.rent.Models.Apartment;
import br.com.rstore.rent.Models.Status;
import br.com.rstore.rent.Repository.ApartmentRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Digits;

public class ApartmentForm {

    @NotNull @NotEmpty
    private String announcementTitle;
    @NotNull @Digits(integer=9, fraction = 2) @PositiveOrZero
    private Double price;
    @NotNull
    private Boolean forRent;
    @NotNull @Digits(integer=9, fraction = 2) @PositiveOrZero
    private Double area;
    @NotNull @Digits(integer=9, fraction = 2) @PositiveOrZero
    private Double aptArea;
    @NotNull @Digits(integer=9, fraction = 2) @PositiveOrZero
    private Double condomValue;
    @NotNull @PositiveOrZero
    private Integer roomsQuant;
    @NotNull @PositiveOrZero
    private Integer floor;
    @NotNull @PositiveOrZero
    private Integer garageSpots;
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

    public Apartment Convert() {
        return new Apartment(announcementTitle, area, forRent, price, zipCode, state, city, neighborhood, street,
                number, aptArea, condomValue, roomsQuant, floor, garageSpots);
    }

    public Apartment Update(Long id, ApartmentRepository apartmentRepository){
        Apartment apartment = apartmentRepository.getById(id);

        apartment.setAnnouncementTitle(this.announcementTitle);
        apartment.setArea(this.area);
        apartment.setForRent(this.forRent);
        apartment.setPrice(this.price);
        apartment.setZipCode(this.zipCode);
        apartment.setState(this.state);
        apartment.setCity(this.city);
        apartment.setNeighborhood(this.neighborhood);
        apartment.setStreet(this.street);
        apartment.setNumber(this.number);
        apartment.setAptArea(this.aptArea);
        apartment.setCondomValue(this.condomValue);
        apartment.setRoomsQuant(this.roomsQuant);
        apartment.setFloor(this.floor);
        apartment.setGarageSpots(this.garageSpots);

        return apartment;
    }

    public Apartment changeAdStatus(Long id, ApartmentRepository apartmentRepository) {
        Apartment apartment = apartmentRepository.getById(id);
        if(apartment.getStatus().equals(Status.AVAILABLE)){
            apartment.setStatus(Status.AVAILABLE);
        }else if(apartment.getStatus().equals(Status.PAUSED)){
            apartment.setStatus(Status.AVAILABLE);
        }
        return apartment;
    }

    /*public Apartment sellOrRentApartment(Long id, Long newOwnerId, ApartmentRepository apartmentRepository){

        Apartment apartment = apartmentRepository.getById(id);
        Owner newOwner =

        if(apartment.getStatus() == Status.AVAILABLE){
            apartment.setOwner(newOwner);
            if(apartment.getForRent()){
                apartment.setStatus(Status.RENTED);
            }else{
                apartment.setStatus(Status.SOLD);
            }
        }
        return apartment;
    }*/

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
