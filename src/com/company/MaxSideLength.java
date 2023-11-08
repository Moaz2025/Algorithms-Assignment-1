package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class MaxSideLength {
    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public long maxSquareLength(Point[] points) {
        if (points.length == 0) {
                return 0;
        }

        Point[] sortedPointsX = Arrays.copyOf(points, points.length);
        Point[] sortedPointsY = Arrays.copyOf(points, points.length);

        Arrays.sort(sortedPointsX, Comparator.comparingInt(p -> p.x));
        Arrays.sort(sortedPointsY, Comparator.comparingInt(p -> p.y));

        return maxSquareLengthRec(sortedPointsX, sortedPointsY);
    }

    public long maxSquareLengthRec(Point[] sortedPointsX, Point[] sortedPointsY) {
        if (sortedPointsX.length <= 3) {
            long maxDist = 0;
            for (int i = 0; i < sortedPointsX.length; i++) {
                for (int j = i + 1; j < sortedPointsX.length; j++) {
                    long dist = Math.max(
                            Math.abs(sortedPointsX[i].x - sortedPointsX[j].x),
                            Math.abs(sortedPointsX[i].y - sortedPointsX[j].y)
                    );
                    maxDist = Math.max(maxDist, dist);
                }
            }
            return maxDist;
        }

        int mid = sortedPointsX.length / 2;
        Point[] leftX = Arrays.copyOfRange(sortedPointsX, 0, mid);
        Point[] rightX = Arrays.copyOfRange(sortedPointsX, mid, sortedPointsX.length);

        long midX = (leftX[leftX.length - 1].x + rightX[0].x) / 2;

        Point[] leftY = new Point[0];
        Point[] rightY = new Point[0];

        for (Point point : sortedPointsY) {
            if (point.x <= midX) {
                leftY = Arrays.copyOf(leftY, leftY.length + 1);
                leftY[leftY.length - 1] = point;
            } else {
                rightY = Arrays.copyOf(rightY, rightY.length + 1);
                rightY[rightY.length - 1] = point;
            }
        }

        long leftMax = maxSquareLengthRec(leftX, leftY);
        long rightMax = maxSquareLengthRec(rightX, rightY);
        long d = Math.min(leftMax, rightMax);

        Point[] strip = new Point[0];
        for (Point point : sortedPointsY) {
            if (Math.abs(midX - point.x) < d) {
                strip = Arrays.copyOf(strip, strip.length + 1);
                strip[strip.length - 1] = point;
            }
        }

        return Math.max(d, stripClosest(strip, d));
    }

    public long stripClosest(Point[] strip, long d) {
        long min = d;

        Arrays.sort(strip, Comparator.comparingInt(p -> p.y));

        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && strip[j].y - strip[i].y < min; j++) {
                long dist = Math.max(
                        Math.abs(strip[i].x - strip[j].x),
                        Math.abs(strip[i].y - strip[j].y)
                );
                min = Math.min(min, dist);
            }
        }

        return min;
    }

    public long solve(String inputFile) {
        try {
            File file = new File(inputFile);
            BufferedReader br = new BufferedReader(new FileReader(file));

            int n = Integer.parseInt(br.readLine());
            Point[] array = new Point[n];

            for (int i = 0; i < n; i++) {
                String[] point = br.readLine().split(" ");
                array[i] = new Point(Integer.parseInt(point[0]), Integer.parseInt(point[1]));
            }

            br.close();

            long maximumSideLength = maxSquareLength(array);
            return maximumSideLength;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
        MaxSideLength maxSideLength = new MaxSideLength();
        long max = maxSideLength.solve("C:\\Users\\Moaz\\Desktop\\Algorithms-Assignment-1\\src\\com\\company\\file.txt");
        System.out.println("Maximum side length = " + max);
    }
}


