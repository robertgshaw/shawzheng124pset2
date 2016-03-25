package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int n = 511;

        int[][] matrix1 = new int[n][n];
        int[][] matrix2 = new int[n][n];
        int[][] matrixA1;
        int[][] matrixA2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Math.random() < 0.5) {
                    matrix1[i][j] = 0;
                } else {
                    matrix1[i][j] = 1;
                }

                if (Math.random() < 0.5) {
                    matrix2[i][j] = 0;
                } else {
                    matrix2[i][j] = 1;
                }
            }
        }

        long startTime;
        long endTime;


        startTime = System.currentTimeMillis();
        int[][] matrix3 = Matrix.multiply2(matrix1, matrix2, 0, 0, 0, 0, n);
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
        System.out.println("");

        startTime = System.currentTimeMillis();
        int[][] strassen = Matrix.strassen5(n/2, matrix1, matrix2, 0,0,0,0, n, new int[7][][],n);
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
        System.out.println("");

        Matrix.runStrassen(72, matrix1, matrix2, n);
    }
}
