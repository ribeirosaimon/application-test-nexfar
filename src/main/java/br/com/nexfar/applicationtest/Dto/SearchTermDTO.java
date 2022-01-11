package br.com.nexfar.applicationtest.Dto;

import java.util.Objects;

public class SearchTermDTO {
    private String searchTerm;
    private Long minReview;
    private PriceRangeDTO priceRange;
    private Integer totalBedrooms;
    private SortDTO sort;

    public SearchTermDTO(String searchTerm, Long minReview, PriceRangeDTO priceRange, Integer totalBedrooms, SortDTO sort) {
        this.searchTerm = searchTerm;
        this.minReview = minReview;
        this.priceRange = priceRange;
        this.totalBedrooms = totalBedrooms;
        this.sort = sort;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public Long getMinReview() {
        return minReview;
    }

    public void setMinReview(Long minReview) {
        this.minReview = minReview;
    }

    public PriceRangeDTO getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(PriceRangeDTO priceRange) {
        this.priceRange = priceRange;
    }

    public Integer getTotalBedrooms() {
        return totalBedrooms;
    }

    public void setTotalBedrooms(Integer totalBedrooms) {
        this.totalBedrooms = totalBedrooms;
    }

    public SortDTO getSort() {
        return sort;
    }

    public void setSort(SortDTO sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchTermDTO)) return false;
        SearchTermDTO that = (SearchTermDTO) o;
        return Objects.equals(getSearchTerm(), that.getSearchTerm()) && Objects.equals(getMinReview(), that.getMinReview()) && Objects.equals(getPriceRange(), that.getPriceRange()) && Objects.equals(getTotalBedrooms(), that.getTotalBedrooms()) && Objects.equals(getSort(), that.getSort());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSearchTerm(), getMinReview(), getPriceRange(), getTotalBedrooms(), getSort());
    }
}

