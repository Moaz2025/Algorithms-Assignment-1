package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n, algorithm = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        n = scanner.nextInt();
        int [] A = new int[n];
        System.out.println("Enter the array's elements: ");
        for (int k = 0; k < n; k++) {
            A[k] = scanner.nextInt();
        }
        int i = (n - 1) / 2 + 1;
        while(algorithm != 0) {
            System.out.println("Enter 1 for divide and conquer, 2 for linear time selection, 3 for naive method, 0 to exit: ");
            algorithm = scanner.nextInt();
            if (algorithm == 1) {
                DivideAndConquer divideAndConquer = new DivideAndConquer(A);
                long startTime = System.currentTimeMillis();
                int result = divideAndConquer.randomizedSelect(A, 0, A.length - 1, i);
                long endTime = System.currentTimeMillis();
                System.out.println("Median is: " + result);
                System.out.println("Running time = " + (endTime - startTime) + " ms");
            } else if (algorithm == 2) {
                LinearTimeSelection linearTimeSelection = new LinearTimeSelection(A);
                long startTime = System.currentTimeMillis();
                int result = linearTimeSelection.select(A, 0, A.length - 1, i);
                long endTime = System.currentTimeMillis();
                System.out.println("Median is: " + result);
                System.out.println("Running time = " + (endTime - startTime) + " ms");
            } else if (algorithm == 3) {
                NaiveMethod naiveMethod = new NaiveMethod(A);
                long startTime = System.currentTimeMillis();
                int result = naiveMethod.kthSmallest(A, i);
                long endTime = System.currentTimeMillis();
                System.out.println("Median is: " + result);
                System.out.println("Running time = " + (endTime - startTime) + " ms");
            }
        }
    }
}
