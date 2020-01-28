package com.ctco.testSchool;

public class Story {
    int value;
    int storyPoints;
    int testPoints;
    type type=null;
    public enum type {DEV, TEST, DB, BA}

    public void setStoryPoints (int points){
        this.storyPoints = points;
    }

    public int getStoryPoints(){
        return storyPoints;
    }

    public void setValue (int value){
        this.value = value;
    }

    public void setType (type type){
        this.type = type;
    }
}
