package br.com.rstore.rent.Controller.Form;

import br.com.rstore.rent.Models.Land;
import br.com.rstore.rent.Models.Owner;
import br.com.rstore.rent.Models.Status;
import br.com.rstore.rent.Repository.OwnerRepository;
import br.com.rstore.rent.Repository.LandRepository;

import javax.validation.constraints.*;
import java.util.Optional;

public class LandForm {

    @NotNull @NotEmpty
    private String announcementTitle;
    @NotNull @PositiveOrZero @Digits(integer = 9, fraction = 2)
    private Double area;
    @NotNull
    private Boolean forRent; // For rent = true, For sale = false
    @NotNull @PositiveOrZero @Digits(integer = 9, fraction = 2)
    private Double price;
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
    @NotNull @Email @NotEmpty
    private String ownerEmail;

    public Land Convert(OwnerRepository ownerRepository){
        Optional<Owner> owner = ownerRepository.findByEmail(ownerEmail);
        if(owner.isPresent())
            return new Land(area, forRent, price, announcementTitle, zipCode, state, city, neighborhood, street, number, owner.get());
        else
            return new Land(area, forRent, price, announcementTitle, zipCode, state, city, neighborhood, street, number, null);
    }

    public Land Update(Long id, LandRepository landRepository){
        Land land = landRepository.getById(id);

        land.setAnnouncementTitle(this.announcementTitle);
        land.setArea(this.area);
        land.setForRent(this.forRent);
        land.setPrice(this.price);
        land.setZipCode(this.zipCode);
        land.setState(this.state);
        land.setCity(this.city);
        land.setNeighborhood(this.neighborhood);
        land.setStreet(this.street);
        land.setNumber(this.number);

        return land;
    }

    public Land changeAdStatus(Long id, LandRepository realStateRepository){
        Land land = realStateRepository.getById(id);
        if(land.getStatus().equals(Status.AVAILABLE)){
            land.setStatus(Status.PAUSED);
        }else if(land.getStatus().equals(Status.PAUSED)){
            land.setStatus(Status.AVAILABLE);
        }        return land;
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
}
