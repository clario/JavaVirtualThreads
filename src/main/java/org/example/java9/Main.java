package org.example.java9;


public class Main {

  void main() {
    try {
      throw new Exception();
    } catch (Exception _) {
      System.out.println("Exception caught");
    }
  }

}