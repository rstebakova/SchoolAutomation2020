package com.ctco.testSchool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import com.ctco.testSchool.Member.type;
import org.junit.Test;

public class ValidationInputTest {

    Team myTeam = new Team();

    Member first_qa = new Member(type.TEST);

    /* Verify exception msg "Sprint should be at least two days long", if Sprint Days  = 0*/
    @Test
    public void testValidationInputZeroSprint() {
        first_qa.setVelocity(1);
        myTeam.addMember(first_qa);

        Story first_story = new Story();
        first_story.setStoryPoints(3);
        first_story.setTestPoints(2);

        myTeam.backlog = Arrays.asList(first_story);
        myTeam.sprintDays = 0;

        try {
            myTeam.canDeliverQuality();
            fail("No expected exception: " + IllegalArgumentException.class.getSimpleName() + "has been thrown");

        } catch (IllegalArgumentException e) {
            System.out.println(e);
            String message = "Sprint should be at least two days long";
            assertEquals(message, e.getMessage());
        }
    }

    /* Verify exception msg "Sprint should be at least two days long", if Sprint Days  = 1*/
    @Test
    public void testValidationInputOneDaySprint() {
        first_qa.setVelocity(1);
        myTeam.addMember(first_qa);

        Story first_story = new Story();
        first_story.setStoryPoints(3);
        first_story.setTestPoints(2);

        myTeam.backlog = Arrays.asList(first_story);
        myTeam.sprintDays = 1;

        try {
            myTeam.canDeliverQuality();
            fail("No expected exception: " + IllegalArgumentException.class.getSimpleName() + "has been thrown");

        } catch (IllegalArgumentException e) {

            System.out.println(e);
            String message = "Sprint should be at least two days long";
            assertEquals(message, e.getMessage());
        }
    }

    /* Verify exception msg "Velocity should be positive", if Velocity = -1*/
    @Test
    public void testValidationInputNegativeVelocity() {
        first_qa.setVelocity(-1);
        myTeam.addMember(first_qa);

        Story first_story = new Story();
        first_story.setStoryPoints(3);
        first_story.setTestPoints(2);

        myTeam.backlog = Arrays.asList(first_story);
        myTeam.sprintDays = 10;

        try {
            myTeam.canDeliverQuality();
            fail("No expected exception: " + IllegalArgumentException.class.getSimpleName() + "has been thrown");

        } catch (IllegalArgumentException e) {
            System.out.println(e);
            String message = "Velocity should be positive";
            assertEquals(message, e.getMessage());
        }
    }

    /* Verify exception msg "Velocity should be positive", if Velocity = 2*/
    @Test
    public void testValidationInputMoreThanOneVelocity() {
        first_qa.setVelocity(2);
        myTeam.addMember(first_qa);

        Story first_story = new Story();
        first_story.setStoryPoints(3);
        first_story.setTestPoints(2);

        myTeam.backlog = Arrays.asList(first_story);
        myTeam.sprintDays = 10;

        try {
            myTeam.canDeliverQuality();
            fail("No expected exception: " + IllegalArgumentException.class.getSimpleName() + "has been thrown");

        } catch (IllegalArgumentException e) {
            System.out.println(e);
            String message = "Velocity can't be more than 1";
            assertEquals(message, e.getMessage());
        }
    }
}