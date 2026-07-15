package com.nurfadilla.requests;

import java.io.File;
import com.nurfadilla.utils.JsonReader;
import com.nurfadilla.utils.Config;
import com.nurfadilla.utils.TokenManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StudentRequest {

    public Response getAllStudent(){

        RestAssured.baseURI = Config.BASE_URL;

        Response response =
                given()
                        .header("Authorization",
                                "Bearer " + TokenManager.getToken())

                        .when()
                        .get("/siswa");

        return response;

    }
    public Response getStudentById(String id){

        RestAssured.baseURI = Config.BASE_URL;

        Response response =
                given()
                        .header("Authorization",
                                "Bearer " + TokenManager.getToken())

                        .when()
                        .get("/siswa/" + id);

        return response;

    }

    public Response createStudent(String body){


        Response response =

                given()

                        .header(
                                "Authorization",
                                "Bearer "
                                        + TokenManager.getToken()
                        )

                        .header(
                                "Content-Type",
                                "application/json"
                        )

                        .body(body)


                        .when()

                        .post(
                                Config.BASE_URL
                                        + "/siswa"
                        );


        return response;

    }

    public Response createStudent() throws Exception {

        RestAssured.baseURI = Config.BASE_URL;

        String requestBody = JsonReader.readJson("student.json");

        return given()
                .header("Authorization","Bearer " + TokenManager.getToken())
                .header("Content-Type","application/json")
                .body(requestBody)
                .log().body()
                .post("/siswa");
    }

    public Response createInvalidStudent() throws Exception {

        RestAssured.baseURI = Config.BASE_URL;

        String requestBody = JsonReader.readJson("studentInvalid.json");

        return given()
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/siswa");
    }
}