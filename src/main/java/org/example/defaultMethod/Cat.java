package org.example.defaultMethod;

public interface Cat {

  default void meow() {
    System.out.println("meow");
  }
}
