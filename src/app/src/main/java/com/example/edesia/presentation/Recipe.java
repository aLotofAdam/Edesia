package com.example.edesia.presentation;

public class Recipe {
    String URL, Title, PrepTime,TotalTime,Ingredients,Instructions;


    public Recipe(String URL, String title, String prepTime, String totalTime, String ingredients, String instructions) {
        this.URL = URL;
        Title = title;
        PrepTime = prepTime;
        TotalTime = totalTime;
        Ingredients = ingredients;
        Instructions = instructions;
    }

    public Recipe() {
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setPrepTime(String prepTime){this.PrepTime = prepTime;
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
}
