package com.example.edesia.presentation;

import java.util.List;
public class RecipeModel {
    int ID;
    String URL, Title, PrepTime, TotalTime, Ingredients, Instructions, Picture;

    public RecipeModel(int ID, String URL, String title, String prepTime, String totalTime, String ingredients, String instructions, String picture) {
        this.ID = ID;
        this.URL = URL;
        this.Title = title;
        this.PrepTime = prepTime;
        this.TotalTime = totalTime;
        this.Ingredients = ingredients;
        this.Instructions = instructions;
        this.Picture = picture;
    }

    public RecipeModel() {

    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setPrepTime(String prepTime) {
        PrepTime = prepTime;
    }

    public void setTotalTime(String totalTime) {
        TotalTime = totalTime;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public void setInstructions(String instructions) {
        Instructions = instructions;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public int getID() {
        return ID;
    }

    public String getURL() {
        return URL;
    }

    public String getTitle() {
        return Title;
    }

    public String getPrepTime() {
        return PrepTime;
    }

    public String getTotalTime() {
        return TotalTime;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public String getInstructions() {
        return Instructions;
    }

    public String getPicture() {
        return Picture;
    }
}

