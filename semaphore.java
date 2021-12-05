package com.company;

import java.util.Objects;
import java.util.concurrent.Semaphore;

public  class semaphore extends Thread{
    Device device=new Device();
    Semaphore sem;
    Network network=new Network();
    Router router=new Router();
    String threadName;
    public semaphore(Semaphore sem, String threadName)
    {
        super(threadName);
        this.sem = sem;
        this.threadName=threadName;
    }
    @Override
    public void run() {

        // run by thread A
        if(this.getName().equals("C1"))
        {
            System.out.println("("+threadName+") "+"("+network.getDeviceName()+") arrived");
            //System.out.println("("+network2.threadName+") "+"("+network2.deviceName+") arrived");
            try
            {
                Main.Shared.count++;
                router.occupy(Main.Shared.count);
                // acquiring the lock
                sem.acquire();
                Main.Shared.count++;
                Thread.sleep(10);
                System.out.println(device.login(threadName));
                Main.Shared.count++;
                Thread.sleep(10);
                System.out.println(device.perform(threadName));

            } catch (InterruptedException exc) {
                System.out.println(exc);
            }

            // Release the permit.
            System.out.println(device.logout(threadName));
            router.release(sem);
        }

        // run by thread B
        else
        {
            System.out.println("("+threadName+") "+"("+network.getDeviceName()+") arrived");
            //System.out.println("("+network2.threadName+") "+"("+network2.deviceName+") arrived");
            try
            {
                Main.Shared.count--;
                router.occupy(2);
                // acquiring the lock
                sem.acquire();
                Main.Shared.count--;
                Thread.sleep(10);
                System.out.println(device.login(threadName));
                Main.Shared.count--;
                Thread.sleep(10);
                System.out.println(device.perform(threadName));

            } catch (InterruptedException exc) {
                System.out.println(exc);
            }

            // Release the permit.
            System.out.println(device.logout(threadName));
            router.release(sem);
        }
    }
}

