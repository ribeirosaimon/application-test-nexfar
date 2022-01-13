package br.com.nexfar.applicationtest.Dto;

import java.util.Objects;

public class PriceRangeDTO {
    private Double min;
    private Double max;

    public PriceRangeDTO(Double min, Double max) {
        this.min = min;
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceRangeDTO)) return false;
        PriceRangeDTO that = (PriceRangeDTO) o;
        return Objects.equals(getMin(), that.getMin()) && Objects.equals(getMax(), that.getMax());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMin(), getMax());
    }

    @Override
    public String toString() {
        return "PriceRangeDTO{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
