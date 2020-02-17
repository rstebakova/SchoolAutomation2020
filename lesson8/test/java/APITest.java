import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import my.Request.UserPage;
import my.Resource.ResourcePage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class APITest {

    private static RequestSpecification spec;

    private static RequestSpecification spec2;

    @BeforeAll
    public static void init() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setProxy("gate.swissre.com", 9443)
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();

        spec2 = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setProxy("gate.swissre.com", 9443)
                .setBaseUri("https://reqres.in/api")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void getTest() {
        User[] users = given()
                .spec(spec)
                .when()
                .get("users")
                .then()
                .statusCode(200)
                .extract().as(User[].class);
        assertEquals(users[0].getId(), 1);
        assertEquals(users[0].username, "Bret");
    }

    @Test
    public void postTest() {

        String payload = "{\n" +
                "  \"userId\": 108,\n" +
                "  \"id\": 108,\n" +
                "  \"title\": \"bazooka\",\n" +
                "  \"body\": \"bamboocha\"\n" +
                "}";

        Response response = given()
                .spec(spec)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("users")
                .then().statusCode(201)
                .and()
                .extract()
                .response();

        String responseBodyString = response.getBody().asString();

        assertEquals("chikipibarum", response.path("title"));
    }

    @Test
    public void someTest() {
        User[] users = given()
                .spec(spec)
                .when()
                .get("users")
                .then()
                .statusCode(200)
                .extract().as(User[].class);
        assertEquals(users[0].getId(), 1);
    }

    @Test
    public void getPosts() {
        Posts[] posts = given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .extract().as(Posts[].class);
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", posts[0].getTitle());
    }

    @Test
    public void getPage() {
        UserPage users = given()
                .spec(spec2)
                .when()
                .get("users?page=2")
                .then()
                .statusCode(200)
                .extract().as(UserPage.class);
        assertEquals(7, users.getData()[0].getId());
    }

    @Test
    public void getPosts2() {
        String payload = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        Response response = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("users?page=2")
                .then().statusCode(200)
                .and()
                .extract()
                .response();

        System.out.println(response);
    }

    @Test
    public void getPosts1() {
        Posts posts = given()
                .spec(spec)
                .when()
                .get("posts/1")
                .then()
                .statusCode(200)
                .extract().as(Posts.class);
        // assertEquals("",posts.getTitle());
        System.out.println(posts.getMyString());
    }

    @Test
    public void getTodos1() {
        Todos todos = given()
                .spec(spec)
                .when()
                .get("todos/1")
                .then()
                .statusCode(200)
                .extract().as(Todos.class);
        assertEquals(1, todos.getId());
        assertEquals(true, todos.getCompleted());

    }

    @Test
    public void getTodos() {
        Todos[] todos = given()
                .spec(spec)
                .when()
                .get("todos")
                .then()
                .statusCode(200)
                .extract().as(Todos[].class);
        assertEquals(false, todos[0].getCompleted());
    }

    @Test
    public void someTestnew() {
        UserPage users = given()
                .spec(spec2)
                .when()
                .get("users?page=2")
                .then()
                .statusCode(200)
                .extract().as(UserPage.class);
        // assertEquals(1, users[0].getId());
        // assertEquals("Bret", users[0].getUsername());
        assertEquals(7, users.getData()[0].getId());
        assertEquals("Michael", users.getData()[0].getFirst_name());

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

}
