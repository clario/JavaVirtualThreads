package org.example;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Util {



  public static boolean isPrime(Integer value, AtomicInteger counter, Set<String> set) throws InterruptedException {

    if (set != null ) {
      set.add(Thread.currentThread().toString());
    }

    if (value <= 1) {
      return false;
    }

    if (value == 2) {
      if (counter != null) {
        counter.incrementAndGet();
      }
      return true;
    }

    if (value % 2 == 0) {
      return false;
    }

    Thread.sleep(1);

    //System.out.println(STR."\{value} \{Thread.currentThread()}");
    for (int i = 2; i < (int) Math.sqrt(value); i++ ) {
      if (value % i == 0) {
        return false;
      }
    }

    if (counter != null) {
      counter.incrementAndGet();
    }

    return true;
  }
}
