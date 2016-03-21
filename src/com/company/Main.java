package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int n = 16;

        int[][] matrix1 = new int[n][n];
        int[][] matrix2 = new int[n][n];

        // creates 2 matrices
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

        //Matrix.printMatrix(matrix1);
        //System.out.println("");

        //Matrix.printMatrix(matrix2);
        //System.out.println("");

        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();
        int[][] product = Matrix.multiply(matrix1, matrix2);
//        Matrix.printMatrix(product);
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
        System.out.println("");

        startTime = System.currentTimeMillis();
//        int[][] strassen = Matrix.strassen(matrix1, matrix2);
//        Matrix.printMatrix(strassen);
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
        System.out.println("");

        startTime = System.currentTimeMillis();
        int[][] strassen2 = Matrix.strassen2(matrix1, matrix2, 0, 0, 0, 0, matrix1.length);
//        Matrix.printMatrix(strassen2);
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
        System.out.println("");
    }
}
