package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;


public class PetStore {
    static long petID = 0;
    static long storeId = 0;

    public static void main(String[] args) {
        createPet();
        getPet();
        deletePet();
        confirmDetele();
        storeHandle();
        getStore();
        deleteStore();
        confirmDeleted();
        createUser();
        userLogin();
        deleteUser();
    }



    @Test
    public static void createPet(){
        baseURI = "https://petstore.swagger.io/v2";
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"sold\"\n" +
                        "}")
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .extract().response();
        petID = response.path("id");
        System.out.println("Pet ID: " + petID);
    }
    @Test
    public static void  getPet() {
        baseURI = "https://petstore.swagger.io/v2";
        given()
                .get("/pet/" + petID)
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test
    public static void deletePet(){
        baseURI = "https://petstore.swagger.io/v2";
        given()
                .delete("/pet/" + petID)
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test
    public static void confirmDetele(){
        baseURI = "https://petstore.swagger.io/v2";
        given()
                .get("/pet/9222968140497180637")
                .then()
                .statusCode(404)
                .log().all();
    }


    @Test
    public static void storeHandle(){
        baseURI = "https://petstore.swagger.io/v2";
        Response response = (Response) given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"petId\": 0,\n" +
                        "  \"quantity\": 0,\n" +
                        "  \"shipDate\": \"2023-10-11T09:08:16.928Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .post("/store/order")
                .then()
                .statusCode(200)
                .extract().response();
        storeId = response.path("id");
        System.out.println("ID: " + storeId);
    }
    @Test
    public static void getStore(){
        baseURI = "https://petstore.swagger.io/v2";
        given()
                .get("/store/order/7")
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test
    public static void deleteStore(){
        baseURI = "https://petstore.swagger.io/v2";
        given()
                .delete("/store/order/" + storeId)
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test
    public static void confirmDeleted(){
        baseURI = "https://petstore.swagger.io/v2";
        given()
                .get("/store/order/5")
                .then()
                .statusCode(404)
                .log().all();
    }


    @Test
    public static void createUser(){
        baseURI = "https://petstore.swagger.io/v2";
            given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"username\": \"test\",\n" +
                        "  \"firstName\": \"test\",\n" +
                        "  \"lastName\": \"test\",\n" +
                        "  \"email\": \"test@gmail.com\",\n" +
                        "  \"password\": \"qwerty\",\n" +
                        "  \"phone\": \"+8545215\",\n" +
                        "  \"userStatus\": 1\n" +
                        "}")
                .post("/user")
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test
    public static void userLogin(){
        baseURI = "https://petstore.swagger.io/v2";
            given()
                .get("/user/login?username=test&password=qwerty")
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test
    public static void deleteUser(){
        baseURI = "https://petstore.swagger.io/v2";
        given()
                .delete("/store/order/1")
                .then()
                .statusCode(404)
                .log().all();
    }



}
