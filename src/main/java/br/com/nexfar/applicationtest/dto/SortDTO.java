package br.com.nexfar.applicationtest.dto;

import java.util.Objects;

public class SortDTO {
    private String order;
    private String property;

    public SortDTO(String order, String propety) {
        this.order = order;
        this.property = propety;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SortDTO)) return false;
        SortDTO sortDTO = (SortDTO) o;
        return Objects.equals(getOrder(), sortDTO.getOrder()) && Objects.equals(getProperty(), sortDTO.getProperty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getProperty());
    }
}
