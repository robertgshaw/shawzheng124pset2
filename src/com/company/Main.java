package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int n = 32;

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

        // adds buffers for even/ odd case
        if (n % 2 == 1) {
            matrixA1 = new int[n + 1][n + 1];
            matrixA2 = new int[n + 1][n + 1];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrixA1[i][j] = matrix1[i][j];
                    matrixA2[i][j] = matrix2[i][j];

                    matrixA1[n][j] = 0;
                    matrixA2[n][j] = 0;

                    matrixA1[i][n] = 0;
                    matrixA2[i][n] = 0;
                }
            }

            matrixA1[n][n] = 0;
            matrixA2[n][n] = 0;
        } else {
            matrixA1 = matrix1;
            matrixA2 = matrix2;
        }

        Matrix.compareStrassen5MatrixMultiply(8, matrix1, matrix2, matrixA1, matrixA2, 0, 0, 0, 0, n, 10);
    }
}
