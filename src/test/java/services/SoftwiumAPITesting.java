package services;

import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class SoftwiumAPITesting {

    private static final String BASE_URL = "https://softwium.com/api";
    private static final Logger log = LoggerFactory.getLogger(SoftwiumAPITesting.class);

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URL;

    }

    @Test
    void testGetAllCurrencies() {

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/currencies");


        assertEquals(200, response.getStatusCode(), "Invalid response status code");

        response
                .then()
                .assertThat()
                .body("size()", equalTo(170));

        response.prettyPrint();
        log.info(String.valueOf(response));

    }

    @Test
    void testGetCurrencyById() {

       RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/currencies/{id}", 1)
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    void testCreateNewBook() {

        RestAssured
                .given()
                .queryParam("format", "json")
                .when()
                .post("books")
                .then()
                .statusCode(201);

    }

    @Test
    void testDeleteBook() {

        RestAssured
                .given()
                .queryParam("format", "json")
                .when()
                .delete("books/23")
                .then()
                .statusCode(200);
    }
}