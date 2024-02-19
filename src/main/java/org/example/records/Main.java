package org.example.records;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    java14();
    //java17();
  }

  private static void java14() {
    Person person = new Person("John", 30, "blue");
    System.out.println(person.name());
    System.out.println(person.age());
    System.out.println(person.favoriteColor());

    Person person2 = new Person("John", 30, "red");

    // - equals and hashCode methods
    System.out.println(STR."Check if person is equal: \{person.equals(person2)}");

    //System.out.println(person.hashCode());

    // - a toString method
    System.out.println(person);
  }


  private static void java17() {
    Shape circle = new Circle(2.5);
    System.out.printf("Area of circle is: %s\n",circle.area());

    Shape rectangle = new Rectangle(5, 10);
    System.out.printf("Area of rectangle is %s \n", rectangle.area());

    Square square = new Square(5);
    System.out.printf("Area of square is %s \n", square.area());

  }


  record Person(String name, int age, String favoriteColor) {

  }

  sealed interface Shape permits Circle, Rectangle {
    double area();
  }

  record Circle(double radius) implements Shape {
    public double area() {
      return Math.PI * radius * radius;
    }
  }

  record Rectangle(double width, double height) implements Shape {
    public double area() {
      return width * height;
    }
  }

  record Square(double side)  {
    public double area() {
      return side * side;
    }
  }


}
