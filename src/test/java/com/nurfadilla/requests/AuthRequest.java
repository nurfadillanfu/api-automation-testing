package com.nurfadilla.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

import com.nurfadilla.utils.Config;

import com.nurfadilla.utils.JsonReader;

public class AuthRequest {

    public Response register(){
        RestAssured.baseURI = Config.BASE_URL;

        File requestBody = JsonReader.getJsonFile("register.json");
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(requestBody)

                        .when()
                        .post("/auth/register");

        return response;
    }

    public Response login(){

        RestAssured.baseURI = Config.BASE_URL;

        File requestBody = JsonReader.getJsonFile("login.json");

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(requestBody)

                        .when()
                        .post("/auth/login");

        return response;

    }
}