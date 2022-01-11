package br.com.nexfar.applicationtest.Model;

import java.util.Objects;

public class Address {
    private String market;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getMarket(), address.getMarket());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMarket());
    }
}