package br.com.rstore.rent.Models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class House extends RealState{

    private Integer roomsQuant;
    private Integer floor;
    private Double builtArea;

    public House(String announcementTitle, Double area, Boolean forRent, Double price, String zipCode, String state, String city, String neighborhood, String street, Integer number, Integer roomsQuant, Integer floor, Double builtArea, Owner owner) {
        super(area, forRent, price, announcementTitle, zipCode, state, city, neighborhood, street, number, owner, RealStateType.HOUSE);
        this.roomsQuant = roomsQuant;
        this.floor = floor;
        this.builtArea = builtArea;
    }

    public House(){ }

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

