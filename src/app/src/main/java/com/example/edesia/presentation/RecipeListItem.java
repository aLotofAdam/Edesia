package com.example.edesia.presentation;

public class RecipeListItem {
    private String recipename;
    private String ingrediates;
    private String totalTime;
    public RecipeListItem(String recipename, String ingrediates, String totalTime){
        this.recipename = recipename;
        this.ingrediates = ingrediates;
        this.totalTime= totalTime;
    }
    public String getRecipename(String recipename){
        //SQL query to retrieve the recipe name
        return recipename;
    }

    public String getIngrediates(String ingrediates){
        //SQL query to retrieve ingredediates
        return ingrediates;
    }
    public String getTotalTime(String totalTime){
        //SQL query to retrieve totalTime
        return totalTime;
    }

}
