package br.com.nexfar.applicationtest.Comparable;

import br.com.nexfar.applicationtest.Model.ListingAndReview;

import java.util.Comparator;

public class ComparatorPrice implements Comparator<ListingAndReview> {

    @Override
    public int compare(ListingAndReview o1, ListingAndReview o2) {
        return Double.compare(o1.getPrice(), o2.getPrice());
    }
}
