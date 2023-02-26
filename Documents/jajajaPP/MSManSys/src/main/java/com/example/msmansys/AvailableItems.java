package com.example.msmansys;

public class AvailableItems {
    private String ItemType;
    private String Model;
    private String version;
    private String cost;
    private String price;
    private String available;

    public AvailableItems(String itemType, String model, String version, String cost, String price, String available) {
        ItemType = itemType;
        Model = model;
        this.version = version;
        this.cost = cost;
        this.price = price;
        this.available = available;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
