package com.bestbuy.testsuite;

import groovy.transform.ToString;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StoresExtractionTest {
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
    // 1 Extract the limit
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // 2 Extract the total
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Extract the name of 5th store
    public void test003() {
        String storeName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Extract the names of all the store
    public void test004() {
        List<String> allStoreNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of All Store Names is : " + allStoreNames);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Extract the storeId of all the store
    public void test005() {
        List<Integer> allStoreID = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of StorId is : " + allStoreID);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Print the size of the data list
    public void test006() {
        int totalData = response.extract().path("data.size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Total Data is : " + totalData);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Get all the value of the store where store name = St Cloud
    public void test007() {
        List<HashMap<String, ?>> stCloud = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of St Cloud : " + stCloud);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //  Get the address of the store where store name = Rochester
    public void test008() {
        List<String> addRochester = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        // String addRochester = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Address of Rocheser : " + addRochester);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Get all the services of 8th store
    public void test009() {
        List<String> store8th = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Services of 8th Store : " + store8th);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Get storeservices of the store where service name = Windows Store
    public void test010() {
        List<HashMap<String, ?>> windowsStore = response.extract().path("data.findAll{it.services.findAll{it.name=='Windows Store'}}.services.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Services the store where service name = Windows Store : " + windowsStore);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //Get all the storeId of all the store
    public void test011() {
        List<Integer> allStoreID = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of StorId is : " + allStoreID);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Get id of all the store
    public void test012() {
        List<Integer> allStoreID = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of StorId is : " + allStoreID);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the store names Where state = ND
    public void test013() {
        //  List<String> ndStore = response.extract().path("data.findAll{it.state == 'ND'}.name");
        List<?> ndStore = response.extract().path("data.findAll{it.state.findAll('ND')}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Where state = ND : " + ndStore);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the Total number of services for the store where store name = Rochester
    public void test014() {
        List<Integer> totalRochester = response.extract().path("data.findAll{it.name=='Rochester'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the store where store name = Rochester : " + totalRochester.size());
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the createdAt for all services whose name = “Windows Store”
    public void test015() {
        List<?> createdAtWS = response.extract().path("data.findAll{it.services.findAll{it.name=='Windows Store'}}.services.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Services the store where service name = Windows Store : " + createdAtWS);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the name of all services Where store name = “Fargo”
    public void test016() {
        List<String> totalFargo = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Services the store where service name = Fargo : " + totalFargo);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the zip of all the store
    public void test017() {
        List<String> allZip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store : " + allZip);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the zip of store name = Roseville
    public void test018() {
        List<String> zipRoseville = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store : " + zipRoseville);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the storeservices details of the service name = Magnolia Home Theater
    public void test019() {
        List<HashMap<String, ?>> magnoliaHomename = response.extract().path("data.findAll { it.services.find { it.name == 'Magnolia Home Theater'} != null }.services.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("storeservices details of the service name = Magnolia Home Theater" + magnoliaHomename);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test020() {
        // Find the lat of all the stores
        List<Integer> latAllStore = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the store where store name = Rochester : " + latAllStore);
        System.out.println("------------------End of Test---------------------------");

    }
}
