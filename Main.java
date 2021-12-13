package com.company;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

    static class Shared
    {
        static int count = 0;
    }
//
public static class Device {

    //Network network=new Network();
    Router router=new Router();
    public String login(String connection,int n) throws IOException {
        FileWriter writer=new FileWriter("output.txt",true);
        connection="connection "+n+" :"+connection+" login";
        writer.write(connection+"\n");
        writer.close();
        return connection;
    }
    public String logout(String connection,int n) throws IOException{
        FileWriter writer=new FileWriter("output.txt",true);
        connection="connection "+n+" :"+connection+" logout";
        writer.write(connection+"\n");
        writer.close();
        return connection;
    }
    public String perform(String connection,int n) throws IOException{
        FileWriter writer=new FileWriter("output.txt",true);
        connection="connection "+n+" :"+connection+" performs online activity";
        writer.write(connection+"\n");
        writer.close();
        return connection;
    }
}
    public static class Network {
    private int maxNumberOfConnection;
    private int totalNumberOfConnection;
    private String threadName;
    private String deviceName;

    public Network(String threadName,String deviceName) {
        this.threadName=threadName;
        this.deviceName=deviceName;
    }
    public Network(){}
    public String getThreadName() {
        return threadName;
    }

//    public void setDeviceName(String deviceName) {
//        this.deviceName = deviceName;
//    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setMaxNumberOfConnection(int maxNumberOfConnection) {
        this.maxNumberOfConnection = maxNumberOfConnection;
    }

    public int getMaxNumberOfConnection() {
        return maxNumberOfConnection;
    }

    public void setTotalNumberOfConnection(int totalNumberOfConnection) {
        this.totalNumberOfConnection = totalNumberOfConnection;
    }

    public int getTotalNumberOfConnection() {
        return totalNumberOfConnection;
    }
}
   public static class Router {
    Network network=new Network();
    //Device device;
//    private int a;
//
//    public void setA(int a) {
//        this.a = a;
//    }
//
//    public int getA() {
//        return a;
//    }
    public void release(Semaphore semaphore){
        semaphore.release();
    }
    public void occupy(String threadName,int a) throws IOException {
        FileWriter writer=new FileWriter("output.txt",true);
        System.out.println("connection "+a+" :"+threadName+ " Occupied");
        writer.write("connection "+a+" :"+threadName+ " Occupied"+"\n");
        writer.close();
    }
}
    public static class semaphore extends Thread{
    Device device=new Device();
    Semaphore sem;
    Network network=new Network();
    Router router=new Router();
    String threadName;
    String deviceName;
    public int c=0;

    public semaphore(Semaphore sem, String threadName,String deviceName)
    {
        super(threadName);
        this.sem = sem;
        this.threadName=threadName;
        this.deviceName=deviceName;
    }


    @Override
    public void run() {

            try
            {
                //c=(c+1)%2;
                switch(threadName) {
                    case "C1":
                        c=1;
                        break;
                    case "C2":
                        c=2;
                        break;
                    case "C3":
                        c=1;
                        break;
                    case "C4":
                        c=2;
                        break;
                    case "C5":
                        c=1;
                        break;
                    case "C6":
                        c=1;
                        break;
                }

                // acquiring the lock
                sem.acquire();
                router.occupy(threadName,c);
                Main.Shared.count++;
                System.out.println(device.login(threadName,c));
                Main.Shared.count++;
                System.out.println(device.perform(threadName,c));

            } catch (InterruptedException | IOException exc) {
                System.out.println(exc);
            }

            // Release the permit.
            Main.Shared.count++;
        try {
            System.out.println(device.logout(threadName,c));
        } catch (IOException e) {
            e.printStackTrace();
        }
        router.release(sem);
    }
}



    public static void main(String[] args) throws IOException, InterruptedException {
        //FileWriter writer=new FileWriter("output.txt",true);
        Network network=new Network();
        //Router router=new Router();
        Scanner input=new Scanner(System.in);
        System.out.println("What is the number of WI-FI Connections?");
        int a=input.nextInt();
        Semaphore sem=new Semaphore(a);
        System.out.println("What is the number of devices Clients want to connect?");
        int b=input.nextInt();
        network.setMaxNumberOfConnection(a);
        network.setTotalNumberOfConnection(b);
        Network[] network1= new Network[b];
        semaphore[] t=new semaphore[b];
        for (int i=0;i<network.getTotalNumberOfConnection();i++){
            String S=input.next();
            String A=input.next();
            network1[i]=new Network(S,A);
            t[i]=new semaphore(sem,network1[i].getThreadName(),network1[i].getDeviceName());
        }
        int c=1;
        for (int i=0;i< network.getMaxNumberOfConnection();i++){
            System.out.println("("+t[i].threadName+") "+"("+t[i].deviceName+") arrived");
            //c=(c+1)%2;
            //router.setA(c+1);
            t[i].start();
        }for (int i=2;i<b;i++){
            System.out.println("("+t[i].threadName+") "+"("+t[i].deviceName+") arrived and waiting");
            //c=(c+1)%2;
            //router.setA(c+1);
            t[i].start();
        }
        for (int i=0;i<b;i++){
            t[i].join();
        }
    }
}
/*
4
C1
mobile
C2
laptop
C3
PC
C4
ipad
 */
