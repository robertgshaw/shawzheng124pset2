package com.company;

/**
 * Created by robertshaw on 3/12/16.
 */
public class Matrix {
    // sum of matrixes
    public static int[][] sum(int[][] matrix1, int[][] matrix2) {
        // assures matrixes "match up"
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            int[][] sum = new int[matrix1.length][matrix1[0].length];
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[0].length; j++) {
                    sum[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
            return sum;
        }
        // garbage value
        else {
            int[][] garbage = new int[1][1];
            garbage[0][0] = Integer.MIN_VALUE;
            return garbage;
        }
    }

    // difference of matrixes
    public static int[][] difference(int[][] matrix1, int[][] matrix2) {
        // assures matrixes "match up"
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            int[][] difference = new int[matrix1.length][matrix1[0].length];
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[0].length; j++) {
                    difference[i][j] = matrix1[i][j] - matrix2[i][j];
                }
            }
            return difference;
        }
        // garbage value
        else {
            int[][] garbage = new int[1][1];
            garbage[0][0] = Integer.MIN_VALUE;
            return garbage;
        }
    }

    // multiply matrixes
    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        // m x n and n x k matrix
        if (matrix1.length == matrix2[0].length) {
            int[][] product = new int[matrix1.length][matrix2[0].length];

            // iterates rows of matrix 1
            for (int i = 0; i < matrix1.length; i++) {
                int dotProduct = 0;
                // rows of matrix 2
                for (int j = 0; j < matrix2[0].length; j++) {
                    // iterates columns of matrix 2
                    for (int k = 0; k < matrix1[0].length; k++) {
                        dotProduct = dotProduct + matrix1[i][k] * matrix2[k][j];
                    }

                    product[i][j] = dotProduct;
                    dotProduct = 0;
                }
            }

            return product;
        }
        // garbage value
        else {
            int[][] garbage = new int[1][1];
            garbage[0][0] = Integer.MIN_VALUE;
            return garbage;
        }
    }

    // currently only works with matrixes of size n = 2^k
    // strassen's algorithm for multiply
    public static int[][] strassen(int[][] matrixA, int[][] matrixB) {
        // confirm we have matrixes of the same size
        if (matrixA.length == matrixB.length && matrixA[0].length == matrixA.length && matrixB.length == matrixB[0].length) {
            // base case
            if (matrixA.length == 1 && matrixA[0].length == 1) {
                int[][] product = new int[1][1];
                product[0][0] = matrixA[0][0] * matrixB[0][0];
                return product;
            }
            // recursive case
            else {
                int[][] product = new int[matrixA.length][matrixA.length];

                // four submatrixes to be multiplied
                int[][] matrixA11 = new int[matrixA.length / 2][matrixA.length / 2];
                int[][] matrixA12 = new int[matrixA.length / 2][matrixA.length / 2];
                int[][] matrixA21 = new int[matrixA.length / 2][matrixA.length / 2];
                int[][] matrixA22 = new int[matrixA.length / 2][matrixA.length / 2];

                int[][] matrixB11 = new int[matrixA.length / 2][matrixA.length / 2];
                int[][] matrixB12 = new int[matrixA.length / 2][matrixA.length / 2];
                int[][] matrixB21 = new int[matrixA.length / 2][matrixA.length / 2];
                int[][] matrixB22 = new int[matrixA.length / 2][matrixA.length / 2];

                // initialize the submatrixes
                for (int i = 0; i < matrixA.length/2; i++) {
                    for (int j = 0; j < matrixA.length/2; j ++) {
                        matrixA11[i][j] = matrixA[i][j];
                        matrixA12[i][j] = matrixA[i][j + matrixA.length/2];
                        matrixA21[i][j] = matrixA[i + matrixA.length/2][j];
                        matrixA22[i][j] = matrixA[i + matrixA.length/2][j + matrixA.length/2];

                        matrixB11[i][j] = matrixB[i][j];
                        matrixB12[i][j] = matrixB[i][j + matrixA.length/2];
                        matrixB21[i][j] = matrixB[i + matrixA.length/2][j];
                        matrixB22[i][j] = matrixB[i + matrixA.length/2][j + matrixA.length/2];
                    }
                }

                // 7 multiplications
                int[][] matrixM1 = Matrix.strassen(Matrix.sum(matrixA11, matrixA22), Matrix.sum(matrixB11, matrixB22));
                int[][] matrixM2 = Matrix.strassen(Matrix.sum(matrixA21, matrixA22), matrixB11);
                int[][] matrixM3 = Matrix.strassen(matrixA11, Matrix.difference(matrixB12, matrixB22));
                int[][] matrixM4 = Matrix.strassen(matrixA22, Matrix.difference(matrixB21, matrixB11));
                int[][] matrixM5 = Matrix.strassen(Matrix.sum(matrixA11, matrixA12), matrixB22);
                int[][] matrixM6 = Matrix.strassen(Matrix.difference(matrixA21, matrixA11), Matrix.sum(matrixB11, matrixB12));
                int[][] matrixM7 = Matrix.strassen(Matrix.difference(matrixA12, matrixA22), Matrix.sum(matrixB21, matrixB22));

                // additions to create product matrix
                int[][] product11 = Matrix.sum(Matrix.sum(matrixM1, matrixM4), Matrix.difference(matrixM7, matrixM5));
                int[][] product12 = Matrix.sum(matrixM3, matrixM5);
                int[][] product21 = Matrix.sum(matrixM2, matrixM4);
                int[][] product22 = Matrix.sum(Matrix.difference(matrixM1, matrixM2), Matrix.sum(matrixM3, matrixM6));

                // recombination
                for (int i = 0; i < matrixA.length/2; i++) {
                    for (int j = 0; j < matrixA.length/2; j++) {
                        product[i][j] = product11[i][j];
                        product[i][j + matrixA.length/2] = product12[i][j];
                        product[i + matrixA.length/2][j] = product21[i][j];
                        product[i + matrixA.length/2][j + matrixA.length/2] = product22[i][j];
                    }
                }

                return product;
            }
        }
        // garbage value
        else {
            int[][] garbage = new int[1][1];
            garbage[0][0] = Integer.MIN_VALUE;
            return garbage;
        }
    }

    // print matrixes
    public static void printMatrix (int[][] matrix) {
        String row = "";

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                row =  row + " " + matrix[i][j];
            }

            System.out.println(row);
            row = "";
        }
    }
}
