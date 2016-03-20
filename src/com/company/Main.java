package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[][] matrix1 = new int[4][4];
        int[][] matrix2 = new int[4][4];

        // matrix 1
        matrix1[0][0] = 1;
        matrix1[0][1] = 2;
        matrix1[0][2] = 3;
        matrix1[0][3] = 4;

        matrix1[1][0] = 5;
        matrix1[1][1] = 6;
        matrix1[1][2] = 7;
        matrix1[1][3] = 8;

        matrix1[2][0] = 9;
        matrix1[2][1] = 10;
        matrix1[2][2] = 11;
        matrix1[2][3] = 12;

        matrix1[3][0] = 13;
        matrix1[3][1] = 14;
        matrix1[3][2] = 15;
        matrix1[3][3] = 16;

        // matrix 2
        matrix2[0][0] = 17;
        matrix2[0][1] = 18;
        matrix2[0][2] = 19;
        matrix2[0][3] = 20;

        matrix2[1][0] = 21;
        matrix2[1][1] = 22;
        matrix2[1][2] = 23;
        matrix2[1][3] = 24;

        matrix2[2][0] = 25;
        matrix2[2][1] = 26;
        matrix2[2][2] = 27;
        matrix2[2][3] = 28;

        matrix2[3][0] = 29;
        matrix2[3][1] = 30;
        matrix2[3][2] = 31;
        matrix2[3][3] = 32;

        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();
        int[][] product = Matrix.multiply(matrix1, matrix2);
        Matrix.printMatrix(product);
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
        System.out.println("");

        startTime = System.currentTimeMillis();
        int[][] strassen = Matrix.strassen(matrix1, matrix2);
        Matrix.printMatrix(strassen);
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
        System.out.println("");

        startTime = System.currentTimeMillis();
        int[][] strassenFAST = Matrix.strassen2(matrix1, matrix2, 0, 0, 0, 0, matrix1.length);
        Matrix.printMatrix(strassenFAST);
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
        System.out.println("");
    }
}
