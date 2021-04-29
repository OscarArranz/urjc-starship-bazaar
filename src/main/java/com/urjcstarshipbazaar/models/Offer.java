package com.urjcstarshipbazaar.models;

import com.urjcstarshipbazaar.models.spaceships.Spaceship;

import java.util.Date;
import java.util.List;

public class Offer {

    private int id;
    private List<Spaceship> spaceships;
    private User vendor;
    private int price;
    private Date deadline;

    public Offer() {    }

    public int getId() {
        return id;
    }

    public List<Spaceship> getSpaceships() {
        return spaceships;
    }

    public User getVendor() {
        return vendor;
    }

    public int getPrice() {
        return price;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpaceships(List<Spaceship> spaceships) {
        this.spaceships = spaceships;
    }

    public void setVendor(User vendor) {
        this.vendor = vendor;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

}
