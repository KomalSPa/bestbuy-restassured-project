package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    @Test
    // Verify the if the total is equal to 1561
    public void test001() {
        response.body("total", equalTo(1561));
    }

    @Test
    // Verify the if the stores of limit is equal to 10
    public void test002() {
        response.body("limit", equalTo(10));
    }

    @Test
    // Check the single ‘Name’ in the Array list (Inver Grove Heights)
    public void test003() {
        response.body("data.name", hasItem("Inver Grove Heights"));
    }

    @Test
    // Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    public void test004() {
        response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));
    }

    // Verify the storeid=7 inside storeservices of the third store of second services
    @Test
    public void test005() {
        response.body("data[2].services[2].storeservices.storeId", equalTo(7));
    }

    // Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    @Test
    public void test06() {
        response.body("data[2].services[0].storeservices", hasKey("createdAt"));
    }
    public void test006a(){
        response.body("data[2].createdAt", equalTo("2016-11-17T17:57:05.853Z"));
    }

    // Verify the state = MN of forth store
    @Test
    public void test007() {
        response.body("data[3].state", equalTo("MN"));
    }

    @Test
    // Verify the store name = Rochester of 9th store
    public void test008() {
        response.body("data[8].name", equalTo("Rochester"));
    }

    @Test
    // Verify the storeId = 11 for the 6th store
    public void test009() {
        response.body("data[5].id", equalTo(11));
    }
    @Test
    // Verify the serviceId = 4 for the 7th store of forth service
    public void test010(){
        response.body("data[6].services[3].storeservices.serviceId",equalTo(4));
    }
}
