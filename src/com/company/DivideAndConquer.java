package com.company;

import java.util.Random;

public class DivideAndConquer {
    int n;
    int p;
    int r;
    int [] A;

    public DivideAndConquer(int[] A){
        this.A = A;
        this.n = A.length;
        this.p = 0;
        this.r = A.length - 1;
    }

    public int partition(int[] A, int p, int r){
        int x = A[r];
        int i = p - 1;
        for(int j = p; j <= r - 1; j++){
            if(A[j] <= x){
                i = i + 1;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        int temp = A[i + 1];
        A[i + 1] = A[r];
        A[r] = temp;
        return i + 1;
    }

    public int randomizedPartition(int[] A, int p, int r){
        Random random = new Random();
        int i = random.nextInt(r - p + 1) + p;
        int temp = A[i];
        A[i] = A[r];
        A[r] = temp;
        return partition(A, p, r);
    }

    public int randomizedSelect(int[] A, int p, int r, int i){
        if(p == r)
            return A[p];
        int q = randomizedPartition(A, p, r);
        int k = q - p + 1;
        if(i == k)
            return A[q];
        else if(i < k)
            return randomizedSelect(A, p, q - 1, i);
        else
            return randomizedSelect(A, q + 1, r, i - k);
    }
}
