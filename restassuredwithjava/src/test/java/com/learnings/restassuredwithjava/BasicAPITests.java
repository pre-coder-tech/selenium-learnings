package com.learnings.restassuredwithjava;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class BasicAPITests extends BaseTest {

    @Test
    public void TestStatusCodeOfPosts() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/posts")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void TestContentTypeOfPosts() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/posts")
                .then()
                .assertThat()
                .contentType(ContentType.JSON);
    }

    @Test
    public void TestResponseTimeOfPosts() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        given()
                .when()
                .get("/posts")
                .then()
                .assertThat()
                .time(lessThan(2000L)); // Time in milliseconds
    }

    @Test
    public void TestSizeOfResourcesFetched() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        given()
                .when()
                .get("/posts")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", equalTo(100));
    }

    @Test
    public void TestSinglePostAPISchema() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("posts_schema.json"));
    }

    @Test(dataProvider = "getSingleResourceTestData")
    public void TestSingleResourceFetch(int resourceId, int responseCode, int expectedUserId) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/posts/" + Integer.toString(resourceId))
                .then()
                .assertThat()
                .statusCode(responseCode)
                .body("userId", equalTo(expectedUserId));

    }

    @Test
    public void TestPushingPosts() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .header(new Header("Content-Type", "application/json"))
                .body("{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }")
                .when()
                .post("/posts")
                .then()
                .assertThat()
                .statusCode(201)
                .body("userId", equalTo(1));
    }

    @Test
    public void TestUserOfPost() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        int userId = given()
                .pathParam("id", 1)
                .when()
                .get("/posts/{id}")
                .then()
                .extract()
                .path("userId");

        given()
                //.queryParam("userId", userId)
                .pathParam("userId", userId)
                .when()
                .get("/users/{userId}")
                .then()
                .assertThat()
                .body("name", equalTo("Leanne Graham"));

    }
}


/**
 * Paginated Data fetching
int page = 1;

while (true) {
    Response response = given()
        .queryParam("page", page)
        .when()
        .get("/posts")
        .then()
        .extract()
        .response();

    if (response.jsonPath().getList("$").isEmpty()) {
        break; // Exit if no more data
    }

    System.out.println("Page " + page + ": " + response.asPrettyString());
    page++;
}

 */