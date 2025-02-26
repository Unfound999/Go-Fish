/*
 * Author: Christopher Waschke, Brody Weinkauf, Jackson Jenks
 * Description: A simple implementation of the Stack data structure.
 * Citation: https://stackoverflow.com/questions/529085/how-can-i-create-a-generic-array-in-java
 */
package com.cs145group;

import java.lang.reflect.Array;
import java.util.Random;

/* Stack Class
 * This class is a generic implementation of a stack, using sized arrays.
 * Custom for the Card Game, I've added a shuffle method, to shuffle the built in stackData array.
 */
public class Stack<T> {
    private T[] stackData; // The Array hold the data in the stack.
    private int top; // Location of the top of the stack.

    /*
     * Constructor Method
     * Sets the initial location of top to 0, creates new array of requested sized.
     * Parameters:
     *  Type (Class<T>), this parameter is simply to ensure type safety when creating the stack, as suggested in the citation.
     *  Size (int) The size of the stack we want to create. Used for creating the array.
     */
    @SuppressWarnings("unchecked")
    public Stack(Class<T> type, int size){
        this.top = 0;
        this.stackData = (T[])Array.newInstance(type, size);
    }

    /* 
     * T Method
     * Returns the item of type T that's at the top of the stack.
     * Proceeds to set that spot to null, and decrement the top of the stack.
     * 
     * Exception:
     * StackOverFlowError: I throw this here when it's actually a Stackunderflow. When the top is below the size of the stack.
     */
    public T pop() throws StackOverflowError{
        if(this.top <= 0){
            throw new StackOverflowError("StackUnderflow.");
        }
        T outGoing = this.stackData[top-1];
        this.stackData[top-1] = null;
        this.top--;
        return outGoing;
    }

    /*
     * T Method
     * Returns the current item at the top of the stack, without removing it.
     */
    public T peek(){
        return this.stackData[top-1];
    }

    /*
     * void Method
     * Adds a new item to the stack.
     * Increments the variable that keeps track of the top, while setting the value at that index to the new item.
     * Parameters:
     * item (T) The new data to add to the stack.
     * 
     * Exceptions:
     * StackOverFlow - When a push would be overloading the size of the stack, we throw this.
     */
    public void push(T item) throws StackOverflowError{
        if(this.top + 1 > this.stackData.length){
            throw new StackOverflowError();
        }
        this.stackData[this.top++] = item;
    }

    /*
     * Int method
     * Returns the size of the array, since that's the size of our stack.
     */
    public int getSize(){
        return this.stackData.length;
    }

    /*
     * Int Method
     * Returns the current value of our top indictor, to say where the stack is.
     */
    public int getCurrentLocation(){
        return this.top;
    }

    /*
     * Void Method
     * Shuffles the items in the stackData, by generating a random index within size, and swapping the values at that location.
     */
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
