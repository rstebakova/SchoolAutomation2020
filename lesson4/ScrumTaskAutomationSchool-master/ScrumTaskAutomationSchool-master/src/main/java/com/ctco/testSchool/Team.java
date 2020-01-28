package com.ctco.testSchool;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Member> members = new ArrayList<>();

    public List<Story> backlog;

    public int sprintDays = 10;

    public void addMember(Member member) {
        members.add(member);
    }

    public Double getTeamVelocity() {
        Double velocity = 0.0;
        for (Member m:members
             ) {velocity+=m.velocity*sprintDays;
        }
        return velocity;
    }

    public boolean canDo() {
        Double capacity = getTeamVelocity();
        for (Story s:backlog
             ) {capacity-=s.storyPoints;
        }
        return  capacity>=0;
    }

    public boolean canDeliverQuality() {
        return members.stream()
                .filter(item -> item.testingSkills)
                .mapToDouble(i->i.velocity)
                .sum() >=  backlog.stream().mapToDouble(s->s.testPoints).sum();
    }

    public boolean canDeliver() {
        return members.stream()
                .filter(item -> item.codinSkills)
                .mapToDouble(i->i.velocity)
                .sum() >=  backlog.stream().mapToDouble(s->s.storyPoints).sum();
    }
}
