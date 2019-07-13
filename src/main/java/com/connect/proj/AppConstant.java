package com.connect.proj;

public enum AppConstant {

    ADDED("ADDED"),
    REMOVED("REMOVED");

    public String action;


    AppConstant(String action){
        this.action=action;
    }

    public String getAction(){
        return this.action;
    }
}
