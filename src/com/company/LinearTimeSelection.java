package com.company;

public class LinearTimeSelection {
    int n;
    int p;
    int r;
    int [] A;

    public LinearTimeSelection(int[] A){
        this.A = A;
        this.n = A.length;
        this.p = 0;
        this.r = A.length - 1;
    }

    // Modified partition
    public int partition(int[] A, int p, int r, int x){
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

    public int getMedian(int[] A, int p, int r){
        // Sort the subarray Using insertion sort
        for (int i = p; i < r; i++) {
            int key = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }
        return A[p + (r - p) / 2];
    }

    public int select(int[] A, int p, int r, int i) {
        int n = r - p + 1 ;
        /* Divide the array into groups of size 5,
        calculate median of every group
        and store it in median[] array.*/
        int k;
        // There will be floor((n + 4) / 5) groups;
        int[] medians = new int[(n + 4) / 5];
        for (k = 0; k < n / 5; k++)
            medians[k] = getMedian(A, p + k * 5, p + k * 5 + 5);
        // For last group with less than 5 elements
        if (k * 5 < n) {
            medians[k] = getMedian(A, p + k * 5, p + k * 5 + n % 5);
            k++;
        }
        // Find median of all medians
        int medOfMed;
        if(k == 1){
            medOfMed = medians[k - 1];
        }
        else {
            medOfMed = select(medians, 0, k - 1, k / 2);
        }
        // Partition the array around the median of medians
        int position = partition(A, p, r, medOfMed);
        if (position - p == i - 1)
            return A[position];
        else if (position - p > i - 1)
            return select(A, p, position - 1, i);
        else
            return select(A, position + 1, r, i - position + p - 1);
    }
}
