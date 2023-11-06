package com.company;

import java.util.Arrays;

public class NaiveMethod {
    int n;
    int[] A;

    public NaiveMethod(int[] A){
        this.A = A;
        this.n = A.length;
    }

    public int kthSmallest(int[] A, int k){
        Arrays.sort(A);
        return A[k - 1];
    }
}
