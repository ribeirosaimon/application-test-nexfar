package br.com.nexfar.applicationtest.Dto;

import java.util.Objects;

public class SortDTO {
    private String order;
    private String propety;

    public SortDTO(String order, String propety) {
        this.order = order;
        this.propety = propety;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPropety() {
        return propety;
    }

    public void setPropety(String propety) {
        this.propety = propety;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SortDTO)) return false;
        SortDTO sortDTO = (SortDTO) o;
        return Objects.equals(getOrder(), sortDTO.getOrder()) && Objects.equals(getPropety(), sortDTO.getPropety());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getPropety());
    }
}
