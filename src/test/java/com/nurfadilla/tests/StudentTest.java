package com.nurfadilla.tests;

import com.nurfadilla.requests.AuthRequest;
import com.nurfadilla.requests.StudentRequest;
import com.nurfadilla.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.nurfadilla.utils.JsonReader;
import io.qameta.allure.*;
@Epic("Student")
@Feature("Student API")
public class StudentTest {

    AuthRequest authRequest = new AuthRequest();
    StudentRequest studentRequest = new StudentRequest();

    @BeforeClass
    public void setup(){

        Response loginResponse = authRequest.login();

        String token =
                loginResponse.jsonPath().getString("data.token");

        TokenManager.setToken(token);

    }

    @Test
    public void getAllStudentTest(){

        Response response =
                studentRequest.getAllStudent();

        response.prettyPrint();

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );

        Assert.assertEquals(
                response.jsonPath().getBoolean("success"),
                true
        );

        Assert.assertNotNull(
                response.jsonPath().getList("data.data")
        );

        Assert.assertTrue(
                response.jsonPath().getList("data.data").size() > 0
        );

    }

    @Test
    public void getStudentByIdTest(){

        String studentId = "58d47639-4234-4a1a-a881-bf7386840784";

        Response response =
                studentRequest.getStudentById(studentId);

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);

        Assert.assertEquals(
                response.jsonPath().getBoolean("success"),
                true
        );

    }

    @Test
    @Description("Verify create student")
    @Severity(SeverityLevel.CRITICAL)
    public void createStudentTest() throws Exception {

        Response response = studentRequest.createStudent();
        System.out.println("Status Code: " + response.getStatusCode());
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 201);

        Assert.assertTrue(response.jsonPath().getBoolean("success"));
    }

    @Test
    public void createInvalidStudentTest() throws Exception {

        Response response = studentRequest.createInvalidStudent();

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }
}