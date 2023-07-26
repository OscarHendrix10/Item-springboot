package com.howdoinjava.demo.Handling;

public class ItemNotFoundException extends RuntimeException {
  //constructor de la clase
    public ItemNotFoundException(Long id) {
      super("Could not find item " + id);
    }
}
