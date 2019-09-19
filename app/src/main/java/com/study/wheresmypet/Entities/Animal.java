package com.study.wheresmypet.Entities;


public class Animal {
    private String Name;
    private String Tell;
    private String Address;
    private String Type;
    private String Race;
    private String State;
    private String Image;


    public Animal(String name, String tell, String address, String type, String race, String state, String image) {
        Name = name;
        Tell = tell;
        Address = address;
        Type = type;
        Race = race;
        State = state;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTell() {
        return Tell;
    }

    public void setTell(String tell) {
        Tell = tell;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRace() {
        return Race;
    }

    public void setRace(String race) {
        Race = race;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
