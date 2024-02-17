package org.example.demo2;

import static org.example.Util.isPrime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class PreVT
{
    final static int MAX_NUMBERS_TO_CHECK = 1_000_000;

    public static void main( String[] args ) {
       //stream();
       parrallelStream();
    }

    public static void stream() {
        long start = System.currentTimeMillis();

        var primes = new ArrayList<>();
        // Generate a list of random numbers for demonstration
        IntStream.range(0, MAX_NUMBERS_TO_CHECK)
            .forEach(e-> {
              try {
                if (isPrime(e, null, null)) {
                    primes.add(e);
                }
              } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
              }
              // System.out.println(STR."\{e} \{Thread.currentThread().getName()}");

            });
        long stop = System.currentTimeMillis();

        print(stop-start);
        System.out.println("Primes found: " + primes.size());

    }

    public static void parrallelStream() {
        long start = System.currentTimeMillis();
        final List<Integer> list = new ArrayList<>();
      Set<String> threadNames = new HashSet<>();
      var syncroThreadNames = Collections.synchronizedSet(threadNames);
        var primes = Collections.synchronizedList(list);
        // Generate a list of random numbers for demonstration
        IntStream.range(0, MAX_NUMBERS_TO_CHECK)
            .parallel()
            .forEach(e-> {
              try {
                if (isPrime(e, null, null)) {
                    primes.add(e);
                }
              } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
              }
             // System.out.println(STR."\{e} \{Thread.currentThread().getName()}");
              var threadName = Thread.currentThread().getName();
              if (!syncroThreadNames.contains(threadName)){
                syncroThreadNames.add(threadName);
              }

            });

        long stop = System.currentTimeMillis();

        print(stop-start);
        System.out.println("Primes found: " + primes.size());
      System.out.println("Number of threads: " + syncroThreadNames.size());
    }

    public static void print(long value) {
        String timeTaken = STR."Time taken \{value} ms";
        System.out.println(timeTaken);
    }

}
