package com.ctco.testSchool;

public class ThirdTask {


    public int getElementPosition(String[] words, String word) {
        for(int i=0; i<words.length; i++) {
            if(words[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }
}