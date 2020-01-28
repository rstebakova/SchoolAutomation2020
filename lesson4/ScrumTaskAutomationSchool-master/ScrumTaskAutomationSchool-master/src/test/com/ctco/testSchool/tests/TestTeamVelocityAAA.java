package com.ctco.testSchool.tests;

import static org.hamcrest.core.Is.is;

import java.util.Arrays;

import com.ctco.testSchool.Member;
import com.ctco.testSchool.Member.type;
import com.ctco.testSchool.Story;
import com.ctco.testSchool.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTeamVelocityAAA {

    Team myTeam = new Team();

    @Test
    public void emptyTeamTest() {
        Assert.assertEquals("Empty team has zero velicity", 0.0, myTeam.getTeamVelocity(), 0.1);
    }

    @Test
    public void happyPathTest() {
        Member member = new Member();
        myTeam.addMember(member);
        Assert.assertEquals("By default my velocity is 1 per 10 days of sprint", 10.0, myTeam.getTeamVelocity(), 0.1);
    }

    @Test
    public void customSprintLength() {
        Member member = new Member();
        myTeam.addMember(member);
        myTeam.sprintDays = 9;
        Assert.assertEquals("Sprint is 9 days long", 9.0, myTeam.getTeamVelocity(), 0.1);
    }


    @Test
    public void testCanDeliverPositiveHappyPath() {
        Member first_dev = new Member(type.DEV);
        Member second_dev = new Member(type.DEV);
        Member first_qa = new Member(type.TEST);
        myTeam.addMember(first_dev);
        myTeam.addMember(second_dev);
        myTeam.addMember(first_qa);

        first_dev.setVelocity(2.0);
        second_dev.setVelocity(4.0);
        first_qa.setVelocity(0.5);

        Story first_story = new Story();
        first_story.setStoryPoints(1);

        Story second_story = new Story();
        second_story.setStoryPoints(4);

        myTeam.backlog = Arrays.asList(first_story, second_story);
        Assert.assertThat("Can't be delivered. Team velocity is less than Story points", myTeam.canDeliver(), is(true));
    }

    @Test
    public void testCanDeliverNegative() {
        Member first_dev = new Member(type.DEV);
        Member second_dev = new Member(type.DEV);
        Member first_qa = new Member(type.TEST);
        myTeam.addMember(first_dev);
        myTeam.addMember(second_dev);
        myTeam.addMember(first_qa);

        first_dev.setVelocity(3.0);
        second_dev.setVelocity(1.0);
        first_qa.setVelocity(0.5);

        Story first_story = new Story();
        first_story.setStoryPoints(3);

        Story second_story = new Story();
        second_story.setStoryPoints(3);

        myTeam.backlog = Arrays.asList(first_story,second_story);
        Assert.assertThat("Can't be delivered. Team velocity is less than Story points", myTeam.canDeliver(), is(false));
    }

    @Test
    public void testCanDeliverPositiveVelocityEqualToStoryPoints() {
        Member first_dev = new Member(type.DEV);
        Member second_dev = new Member(type.DEV);
        Member first_qa = new Member(type.TEST);
        myTeam.addMember(first_dev);
        myTeam.addMember(second_dev);
        myTeam.addMember(first_qa);

        first_dev.setVelocity(2.0);
        second_dev.setVelocity(1.0);
        first_qa.setVelocity(0.5);

        Story first_story = new Story();
        first_story.setStoryPoints(3);

        Story second_story = new Story();
        second_story.setStoryPoints(3);

        myTeam.backlog = Arrays.asList(first_story);
        Assert.assertThat("Can't be delivered. Team velocity is less than Story points", myTeam.canDeliver(), is(true));
    }

    @Test
    public void testCanDeliverIfStoryPointsIsNull() {
        Member first_dev = new Member(type.DEV);
        Member second_dev = new Member(type.DEV);
        Member first_qa = new Member(type.TEST);
        myTeam.addMember(first_dev);
        myTeam.addMember(second_dev);
        myTeam.addMember(first_qa);

        first_dev.setVelocity(2.1);
        second_dev.setVelocity(2.0);
        first_qa.setVelocity(2.0);

        Story first_story = new Story();
        first_story.setStoryPoints(0);

        Story second_story = new Story();
        second_story.setStoryPoints(0);

        myTeam.backlog = Arrays.asList(first_story,second_story);
        Assert.assertThat("Can't be delivered. Team velocity is less than Story points", myTeam.canDeliver(), is(true));
    }

    @Test
    public void testCanDeliverIfVelocityNull() {
        Member first_dev = new Member(type.DEV);
        Member second_dev = new Member(type.DEV);
        Member first_qa = new Member(type.TEST);
        myTeam.addMember(first_dev);
        myTeam.addMember(second_dev);
        myTeam.addMember(first_qa);

        first_dev.setVelocity(0);
        second_dev.setVelocity(0);
        first_qa.setVelocity(0);

        Story first_story = new Story();
        first_story.setStoryPoints(3);

        Story second_story = new Story();
        second_story.setStoryPoints(3);
//defect - story should be added to backlog
        myTeam.backlog = Arrays.asList();
        Assert.assertThat("Can't be delivered. Team velocity is less than Story points", myTeam.canDeliver(), is(false));
    }

    @Test
    public void testCanDeliverNegativeVelocity() {
        Member first_dev = new Member(type.DEV);
        Member second_dev = new Member(type.DEV);
        Member first_qa = new Member(type.TEST);
        myTeam.addMember(first_dev);
        myTeam.addMember(second_dev);
        myTeam.addMember(first_qa);

        first_dev.setVelocity(-1.0);
        second_dev.setVelocity(-2.0);
        first_qa.setVelocity(0.0);

        Story first_story = new Story();
        first_story.setStoryPoints(3);

        Story second_story = new Story();
        second_story.setStoryPoints(3);
        //defect - velocity should not be negative
        myTeam.backlog = Arrays.asList();
        Assert.assertThat("Can't be delivered. Team velocity is less than Story points", myTeam.canDeliver(), is(false));
    }
}
