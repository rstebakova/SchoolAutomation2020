package com.ctco.testSchool;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public  class TestClass {

}

class FifthTask {
    public String isRectangle(FourthTask fourthTask) {
        if(fourthTask.getPerimeter()==(2*fourthTask.a+2*fourthTask.b)) {
            return "true";
        } return "false";
    }
}

class SixthTask {
    public static String isNumberPositive(int number) {
        while(number>0) {
            return "positive";
        } return "negative";
    }
}

class SeventhTask {
    static class Dog {
        private String name;
        public String breed;
        public Dog(String name) {
            this.name = name;
        }
    }
    public static String getDogBreed(Dog dog) {
        return dog.breed;
    }
}

class EightTask {
    List<String> myList = new ArrayList<>();
    public List<String> addStringsToList(String... args) {
        for(String s : args) {
            myList.add(s);
        }
        return myList;
    }
}

class NinthTask {
    //Method merges two dates into one by choosing the largest value of each component
    public static LocalDateTime merge(String firstDate, String secondDate) {
        final LocalDateTime firstDateTime = LocalDateTime.parse(firstDate);
        final LocalDateTime secondDateTime = LocalDateTime.parse(firstDate);
        return LocalDateTime.of(
                Math.max(firstDateTime.getYear(), secondDateTime.getYear()),
                Math.max(firstDateTime.getMonthValue(), secondDateTime.getMonthValue()),
                Math.max(firstDateTime.getDayOfMonth(), secondDateTime.getDayOfMonth()),
                Math.max(firstDateTime.getHour(), secondDateTime.getHour()),
                Math.max(firstDateTime.getMinute(), secondDateTime.getMinute()),
                Math.max(firstDateTime.getSecond(), secondDateTime.getSecond())
        );
    }
}

