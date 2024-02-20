package org.example.virtualThreads.demo2;


import static org.example.Util.isPrime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class VirtualThreads
{
    private static final AtomicInteger counter = new AtomicInteger(0);
    private static final Set<String> set = new HashSet<String>();
    private static final Set<String> syncSet = Collections.synchronizedSet(set);

    public static void main( String[] args )
    {

        // Generate a list of random numbers for demonstration
        var numbers = Arrays.stream(IntStream.range(0, 1_000_000)
            .toArray())
            .boxed()
            .toList();

        // Use virtual threads to check for primes in parallel
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<Boolean>> futures = new ArrayList<>();

            long start = System.currentTimeMillis();
            for (Integer number : numbers) {
                futures.add(executorService.submit(() -> isPrime(number, counter, syncSet)));
            }


            // Collect and process results

           int count = 0;

            for (int i = 0; i < numbers.size(); i++) {
                try {
                    boolean isPrime = futures.get(i).get();
                    //System.out.println(numbers.get(i) + " is prime: " + isPrime);
                    if (isPrime) {
                        count ++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            long stop = System.currentTimeMillis();
            print(stop-start);
            System.out.println("Primes found: " + counter); //18093
            System.out.println("Threads: " + syncSet.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void print(long value) {
        String timeTaken = STR."Time taken \{value} ms";
        System.out.println(timeTaken);
    }

}
