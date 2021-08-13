package br.com.rstore.rent.Models;

import javax.persistence.Entity;

@Entity
public class Land extends RealState{
    public Land(Double area, Boolean forRent, Double price, String announcementTitle, String zipCode, String state, String city, String neighborhood, String street, Integer number, Owner owner) {
        super(area, forRent, price, announcementTitle, zipCode, state, city, neighborhood, street, number, owner, RealStateType.LAND);
    }
    public Land(){
        super();
    }
}
