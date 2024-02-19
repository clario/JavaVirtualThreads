package org.example.defaultMethod;

public class Main {

  public static void main(String[] args) {
    Cat cat = new HouseCat();
    System.out.println("HouseCat:");
    cat.meow();
    System.out.println();

    Cat wildCat = new WildCat();
    System.out.println("WildCat:");
    wildCat.meow();


  }

}




