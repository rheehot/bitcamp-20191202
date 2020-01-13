package com.eomcs.util;

public class Queue extends LinkedList<Object> {
  
  public void offer(Object value) {
    this.add(value);
  }
  
  public Object poll() {
    return this.remove(0);
  }
}
