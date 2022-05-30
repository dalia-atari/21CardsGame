package com.company;

public class ArrayStack<T> implements LimStack<T> {
    protected T[] stack;
    protected int topIndex = -1;

    public ArrayStack(int maxSize)
    {

        stack = (T[]) new Object[maxSize];
    }

    public void push(T element)
    {
        if (!isFull())
        {
            topIndex++;
            stack[topIndex] = element;
        }
    }

    public void pop()
    {
        if (!isEmpty())
        {
            stack[topIndex] = null;
            topIndex--;
        }
    }

    public T top()
    {
        T topOfStack = null;
        if (!isEmpty()) {
            topOfStack = stack[topIndex];
        }
        return topOfStack;
    }

    public boolean isEmpty()
    {
        return topIndex == -1;
    }

    public boolean isFull()
    {
        return topIndex == (stack.length - 1);
    }
}
