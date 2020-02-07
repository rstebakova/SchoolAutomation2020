package com.ctco.testSchool;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

import java.util.Arrays;

import com.ctco.testSchool.Member.type;
import org.junit.Assert;
import org.junit.Test;

public class BlackBoxTesting {

    Team myTeam = new Team();

    Member first_qa = new Member(type.TEST);

    Member second_qa = new Member(type.TEST);

    Member first_dev = new Member(type.DEV);

    Member second_dev = new Member(type.DEV);


    /*Happy Path: team can deliver stories. 1 tester and  1 first_dev work 1 story; */
    @Test
    public void happyCanDeliverQualityTestOneTestersOneDeveloper() {
        // add team members
        myTeam.addMember(first_qa);
        first_qa.setVelocity(1);
        myTeam.addMember(first_dev);

        // define stories per scope
        Story first_story = new Story();
        first_story.setStoryPoints(3);
        first_story.setTestPoints(2);

        myTeam.backlog = Arrays.asList(first_story);
        Assert.assertThat("Can be delivered.", myTeam.canDeliverQuality(), is(true));
    }

    /* - Happy Path: team can deliver stories. 2 tester and  2 first_dev work */
    @Test
    public void happyCanDeliverQualityTestAllTeam() {


        // add qa team members
        myTeam.addMember(first_qa);
        first_qa.setVelocity(1);

        myTeam.addMember(first_qa);
        second_qa.setVelocity(1);

        // add dev team members
        myTeam.addMember(first_dev);
        myTeam.addMember(second_dev);

        // define stories per scope
        Story first_story = new Story();
        first_story.setStoryPoints(3);
        first_story.setTestPoints(2);

        Story second_story = new Story();
        second_story.setStoryPoints(2);
        second_story.setTestPoints(3);

        Story third_story = new Story();
        third_story.setStoryPoints(5);
        third_story.setTestPoints(5);

        myTeam.backlog = Arrays.asList(first_story,second_story);
        Assert.assertThat("Can be delivered.", myTeam.canDeliverQuality(), is(true));
    }

    /*Negative case: team can not deliver stories. with one tester in team and 2 devs work in parallel;*/
    @Test
    public void negativeCanDeliverQualityTestTwoDevelopersOneTester() {

        // add qa team members
        myTeam.addMember(first_qa);
        first_qa.setVelocity(0.5);

        // add dev team members
        myTeam.addMember(first_dev);
        myTeam.addMember(second_dev);

        // define stories per scope
        Story first_story = new Story();
        first_story.setStoryPoints(3);
        first_story.setTestPoints(2);

        Story second_story = new Story();
        second_story.setStoryPoints(2);
        second_story.setTestPoints(3);

        Story third_story = new Story();
        third_story.setStoryPoints(5);
        third_story.setTestPoints(5);

        myTeam.backlog = Arrays.asList(first_story,third_story);
        Assert.assertThat("Should be False. Can't be delivered. Tester won't have enaugh time ", myTeam.canDeliverQuality(), is(false));
    }

  /* - Negative case: team can not deliver stories. with two tester in team and 1 devs work if sprint is short;*/
    @Test
    public void negativeCanDeliverQualityShortSprint() {
        myTeam.sprintDays = 5;

        myTeam.addMember(first_qa);
        first_qa.setVelocity(1);
        myTeam.addMember(second_qa);
        second_qa.setVelocity(1);

        myTeam.addMember(first_dev);

        // define stories per scope
        Story first_story = new Story();
        first_story.setStoryPoints(3);
        first_story.setTestPoints(3);

        myTeam.backlog = Arrays.asList(first_story);
        Assert.assertThat("Can't be delivered. Sprint is too short", myTeam.canDeliverQuality(), is(false));
    }

    /* - Negative Path: team can not deliver stories if testers velocity = 0*/
    @Test
    public void negativeCanDeliverQualityWithSlowTeamMembers() {
        // add sprint days
        myTeam.sprintDays = 5;

        // add qa member with velocity 0.5
        myTeam.addMember(first_qa);
        first_qa.setVelocity(0.5);

        // add first_dev member with velocity 0.5
        myTeam.addMember(first_dev);
        first_dev.setVelocity(0.5);

        // define story per scope
        Story first_story = new Story();
        first_story.setStoryPoints(3);
        first_story.setTestPoints(3);

        // add story to backlog
        myTeam.backlog = Arrays.asList(first_story);

        //verify that scope can't be delivered
        Assert.assertThat("Can't be delivered if tester velocity is null", myTeam.canDeliverQuality(), is(false));
    }

    /*Negative Path: team can not deliver stories if first_dev's velocity = 0 */
    @Test
    public void positiveCanDeliverQualityWitSlowDevAndFastTester() {

        // add sprint days
        myTeam.sprintDays = 0;

        // add qa member with velocity 1
        myTeam.addMember(first_qa);
        first_qa.setVelocity(1);

        // add first_dev member with velocity 0.5
        myTeam.addMember(first_dev);
        first_dev.setVelocity(0.5);

        // define story per scope
        Story first_story = new Story();
        first_story.setStoryPoints(1);
        first_story.setTestPoints(3);

        // add story to backlog
        myTeam.backlog = Arrays.asList(first_story);

        //verify that scope can't be delivered
       try {
          myTeam.getTeamVelocity();
          fail("Should be error msg");

       } catch (Exception e){

       }
    }
    //домашка написать тесты на ршибки Validate inputs - 3 exceptions



}
