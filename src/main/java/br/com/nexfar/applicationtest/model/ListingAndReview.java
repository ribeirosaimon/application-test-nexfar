package br.com.nexfar.applicationtest.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document(collection = "listingsAndReviews")
public class ListingAndReview {
    @Id
    private String _id;
    private String name;
    private String description;
    private Date last_review;
    private Integer bedrooms;
    private Double price;
    private Address address;
    private Integer number_of_reviews;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLast_review() {
        return last_review;
    }

    public void setLast_review(Date last_review) {
        this.last_review = last_review;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getNumber_of_reviews() {
        return number_of_reviews;
    }

    public void setNumber_of_reviews(Integer number_of_reviews) {
        this.number_of_reviews = number_of_reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListingAndReview)) return false;
        ListingAndReview that = (ListingAndReview) o;
        return Objects.equals(_id, that._id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(last_review, that.last_review) && Objects.equals(bedrooms, that.bedrooms) && Objects.equals(price, that.price) && Objects.equals(address, that.address) && Objects.equals(number_of_reviews, that.number_of_reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, name, description, last_review, bedrooms, price, address, number_of_reviews);
    }

    @Override
    public String toString() {
        return "ListingAndReview{" +
                "_id='" + _id + '\'' +
                ", last_review=" + last_review +
                ", price=" + price +
                '}';
    }
}
