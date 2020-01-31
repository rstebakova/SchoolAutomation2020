package com.ctco.testSchool;

import static org.hamcrest.core.Is.is;

import java.util.Arrays;

import com.ctco.testSchool.Member.type;
import org.junit.Assert;
import org.junit.Test;

public class WhiteBoxTesting {

    Team myTeam = new Team();

    @Test
    public void testCanDeliverQualityPositiveHappyPath() {
        Member first_qa = new Member(type.TEST);
        myTeam.addMember(first_qa);

        first_qa.setVelocity(0.5);

        Story first_story = new Story();
        first_story.setTestPoints(2);

        Story second_story = new Story();
        second_story.setTestPoints(3);

        myTeam.backlog = Arrays.asList(first_story, second_story);
        Assert.assertThat("Can't be delivered. Team velocity is less than Tess Points", myTeam.canDeliverQuality(), is(true));
    }

    // defect
    @Test
    public void testCanDeliverQualityNegative() {
        Member first_qa = new Member(type.TEST);
        myTeam.addMember(first_qa);

        first_qa.setVelocity(0.5);

        Story first_story = new Story();
        first_story.setTestPoints(3);

        Story second_story = new Story();
        second_story.setTestPoints(3);

        myTeam.backlog = Arrays.asList(first_story, second_story);
        Assert.assertThat("Inccorect. Team velocity is less than Story points", myTeam.canDeliverQuality(), is(false));
    }

    @Test
    public void testCanDeliverQualityPositiveNegativeValue() {
        Member first_qa = new Member(type.TEST);
        myTeam.addMember(first_qa);

        first_qa.setVelocity(0.5);

        Story first_story = new Story();
        first_story.setTestPoints(-5);

        Story second_story = new Story();
        second_story.setTestPoints(-3);

        myTeam.backlog = Arrays.asList(first_story, second_story);
        Assert.assertThat("Can't be delivered. Team velocity is less than Story points", myTeam.canDeliverQuality(), is(true));
    }
// Is it correct behaviour
    @Test
    public void testCanDeliverQualityPositiveWithoutStoryInBacklog() {
        Member first_qa = new Member(type.TEST);
        myTeam.addMember(first_qa);

        first_qa.setVelocity(0.5);

        Story first_story = new Story();
        first_story.setTestPoints(5);

        Story second_story = new Story();
        second_story.setTestPoints(3);

        myTeam.backlog = Arrays.asList();
        Assert.assertThat("Can't be delivered. Team velocity is less than Story points", myTeam.canDeliverQuality(), is(true));

    }
// Defect?
    @Test
    public void testCanDeliverQualityPositiveWithTwoTesters() {
        Member first_qa = new Member(type.TEST);
        myTeam.addMember(first_qa);

        first_qa.setVelocity(0.5);

        Member second_qa = new Member(type.TEST);
        myTeam.addMember(second_qa);
        first_qa.setVelocity(1.0);

        Story first_story = new Story();
        first_story.setTestPoints(5);

        Story second_story = new Story();
        second_story.setTestPoints(11);

        myTeam.backlog = Arrays.asList();
        Assert.assertThat("Can't be delivered. Team velocity is less than Story points", myTeam.canDeliverQuality(), is(false));

    }
}