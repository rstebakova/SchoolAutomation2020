package com.ctco.testSchool;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

public class TestFirstClass {

    @Test
    //First Task
    public void firstTestHappyPath() {
        Assert.assertThat("", FirstTask.concatenate(1, 2, "test s"), is("4test s"));
    }

    @Test
    public void firstTestZero() {
        Assert.assertThat("", FirstTask.concatenate(0, 0, ""), is("1"));
    }

    @Test
    public void firstTestThird() {
        Assert.assertThat("", FirstTask.concatenate(-1, 0, null), is("0null"));
    }

    // Second Part
    @Test
    public void secondTaskHappyPath() {
        Assert.assertThat("", new SecondTask().isEven(4), is(true));
    }

    @Test
    public void secondTaskNegative() {
        Assert.assertThat("", new SecondTask().isEven(-5), is(false));
    }

    @Test
    public void secondTaskZero() {
        Assert.assertThat("", new SecondTask().isEven(0), is(true));
    }

    //Third Task

        @Test
        public void thirdTaskHappyPath() {
            String[] words = {"cat","puppy"};
            Assert.assertThat("", new ThirdTask().getElementPosition(words,"cat"), is(0));
        }

    //    @Test
    //    public void thirdTaskNegative() {
    //        Assert.assertThat("", new SecondTask().isEven(-5), is(false));
    //    }
    //
    //    @Test
    //    public void thirdTaskZero() {
    //        Assert.assertThat("", new ThirdTask());
    //    }

    //Fourth Task

    @Test
    public void fourthTaskHappyPath() {
        Assert.assertThat("", new FourthTask(2, 2).getPerimeter(), is(8));
    }

    @Test
    public void fourthTaskNegative() {
        Assert.assertThat("", new FourthTask(-1, 1).getPerimeter(), is(0.0));
    }

    @Test
    public void fourthTaskZero() {
        Assert.assertThat("", new FourthTask(0, -1).getPerimeter(), is(-2.0));
    }
    //Fifth Tests

    @Test
    public void fifthTaskHappyPath() {
        Assert.assertThat("", new FifthTask().isRectangle(new FourthTask(2, 2)), is("true"));
    }

    @Test
    public void fifthTaskNegative() {
        Assert.assertThat("", new FifthTask().isRectangle(new FourthTask(0, -2)), is("true"));
    }

    //Sixth Tests

    @Test
    public void sixTaskHappyPath() {
        Assert.assertThat("", SixthTask.isNumberPositive(2), is("positive"));
    }

    @Test
    public void sixTaskNegative() {
        Assert.assertThat("", SixthTask.isNumberPositive(-2), is("negative"));
    }

    @Test
    public void sixTaskZer() {
        Assert.assertThat("", SixthTask.isNumberPositive(0), is("positive"));
    }

    //
    //    @Test
    //    public void fifthTaskNegative() {
    //        Assert.assertThat("", new FourthTask(-1,1).getPerimeter(), is(0.0));
    //    }
    //
    //    @Test
    //    public void fifthTaskZero() {
    //        Assert.assertThat("", new FifthTask(0,-1).getPerimeter(), is(-2.0));
    //    }
}
