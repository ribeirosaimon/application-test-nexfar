package br.com.nexfar.applicationtest.comparable;

import br.com.nexfar.applicationtest.model.ListingAndReview;

import java.util.Comparator;

public class ComparatorPrice implements Comparator<ListingAndReview> {

    @Override
    public int compare(ListingAndReview o1, ListingAndReview o2) {
        return Double.compare(o1.getPrice(), o2.getPrice());
    }
}
