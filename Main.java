package com.company;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

//    public class CountingSemaphore
//    {
//        private int signals = 0;
//
//        public synchronized void take()
//        {
//            this.signals++;
//            this.notify();
//        }
//
//        public synchronized void release() throws InterruptedException
//        {
//            while(this.signals == 0)
//                wait();
//            this.signals--;
//        }
//    }
    static class Shared
    {
        static int count = 0;
    }
//    public static class Device  {
//        Network network;
//        public String login(String connection){
//            connection=network.getThreadName()+" login";
//            return connection;
//        }
//        public String logout(String connection){
//            connection=network.getThreadName()+" logout";
//            return connection;
//        }
//        public String perform(String connection){
//            connection=network.getThreadName()+" performs online activity";
//            return connection;
//        }
//    }
//    public static class Network {
//
//        private int maxNumberOfConnection;
//        private int totalNumberOfConnection;
//        private String threadName;
//        private String deviceName;
//
//        public Network() {}
//        public void setThreadName(String threadName) {
//            this.threadName = threadName;
//        }
//        public String getThreadName() {
//            return threadName;
//        }
//
//        public void setDeviceName(String deviceName) {
//            this.deviceName = deviceName;
//        }
//
//        public String getDeviceName() {
//            return deviceName;
//        }
//
//        public void setMaxNumberOfConnection(int maxNumberOfConnection) {
//            this.maxNumberOfConnection = maxNumberOfConnection;
//        }
//
//        public int getMaxNumberOfConnection() {
//            return maxNumberOfConnection;
//        }
//
//        public void setTotalNumberOfConnection(int totalNumberOfConnection) {
//            this.totalNumberOfConnection = totalNumberOfConnection;
//        }
//
//        public int getTotalNumberOfConnection() {
//            return totalNumberOfConnection;
//        }
//    }
//    public  class Router {
//        Semaphore sem;
//        Network network;
//        Device device;
//        public void release(Semaphore semaphore){
//            semaphore.release();
//        }
//        public void occupy(int n){
//            System.out.println("connection "+n+":"+network.getThreadName()+ "Occupied");
//        }
//    }
//    public static class semaphore extends Thread{
//        Device device;
//        Semaphore sem;
//        Network network;
//        Router router;
//        public semaphore(Semaphore sem, String threadName)
//        {
//            super(threadName);
//            this.sem = sem;
//            //threadName= network.threadName;
//        }
//        @Override
//        public void run() {
//
//            // run by thread A
//            if(Objects.equals(network.getThreadName(), "C1"))
//            {
//                System.out.println("("+network.getThreadName()+") "+"("+network.getDeviceName()+") arrived");
//                //System.out.println("("+network2.threadName+") "+"("+network2.deviceName+") arrived");
//                try
//                {
//                    Shared.count++;
//                    router.occupy(Shared.count);
//                    // acquiring the lock
//                    sem.acquire();
//                    Shared.count++;
//                    Thread.sleep(10);
//                    System.out.println(device.login(network.getThreadName()));
//                    Shared.count++;
//                    Thread.sleep(10);
//                    System.out.println(device.perform(network.getThreadName()));
//
//                } catch (InterruptedException exc) {
//                    System.out.println(exc);
//                }
//
//                // Release the permit.
//                System.out.println(device.logout(network.getThreadName()));
//                router.release(sem);
//            }
//
//            // run by thread B
//            else
//            {
//                System.out.println("("+network.getThreadName()+") "+"("+network.getDeviceName()+") arrived");
//                //System.out.println("("+network2.threadName+") "+"("+network2.deviceName+") arrived");
//                try
//                {
//                    Shared.count--;
//                    router.occupy(2);
//                    // acquiring the lock
//                    sem.acquire();
//                    Shared.count--;
//                    Thread.sleep(10);
//                    System.out.println(device.login(network.getThreadName()));
//                    Shared.count--;
//                    Thread.sleep(10);
//                    System.out.println(device.perform(network.getThreadName()));
//
//                } catch (InterruptedException exc) {
//                    System.out.println(exc);
//                }
//
//                // Release the permit.
//                System.out.println(device.logout(network.getThreadName()));
//                router.release(sem);
//            }
//        }
//    }



    public static void main(String[] args) throws InterruptedException {
        Semaphore sem=new Semaphore(2);
        Network network2=new Network("C2","laptop");
        Scanner input=new Scanner(System.in);
        System.out.println("What is the number of WI-FI Connections?");
        int a=input.nextInt();
        System.out.println("What is the number of devices Clients want to connect?");
        int b=input.nextInt();
        String S=input.next();
        String A=input.next();
        Network network=new Network(S,A);
        network.setMaxNumberOfConnection(a);
        network.setTotalNumberOfConnection(b);
        semaphore t1=new semaphore(sem,network.getThreadName());
        semaphore t2=new semaphore(sem,"C2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
//        for (int i=0;i< network.totalNumberOfConnection;i++){
//            String a=input.nextLine();
//            String b=input.nextLine();
//            Network network=new Network(a,b);
//        }
    }
}
