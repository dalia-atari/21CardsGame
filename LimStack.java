package com.company;

public interface LimStack<T> extends Stack<T>{
    void push(T element);
    boolean isFull();
}
