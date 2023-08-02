package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    @Test
    // Extract the limit
    public void test021() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // 2 Extract the total
    public void test022() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Extract the name of 5th product
    public void test023() {
        String product5th = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + product5th);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Extract the names of all the products
    public void test024() {
        List<String> allProducts = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of All Store Names is : " + allProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Extract the productId of all the products
    public void test025() {
        List<Integer> allProducts = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of StorId is : " + allProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Print the size of the data list
    public void test026() {
        int totalData = response.extract().path("data.size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Total Data is : " + totalData);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    public void test027() {
        List<HashMap<String, ?>> maxEnergizer = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Energizer - MAX Batteries AA (4-Pack) : " + maxEnergizer);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    public void test028() {
        List<String> nCellEnergizer = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("'Energizer - N Cell E90 Batteries (2-Pack) : " + nCellEnergizer);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Get all the categories of 8th products
    public void test029() {
        List<String> product8th = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Services of 8th Store : " + product8th);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Get categories of the store where product id = 150115
    public void test030() {
        List<HashMap<String, ?>> pId150115 = response.extract().path("data[3].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the store where product id = 150115 : " + pId150115);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Get all the descriptions of all the products
    public void test031() {
        List<String> allDescription = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of ProductID is : " + allDescription);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Get id of all the all categories of all the products
    public void test032() {
        List<Integer> allPrdCTG = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of StorId is : " + allPrdCTG);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the product names Where type = HardGood
    public void test033() {
        List<?> nameHardGood = response.extract().path("data.findAll{it.type =='HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the product names Where type = HardGood : " + nameHardGood);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
//
    public void test034() {
        List<String> totalRochester = response.extract().path("data.findAll{it.name=='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Duracell - AA 1.5V CopperTop Batteries (4-Pack) : " + totalRochester);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the createdAt for all products whose price < 5.49
    public void test035() {
        List<?> createdAtList = response.extract().path("data.findAll{it.categories.findAll{it.price<'5.49'}}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the createdAt for all products whose price < 5.49 : " + createdAtList);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)
    public void test036() {
        List<String> prdtEnergizer = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Services the store where service name = Fargo : " + prdtEnergizer);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the manufacturer of all the products
    public void test037() {
        List<String> manuFacturer = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the manufacturer of all the products : " + manuFacturer);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the imge of products whose manufacturer is = Energizer
    public void test038() {
        List<String> imgEnergizer = response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the imge of products whose manufacturer is = Energizer : " + imgEnergizer);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the createdAt for all categories products whose price > 5.99
    public void test039() {
        List<HashMap<String, ?>> price599 = response.extract().path("data.findAll{it.price>5.99}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the createdAt for all categories products whose price > '5.99'" + price599);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the uri of all the products
    public void test040() {
        List<String> uriProducts = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the url of all the products : " + uriProducts);
        System.out.println("------------------End of Test---------------------------");
    }

}
