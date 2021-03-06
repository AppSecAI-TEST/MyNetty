package com.dk.juc.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA
 * TryLock
 *
 * @author dk
 * @date 2017/7/24 19:53
 */
public class TryLock implements Runnable{
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public TryLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if(lock == 1){
            while (true){
                if(lock1.tryLock()){
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(lock2.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getName()+"  My Job done");
                                return;
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } finally {
                        lock1.unlock();
                    }
                }else{
                    System.out.println("haha1");
                }
            }
        }else {
            while (true){
                if(lock2.tryLock()){
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(lock1.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getName()+" My Job done");
                                return;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }else{
                    System.out.println("haha2");
                }
            }

        }
    }

    public static void main(String[] args) {
        TryLock tryLock1 = new TryLock(1);
        TryLock tryLock2 = new TryLock(2);

        Thread t1 = new Thread(tryLock1);
        Thread t2 = new Thread(tryLock2);
        t1.start();
        t2.start();

    }
}
