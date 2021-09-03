package br.com.rstore.rent.Models;

import javax.persistence.*;

@Entity
public class Apartment extends RealState{

    //Specific Apartment Info
    private Double aptArea;
    private Double condomValue;
    private Integer roomsQuant;
    private Integer floor;
    private Integer garageSpots;
    public Apartment(String announcementTitle,  Double area, Boolean forRent, Double price, String zipCode, String state, String city, String neighborhood, String street, Integer number, Double aptArea, Double condomValue, Integer roomsQuant, Integer floor, Integer garageSpots, Owner owner) {
        super(area, forRent, price, announcementTitle, zipCode, state, city, neighborhood, street, number, owner, RealStateType.APARTMENT);
        this.aptArea = aptArea;
        this.condomValue = condomValue;
        this.roomsQuant = roomsQuant;
        this.floor = floor;
        this.garageSpots = garageSpots;
    }

    public Apartment(){  }

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
