package org.example.virtualThreads.demo1;

import java.lang.Thread.UncaughtExceptionHandler;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class VirtualThreadDemo {

  public static Thread createThread() {
    Runnable r = () -> System.out.println("Hey, I'm a thread! " + Thread.currentThread());

    return Thread.startVirtualThread(r);
  }

  public static void main(String[] args) throws InterruptedException {
    long start = System.currentTimeMillis();
    List<Thread> list = new ArrayList<>();
    for (int i = 0; i < 1_000_000; i++) {
      list.add(createThread());
    }

    //list.forEach(Thread::start);


    for (Thread thread : list) {
      thread.join();
    }
    var stop = System.currentTimeMillis();
    // format the duration seconds and milliseconds
    var duration = Duration.ofMillis(stop - start);
    // Time in seconds and milliseconds
    System.out.println(STR."Time: \{duration}");
  }
}
