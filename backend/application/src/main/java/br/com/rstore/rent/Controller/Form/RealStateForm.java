package br.com.rstore.rent.Controller.Form;

import br.com.rstore.rent.Models.RealState;
import br.com.rstore.rent.Models.Status;
import br.com.rstore.rent.Repository.RealStateRepository;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class RealStateForm {

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

    public RealState Convert(){
        return new RealState(area, forRent, price, announcementTitle, zipCode, state, city, neighborhood, street, number);
    }

    public RealState Update(Long id, RealStateRepository realStateRepository){
        RealState realState = realStateRepository.getById(id);

        realState.setAnnouncementTitle(this.announcementTitle);
        realState.setArea(this.area);
        realState.setForRent(this.forRent);
        realState.setPrice(this.price);
        realState.setZipCode(this.zipCode);
        realState.setState(this.state);
        realState.setCity(this.city);
        realState.setNeighborhood(this.neighborhood);
        realState.setStreet(this.street);
        realState.setNumber(this.number);

        return realState;
    }

    public RealState changeAdStatus(Long id, RealStateRepository realStateRepository){
        RealState realState = realStateRepository.getById(id);
        if(realState.getStatus().equals(Status.AVAILABLE)){
            realState.setStatus(Status.AVAILABLE);
        }else if(realState.getStatus().equals(Status.PAUSED)){
            realState.setStatus(Status.AVAILABLE);
        }        return realState;
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
