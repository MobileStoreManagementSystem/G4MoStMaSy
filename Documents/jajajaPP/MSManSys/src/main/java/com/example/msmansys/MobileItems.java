package com.example.msmansys;

public class MobileItems {
    String price;
    String model;

    public MobileItems(String model, String version, String price) {
        this.price = price;
        this.model = model;
        this.version = version;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    String version;

}
