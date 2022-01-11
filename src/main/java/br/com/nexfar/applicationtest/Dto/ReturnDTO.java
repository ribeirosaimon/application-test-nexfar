package br.com.nexfar.applicationtest.Dto;

public class ReturnDTO {
    private String id;
    private String market;
    private String description;
    private String name;



    public ReturnDTO(String id, String market, String description, String name) {
        this.id = id;
        this.market = market;
        this.description = description;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
