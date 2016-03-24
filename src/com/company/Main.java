package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int n = 15;

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

                if(Math.random() < 0.5) {
                    matrix2[i][j] = 0;
                } else {
                    matrix2[i][j] = 1;
                }
            }
        }

        // adds buffers for even/ odd case
        if (n % 2 == 1) {
            matrixA1 = new int[n+1][n+1];
            matrixA2 = new int[n+1][n+1];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j ++) {
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

        long startTime;
        long endTime;

        int[][] product = Matrix.multiply(matrix1, matrix2);
        Matrix.printMatrix(product);
        System.out.println("");

        int[][] product2 = Matrix.strassen4(matrixA1, matrixA2, 0, 0, 0, 0, n+1);
        Matrix.printMatrix(product2);
        System.out.println("");

//        int[][] matrix1A = new int[n/2][n/2];
//        int[][] matrix1B = new int[n/2][n/2];
//        int[][] matrix1C = new int[n/2][n/2];
//        int[][] matrix1D = new int[n/2][n/2];
//
//        for (int i = 0; i < n/2; i ++) {
//            for (int j = 0; j < n/2; j ++) {
//                matrix1A[i][j] = matrix1[i][j];
//                matrix1B[i][j] = matrix1[i][n/2 + j];
//                matrix1C[i][j] = matrix1[n/2 + i][j];
//                matrix1D[i][j] = matrix1[n/2 + i][n/2 + j];
//            }
//        }

//        startTime = System.currentTimeMillis();
//        endTime = System.currentTimeMillis();
//        System.out.println((endTime - startTime) + " milliseconds");
//        System.out.println("");


//        int[][] strassen4 = Matrix.strassen4(matrix1, matrix2, 0, 0, 0, 0, n);
//        Matrix.printMatrix(strassen4);


//        startTime = System.currentTimeMillis();
//        int[][] strassen = Matrix.strassen(matrix1, matrix2);
////        Matrix.printMatrix(strassen);
//        endTime = System.currentTimeMillis();
//        System.out.println((endTime - startTime) + " milliseconds");
//        System.out.println("");
//
//        startTime = System.currentTimeMillis();
//        int[][] strassen2 = Matrix.strassen2(matrix1, matrix2, 0, 0, 0, 0, matrix1.length);
//        //Matrix.printMatrix(strassen2);
//        endTime = System.currentTimeMillis();
//        System.out.println((endTime - startTime) + " milliseconds");
//        System.out.println("");
//
//        startTime = System.currentTimeMillis();
//        int subMatrixArrayLength = 7 * (Matrix.log(n, 2));
//        int[][][] subMatrixArray = new int[subMatrixArrayLength][][];
//        int[][] strassen3 = Matrix.strassen3(matrix1, matrix2, 0, 0, 0, 0, matrix1.length, subMatrixArray, matrix1.length);
//        // Matrix.printMatrix(strassen3);
//        endTime = System.currentTimeMillis();
//        System.out.println((endTime - startTime) + " milliseconds");
//        System.out.println("");
    }
}
