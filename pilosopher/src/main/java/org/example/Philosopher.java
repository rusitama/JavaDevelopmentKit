package org.example;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher implements Runnable {
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;

    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating");
        Thread.sleep((long) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                think();

                leftFork.lock();
                System.out.println("Philosopher " + id + " picked up left fork");
                rightFork.lock();
                System.out.println("Philosopher " + id + " picked up right fork");

                eat();

                rightFork.unlock();
                System.out.println("Philosopher " + id + " put down right fork");
                leftFork.unlock();
                System.out.println("Philosopher " + id + " put down left fork");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


