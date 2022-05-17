package com.example.cm.View.Activities.Login;

import android.util.Log;
import android.util.Patterns;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginViewModelTest {

    @Test
    public void isEmailValid_EmailNull() {
        LoginViewModel loginViewModel = new LoginViewModel();
        String Email = null;
        assertFalse(loginViewModel.isEmailValid(Email));
    }

    @Test
      public void isEmailValid_EmailEmpty(){
        LoginViewModel loginViewModel = new LoginViewModel();
        String Email = "";
        assertFalse(loginViewModel.isEmailValid(Email));
    }

    @Test
    public void isEmailValid_EmailErrata(){
        LoginViewModel loginViewModel = new LoginViewModel();
        String Email = "mRossi";
        assertFalse(loginViewModel.isEmailValid(Email));
    }


    @Test
    public void isEmailValid_EmailCorretta(){
        LoginViewModel loginViewModel = new LoginViewModel();
        String Email = "mrossi@libero.it";
        assertTrue(loginViewModel.isEmailValid(Email));
    }


}