package br.com.nexfar.applicationtest.Model;

import java.util.Date;
import java.util.Objects;

public class Review {
    private String _id;
    private Date date;
    private String listing_id;
    private String reviewer_id;
    private String reviewer_name;
    private String comments;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getListing_id() {
        return listing_id;
    }

    public void setListing_id(String listing_id) {
        this.listing_id = listing_id;
    }

    public String getReviewer_id() {
        return reviewer_id;
    }

    public void setReviewer_id(String reviewer_id) {
        this.reviewer_id = reviewer_id;
    }

    public String getReviewer_name() {
        return reviewer_name;
    }

    public void setReviewer_name(String reviewer_name) {
        this.reviewer_name = reviewer_name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Objects.equals(get_id(), review.get_id()) && Objects.equals(getDate(), review.getDate()) && Objects.equals(getListing_id(), review.getListing_id()) && Objects.equals(getReviewer_id(), review.getReviewer_id()) && Objects.equals(getReviewer_name(), review.getReviewer_name()) && Objects.equals(getComments(), review.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getDate(), getListing_id(), getReviewer_id(), getReviewer_name(), getComments());
    }
}