package com.bilplay.model;

public class Game {
    private int id;
    private String name;
    private double price;
    private String description;
    private int mode;
    private String pic;
    private Double rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getPic(){
      return pic;
    }

    public void setPic(String url){
      this.pic = url;
    }

    public Double getRating(){
      return rating;
    }

    public void setRating(Double rating){
      this.rating = rating;
    }
}
