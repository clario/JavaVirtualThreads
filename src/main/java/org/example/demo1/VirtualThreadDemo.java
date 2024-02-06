package org.example.demo1;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

public class VirtualThreadDemo {

  public static Thread createThread() {
    Runnable r = () -> System.out.println("Hey, I'm a thread! " + Thread.currentThread());

    return Thread.startVirtualThread(r);
  }

  public static void main(String[] args) throws InterruptedException {
    List<Thread> list = new ArrayList<>();
    for (int i = 0; i < 1_000_000; i++) {
      list.add(createThread());
    }


    for (Thread thread : list) {
      thread.join();
    }
  }
}
