package org.example.virtualThreads.demo1;

public class ThreadDemo {

  public static void createThread() {
    Runnable r = () -> System.out.println("Hey, I'm a thread! " + Thread.currentThread());

    Thread thread =  new Thread(r);
    thread.start();
  }


  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    for (int i = 0; i < 1_000_000; i++) {
      createThread();
    }
    long stop = System.currentTimeMillis();
    System.out.printf("Time: %d s", (stop - start) / 1000);
  }


}
