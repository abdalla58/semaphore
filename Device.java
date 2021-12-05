package com.company;

public class Device {
    Network network=new Network();
    public String login(String connection){
        connection=connection+" login";
        return connection;
    }
    public String logout(String connection){
        connection=connection+" logout";
        return connection;
    }
    public String perform(String connection){
        connection=connection+" performs online activity";
        return connection;
    }
}
