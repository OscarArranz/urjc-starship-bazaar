package com.urjcstarshipbazaar.models;

import java.util.Objects;

public class Review {

    private int id;
    private String comment;
    private double score;
    private User vendor;
    private User buyer;

    public Review(String comment, double score, User vendor, User buyer) {
        this.comment = comment;
        this.score = score;
        this.vendor = vendor;
        this.buyer = buyer;
    }

    public Review() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return vendor.getId() == review.getVendor().getId()
                && buyer.getId() == review.buyer.getId() && comment.equals(review.getComment())
                && review.getScore() == review.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
