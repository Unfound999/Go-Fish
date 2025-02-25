/*
 * Author: Christopher Waschke
 * Description: A simple implementation of the Stack data structure.
 * Citation: https://stackoverflow.com/questions/529085/how-can-i-create-a-generic-array-in-java
 */
package com.cs145group;

import java.lang.reflect.Array;
import java.util.Random;

public class Stack<T> {
    private T[] stackData;
    private int top;

    @SuppressWarnings("unchecked")
    public Stack(Class<T> type, int size){
        this.top = 0;
        this.stackData = (T[])Array.newInstance(type, size);
    }

    public T pop() throws StackOverflowError{
        if(this.top < 0){
            throw new StackOverflowError("StackUnderflow.");
        }
        T outGoing = this.stackData[top];
        this.top--;
        return outGoing;
    }

    public T peek(){
        return this.stackData[top];
    }

    public void push(T item) throws StackOverflowError{
        if(this.top + 1 > this.stackData.length){
            throw new StackOverflowError();
        }
        this.stackData[++this.top] = item;
    }

    public int getSize(){
        return this.stackData.length;
    }

    public int getCurrentLocation(){
        return this.top;
    }

    public void shuffle(){
        Random random = new Random();
        for(int i = this.stackData.length-1; i>0; i--){
            int rnd_index = random.nextInt(i+1);
            T item1 = this.stackData[i];
            this.stackData[i] = this.stackData[rnd_index];
            this.stackData[rnd_index] = item1;
        }
    }
}
