package com.nurfadilla.tests;

import com.nurfadilla.requests.AuthRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nurfadilla.utils.TokenManager;
import io.qameta.allure.*;

public class AuthTest {

    AuthRequest authRequest = new AuthRequest();

    @Test
    @Description("Verify user can login")
    @Severity(SeverityLevel.CRITICAL)
    public void registerUserTest(){

        Response response = authRequest.register();

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),201);

        Assert.assertEquals(
                response.jsonPath().getBoolean("success"),
                true
        );

        Assert.assertEquals(
                response.jsonPath().getString("message"),
                "Registrasi berhasil"
        );

    }

    @Test
    @Description("Verify user can register")
    @Severity(SeverityLevel.CRITICAL)
    public void loginUserTest(){

        Response response = authRequest.login();

        response.prettyPrint();

        String token =
                response.jsonPath().getString("data.token");

        TokenManager.setToken(token);

        Assert.assertEquals(response.getStatusCode(),200);

        Assert.assertEquals(
                response.jsonPath().getBoolean("success"),
                true
        );
        System.out.println(TokenManager.getToken());

    }
}

