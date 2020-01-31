package com.ctco.testSchool;

import org.junit.Assert;
import org.junit.Test;

public class BlackBoxTesting {

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
}
