import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import my.Resource.ResourcePage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Lesson8Homework {

    private static RequestSpecification spec2;

    @BeforeAll
    public static void init() {

        spec2 = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setProxy("gate.swissre.com", 9443)
                .setBaseUri("https://reqres.in/api")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();

    }

    @Test
    public void listResourceName() {
        ResourcePage resources = given()
                .spec(spec2)
                .when()
                .get("unknown")
                .then()
                .statusCode(200)
                .extract().as(ResourcePage.class);

        assertEquals(1, resources.getData()[0].getId());
        assertThat("Count if total pages is " + resources.getTotal_pages(), resources.isTotalPageMoreOrEquals(2), is(true));
        assertThat("Count is incorrect", resources.countOfItems("true red"), is(1));

    }

    @Test
    public void testLoginUnsuccessful() {

        String payload = "{\n" +
                "    \"email\": \"peter@klaven\"}";
        UserResponse response = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("login")
                .then().statusCode(400)
                .and()
                .extract().as(UserResponse.class);

        assertEquals("Missing password", response.getError(), "Message is incorrect");
    }
}
