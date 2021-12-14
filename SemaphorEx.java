package MultiThreads;

import java.util.concurrent.Semaphore;

public class SemaphorEx {
    public static void main(String[] args) {
        Semaphore callBox = new Semaphore(3);
        new Person("Tom", callBox);
        new Person("Jim", callBox);
        new Person("Alice", callBox);
        new Person("Oleg", callBox);
        new Person("Greg", callBox);
    }
}


class Person extends Thread{
    String name;
    private Semaphore callBox;

    public Person(String name, Semaphore callBox) {
        this.name = name;
        this.callBox = callBox;
        this.start();
    }

    public void run() {
        System.out.println("Ждет : " + name);
        try {
            callBox.acquire();
            System.out.println(name + ": Начал звонок");
            Thread.sleep(2000);
            System.out.println(name + ": Закончил звонок");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            callBox.release();
        }
    }
}