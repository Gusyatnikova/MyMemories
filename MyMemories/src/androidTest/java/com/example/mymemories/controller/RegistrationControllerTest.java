package com.example.mymemories.controller;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.*;

public class RegistrationControllerTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void registrateWithEmptyLogin() throws Exception{
        this.expected.expect(CustomException.class);
        this.expected.expectMessage("Field mustn't be empty");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String login ="";
        String pswd = "1234345";
        String email = "sdfsd@gmail.com";
        RegistrationController controller = new RegistrationController(appContext);
        controller.registrate(login,pswd,email);
    }

    @Test
    public void registrateWithWrongEmail() throws Exception{
        this.expected.expect(CustomException.class);
        this.expected.expectMessage("Wrong e-mail address");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String login ="dfg";
        String pswd = "1234345";
        String email = "sdfsd.com";
        RegistrationController controller = new RegistrationController(appContext);
        controller.registrate(login,pswd,email);
    }

    @Test
    public void registrateWithWeakPassword() throws Exception{
        this.expected.expect(CustomException.class);
        this.expected.expectMessage("Password must be longer than 6 characters");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String login ="Someone";
        String pswd = "123";
        String email = "sdfsd@gmail.com";
        RegistrationController controller = new RegistrationController(appContext);
        controller.registrate(login,pswd,email);
    }

    @Test
    public void registrateWithExistingLogin() throws Exception{
        this.expected.expect(CustomException.class);
        this.expected.expectMessage("This login is already used");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String login ="Ann";
        String pswd = "1231231";
        String email = "sdfsd@gmail.com";
        RegistrationController controller = new RegistrationController(appContext);
        controller.registrate(login,pswd,email);
        controller.registrate(login,pswd,email);
    }
}