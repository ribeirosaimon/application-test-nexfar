package br.com.nexfar.applicationtest.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "listingsAndReviews")
public class ListingAndReview {
    @Id
    private String _id;
    private String name;
    private String description;
    private int bedrooms;
    private Float price;
    private Date last_review;
    private Set<Review> reviews = new HashSet<>();
    private Address address;


    public ListingAndReview(){

    }
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

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }


    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getLast_review() {
        return last_review;
    }

    public void setLast_review(Date last_review) {
        this.last_review = last_review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListingAndReview)) return false;
        ListingAndReview that = (ListingAndReview) o;
        return getBedrooms() == that.getBedrooms() && Objects.equals(get_id(), that.get_id()) && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(getReviews(), that.getReviews()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getLast_review(), that.getLast_review());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getName(), getDescription(), getBedrooms(), getPrice(), getReviews(), getAddress(), getLast_review());
    }

}
