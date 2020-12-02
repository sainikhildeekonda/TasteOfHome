package com.example.tasteofhome;

public class ContentRecipeAdd {
    public String recipename1;
    public String recipepro1;

    public ContentRecipeAdd()
    {

    }

    public ContentRecipeAdd(String recipename, String recipepro)
    {
        this.recipename1 = recipename;
        this.recipepro1 = recipepro;
    }

    public String getRecipename1() {
        return recipename1;
    }

    public void setRecipename1(String recipename1) {
        this.recipename1 = recipename1;
    }

    public String getRecipepro1() {
        return recipepro1;
    }

    public void setRecipepro1(String recipepro1) {
        this.recipepro1 = recipepro1;
    }
}