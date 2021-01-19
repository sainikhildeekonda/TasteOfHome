package com.example.tasteofhome;

public class ContentRecipeAdd {
    private String recipename;
    private String recipeprocedure;

    public ContentRecipeAdd() {
    }


    public ContentRecipeAdd(String recipename, String recipeprocedure) {
        this.recipename = recipename;
        this.recipeprocedure = recipeprocedure;
    }

    public String getRecipename() {
        return recipename;
    }

    public void setRecipename(String recipename) {
        this.recipename = recipename;
    }

    public String getRecipeprocedure() {
        return recipeprocedure;
    }

    public void setRecipeprocedure(String recipeprocedure) {
        this.recipeprocedure = recipeprocedure;
    }
}