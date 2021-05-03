package com.urjcstarshipbazaar.models;

import com.urjcstarshipbazaar.models.spaceships.Spaceship;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Offer {

    private int id;
    private List<Spaceship> spaceships;
    private User vendor;
    private int price;
    private Date deadline;

    public Offer() {

    }

    public Offer(int id, List<Spaceship> spaceships, User vendor, int price, Date deadline) {
        this.id = id;
        this.spaceships = spaceships;
        this.vendor = vendor;
        this.price = price;
        this.deadline = deadline;
    }

    public Offer(List<Spaceship> spaceships, User vendor, int price, Date deadline) {
        this.spaceships = spaceships;
        this.vendor = vendor;
        this.price = price;
        this.deadline = deadline;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id == offer.id && price == offer.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price);
    }
}
