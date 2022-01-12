package br.com.nexfar.applicationtest.Model;

import java.util.Objects;

public class Review {
    private String _id;

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Objects.equals(get_id(), review.get_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id());
    }
}