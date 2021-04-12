package com.urjcstarshipbazaar.models;

import com.urjcstarshipbazaar.models.spaceships.Spaceship;

import java.util.List;

public class Transaction {

    private int id;
    private List<Spaceship> spaceships;
    private User vendor;
    private User buyer;
    private int priceCents;

    public Transaction(List<Spaceship> spaceships, User vendor, User buyer, int priceCents) {
        this.spaceships = spaceships;
        this.vendor = vendor;
        this.buyer = buyer;
        this.priceCents = priceCents;
    }

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Spaceship> getSpaceships() {
        return spaceships;
    }

    public void setSpaceships(List<Spaceship> spaceships) {
        this.spaceships = spaceships;
    }

    public User getVendor() {
        return vendor;
    }

    public void setVendor(User vendor) {
        this.vendor = vendor;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public int getPriceCents() {
        return priceCents;
    }

    public void setPriceCents(int priceCents) {
        this.priceCents = priceCents;
    }
}
