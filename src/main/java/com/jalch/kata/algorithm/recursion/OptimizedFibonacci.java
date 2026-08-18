package com.jalch.kata.algorithm.recursion;

public class OptimizedFibonacci {

    public long calculate(int input, long[] mem) throws IllegalArgumentException {
        if (input < 0)
            throw new IllegalArgumentException("Cannot use this function to calculate the Fibonacci number of a negative input.");
        if (input <= 1)
            return input;
        if (mem[input] == 0)
            mem[input] = calculate(input - 1, mem) + calculate(input - 2, mem);

        return mem[input];
    }

}
