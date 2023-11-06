package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n, i, algorithm;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 for divide and conquer, 2 for linear time selection: ");
        algorithm = scanner.nextInt();
        System.out.println("Enter the size of the array: ");
        n = scanner.nextInt();
        int [] A = new int[n];
        System.out.println("Enter the array's elements: ");
        for (int k = 0; k < n; k++) {
            A[k] = scanner.nextInt();
        }
        System.out.println("Enter the index of the ith element: ");
        i = scanner.nextInt();
        if(algorithm == 1){
            DivideAndConquer divideAndConquer = new DivideAndConquer(A);
            int result = divideAndConquer.randomizedSelect(A, 0, A.length - 1, i);
            System.out.println(result);
        }
        else if(algorithm == 2){
            LinearTimeSelection linearTimeSelection = new LinearTimeSelection(A);
            int result = linearTimeSelection.select(A, 0,A.length - 1, i);
            System.out.println(result);
        }
    }
}
