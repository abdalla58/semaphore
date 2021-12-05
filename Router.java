package com.company;

import java.util.concurrent.Semaphore;

public  class Router {
    //Semaphore sem;
    Network network=new Network();
    //Device device;
    public void release(Semaphore semaphore){
        semaphore.release();
    }
    public void occupy(int n){
        System.out.println("connection "+n+":"+network.getThreadName()+ "Occupied");
    }
}
