package com.panos.lenovoppc.lighterapp;

/**
 * Created by Lenovo pPc on 25-Jan-18.
 */

public class PlayerModel {

    private String name;
    private int image;

    public PlayerModel(String n, int i){
        name = n;
        image = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
