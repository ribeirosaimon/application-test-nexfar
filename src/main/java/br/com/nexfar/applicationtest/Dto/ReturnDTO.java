package br.com.nexfar.applicationtest.Dto;

public class ReturnDTO {
    private String market;
    private String description;
    private String name;



    public ReturnDTO(String market, String description, String name) {

        this.market = market;
        this.description = description;
        this.name = name;
    }



    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
