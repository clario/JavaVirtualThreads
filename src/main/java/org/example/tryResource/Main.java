package org.example.tryResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

  // Before Java 7
  public static void main(String[] args) throws FileNotFoundException {
    beforeJava7();
    //java7();
    //java9();
  }

  static void beforeJava7() {
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

  static void java7 () {
    try (Scanner scanner = new Scanner(new File("src/test.txt"))){
      while (scanner.hasNext()) {
        System.out.println(scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

  }

  static void java9 () throws FileNotFoundException {
    final Scanner scanner = new Scanner(new File("src/test.txt"));
    try (scanner){
      while (scanner.hasNext()) {
        System.out.println(scanner.nextLine());
      }
    }
  }

}

