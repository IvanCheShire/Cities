package ru.geekbrains.cities;

public class Soc {
    private String description;
    private int picture;
    private boolean like;
    public Soc(String description, int picture, boolean like){
        this.description=description;
        this.picture=picture;
        this.like=like;
    }


    public String getDescription(){
        return description;
    }

    public int getPicture(){
        return picture;
    }

    public boolean getLike(){
        return like;
    }
}
