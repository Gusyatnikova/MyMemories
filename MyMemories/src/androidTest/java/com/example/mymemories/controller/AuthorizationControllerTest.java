package com.example.mymemories.controller;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

public class AuthorizationControllerTest {
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void SuccessEnter() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String login = "Ann";
        String pswd = "1234567";
        AuthorizationController controller = new AuthorizationController(appContext);
        try{
            controller.enter(login, pswd);
        }catch (CustomException e){
            System.out.println(e.getMessage());
        }
        assertEquals("asd@gmail.com", controller.getEmail());
    }

    @Test
    public void enterWithEmptyLogin() throws Exception{
        this.expected.expect(CustomException.class);
        this.expected.expectMessage("Fields mustn't be empty");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String login ="";
        String pswd = "1";
        AuthorizationController controller = new AuthorizationController(appContext);
        controller.enter(login, pswd);
    }

    @Test
    public void enterWithWrongPassword() throws Exception{
        this.expected.expect(CustomException.class);
        this.expected.expectMessage("Wrong login or password");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String login ="dfgbnm";
        String pswd = "1";
        AuthorizationController controller = new AuthorizationController(appContext);
        controller.enter(login, pswd);
    }
}