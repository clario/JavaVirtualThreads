package org.example.tryResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

  // Before Java 7
  public static void main(String[] args) {
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File("src/test.txt"));
      while (scanner.hasNext()) {
        System.out.println(scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
  }

  public class Java7 {
    public static void main(String[] args) {
      try (Scanner scanner = new Scanner(new File("src/test.txt"))){
        while (scanner.hasNext()) {
          System.out.println(scanner.nextLine());
        }
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    }

  }

  public class Java9 {
    public static void main(String[] args) throws FileNotFoundException {
      final Scanner scanner = new Scanner(new File("src/test.txt"));
      try (scanner){
        while (scanner.hasNext()) {
          System.out.println(scanner.nextLine());
        }
      }
    }

  }
}

