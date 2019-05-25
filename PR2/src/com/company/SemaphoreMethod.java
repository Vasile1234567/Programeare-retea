package com.company;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreMethod {
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        //crearea si initializarea semafoarelor
        Semaphore sem1 = new Semaphore(0);
        Semaphore sem2 = new Semaphore(0);
        Semaphore sem3 = new Semaphore(0);
        Semaphore sem4 = new Semaphore(0);
        Semaphore sem5 = new Semaphore(0);
        Semaphore sem6 = new Semaphore(0);
        Semaphore sem7 = new Semaphore(0);


        new Thread(new Runnable() {
            @Override
            public void run() {
                Work("7", sem7);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //2 depends 5 && 2 depends 6
                    sem7.acquire();
                    Work("5", sem5);
                    Work("6", sem6);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //2 depends 5 && 2 depends 6
                    sem5.acquire();
                    Work("1", sem1);
                    Work("2", sem2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //2 depends 5 && 2 depends 6
                    sem6.acquire();
                    Work("3", sem3);
                    Work("4", sem4);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static void Work(String id, Semaphore semToRelease) { // gets thread id, and semaphore which will be released
        int millis = random.nextInt(10);
        try {
            Thread.sleep(millis); // sleep for time < 2000
            System.out.printf("Thread nr. %s\n", id);
            semToRelease.release(); // semaphore releases next thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
