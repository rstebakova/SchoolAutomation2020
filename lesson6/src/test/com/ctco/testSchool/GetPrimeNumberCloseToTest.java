package com.ctco.testSchool;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

public class GetPrimeNumberCloseToTest {

    @Test
    public void testGetPrimeNumberCloseToHappyPath() {
        Assert.assertThat("", Team.getPrimeNumberClosesTo(8), is(7));

    }

    @Test
    public void testGetPrimeNumberCloseOne() {
        Assert.assertThat("", Team.getPrimeNumberClosesTo(3), is(3));
    }

    @Test
    public void testGetPrimeNumberCloseSecond() {
        Assert.assertThat("", Team.getPrimeNumberClosesTo(9), is(11));
    }

    @Test
    public void testGetPrimeNumberCloseThird() {
        int result = Team.getPrimeNumberClosesTo(9);
        if ((result == 7 || result == 11)) {
            Assert.assertTrue(true);
        } else {
            fail("Returned" + result + "expected was 7 or 11");
        }
    }

    @Test
    public void testGetPrimeNumberCloseFourth() {
        int result = Team.getPrimeNumberClosesTo(29);
        assertTrue("Returned " + result + " expected was 7 or 11", ((result == 7 || result == 11)));
    }

    @Test
    public void testGetHelloWorldText() {
       assertThat("",Team.getHelloWorldText(),is ("Hello world!"));

    }

    @Test
    public void testGetHelloWorldTextSecond() {
        String result  =  Team.getHelloWorldText();
        String word = "good";
       assertTrue("Returned " + result + "doesn'have this word -" + word,result.contains(word));

    }


    @Test
    public void testGetHelloWorldTextThird() {
        String resultA = "Good morning world!";
        String resultB = "Good day world!";
        String resultC = "Hello world!";
        String resultD  = "Good night world!";
        String result  =  Team.getHelloWorldText();
        if ((result == resultA || result == resultB || result == resultC || resultD == result))
        {
            Assert.assertTrue(true);
        } else {
            fail("Returned result" + result + "is incorrect");
        }
    }
}
