package com.example.cm.data;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.regions.Regions;

public class CognitoSetting {

    private String userPoolId="*********************";
    private String clientId="***********************";
    private String clientSecret="*********************";
    private Regions cognitoRegions=Regions.US_EAST_2;


    Context context;

    public CognitoSetting(Context context){
        this.context=context;
    }

    public String getUserPoolId() {
        return userPoolId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public Regions getCognitoRegions() {
        return cognitoRegions;
    }

    public CognitoUserPool getUserPool(){
        return new CognitoUserPool(context,userPoolId,clientId,clientSecret,cognitoRegions);
    }




}
