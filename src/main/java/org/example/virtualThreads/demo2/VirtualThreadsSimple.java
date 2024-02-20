package org.example.virtualThreads.demo2;


import static org.example.Util.isPrime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class VirtualThreadsSimple
{
    public static void main( String[] args )
    {

        // Generate a list of random numbers for demonstration
        var numbers = Arrays.stream(IntStream.range(0, 10_000)
            .toArray())
            .boxed()
            .toList();

        // Use virtual threads to check for primes in parallel
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<Boolean>> futures = new ArrayList<>();

            long start = System.currentTimeMillis();
            for (Integer number : numbers) {
                futures.add(executorService.submit(() -> isPrime(number, null, null)));
            }

            for (int i = 0; i < numbers.size(); i++) {
                try {
                   futures.get(i).get();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            long stop = System.currentTimeMillis();
            print(stop-start);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void print(long value) {
        String timeTaken = STR."Time taken \{value} ms";
        System.out.println(timeTaken);
    }



}
