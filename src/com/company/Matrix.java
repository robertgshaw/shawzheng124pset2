package com.company;

/**
 * Created by robertshaw on 3/12/16.
 */
public class Matrix {

    public static void compareStrassen4MatrixMultiply(int[][] matrix1, int[][] matrix2, int[][] matrix1A, int[][] matrix2A, int rowA, int colA,
                                                      int rowB, int colB,
                                                      int size, int iterations) {
        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();
        Matrix.multiply(matrix1, matrix2);
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
        System.out.println("");

        startTime = System.currentTimeMillis();
        Matrix.strassen4(matrix1A, matrix2A, rowA, colA, rowB, colB, size);
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
        System.out.println("");
    }

    // sum of matrices
    public static int[][] sum(int[][] matrix1, int[][] matrix2) {
        // assures matrices "match up"
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

    // combine matrices without copying info
    // matrix 1 and 2 are of size n we are adding submatrices of 1,2
    // assumes matrices are square
    public static int[][] sum2(int[][] matrix1, int[][] matrix2, int row1, int col1, int row2, int col2, int size) {
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            // calculates sum, using new indexing from "location"
            int[][] combo = new int[size][size];
            // iterates through elements of the matrices and adds them together
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    combo[i][j] = matrix1[row1 + i][col1 + j] + matrix2[row2 + i][col2 + j];
                }
            }
            // return result
            return combo;

        } else {
            int[][] garbage = new int[1][1];
            garbage[0][0] = Integer.MIN_VALUE;
            return garbage;
        }
    }

    // difference of matrices
    public static int[][] difference(int[][] matrix1, int[][] matrix2) {
        // assures matrices "match up"
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

    // difference of matrices without copying info
    // matrix 1 and 2 are of size n we are adding submatrices of 1,2
    // assumes matrices are square
    public static int[][] difference2(int[][] matrix1, int[][] matrix2, int row1, int col1, int row2, int col2, int size) {
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            // calculates sum, using new indexing from "location"
            int[][] combo = new int[size][size];
            // iterates through elements of the matrices and adds them together
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    combo[i][j] = matrix1[row1 + i][col1 + j] - matrix2[row2 + i][col2 + j];
                }
            }
            // return result
            return combo;

        } else {
            int[][] garbage = new int[1][1];
            garbage[0][0] = Integer.MIN_VALUE;
            return garbage;
        }
    }

    // multiply matrices
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

    // multiply matrices
    public static int[][] multiply2(int[][] matrix1, int[][] matrix2, int row1, int col1, int row2, int col2, int size) {
        // initializes the product matrix to be returned
        int[][] product = new int[size][size];

        // iterates rows of matrix 1
        for (int i = 0; i < size; i++) {
            int dotProduct = 0;
            // rows of matrix 2
            for (int j = 0; j < size; j++) {
                // iterates columns of matrix 2
                for (int k = 0; k < size; k++) {
                    dotProduct = dotProduct + matrix1[row1 + i][col1 + k] * matrix2[row2 + k][col2 + j];
                }

                product[i][j] = dotProduct;
                dotProduct = 0;
            }
        }

        return product;

    }


    // strassen that changes to regular matrix multiply after 1 iteration
    public static int[][] strassen4(int[][] matrixA, int[][] matrixB,
                                    int rowA, int colA,
                                    int rowB, int colB,
                                    int size) {
        // base case
        // when we get down to 1, cannot recurse any deeper
        if (size == 1) {
            int[][] product = new int[1][1];
            product[0][0] = matrixA[rowA][colA] * matrixB[rowB][colB];
            return product;
        }
        // recursive case, create smaller matrices and find their values recursively
        else {
            // finds start index of the subMatrixArray that is passed around in order to minimize the amount
            // of initialization that occurs
            int subSize = size / 2;
            int[][] product = new int[size][size];


            // 7 multiplications, does not copy any data
            // keeps track of "start" and "size" of the matrices in order to avoid recopying data multiple times
            // combines though generate a new matrix. in order to be created, we must look at data of matrix A and B, so
            // creating a new matrix is necessary

            int[][] matrixM1 = Matrix.multiply2(
                    Matrix.sum2(matrixA, matrixA, rowA, colA, (rowA + subSize), (colA + subSize), subSize),
                    Matrix.sum2(matrixB, matrixB, rowB, colB, (rowB + subSize), (colB + subSize), subSize),
                    0, 0, 0, 0, subSize);

            int[][] matrixM2 = Matrix.multiply2(
                    Matrix.sum2(matrixA, matrixA, (rowA + subSize), colA, (rowA + subSize), (colA + subSize), subSize),
                    matrixB,
                    0, 0, rowB, colB, subSize);

            int[][] matrixM3 = Matrix.multiply2(
                    matrixA,
                    Matrix.difference2(matrixB, matrixB, rowB, (colB + subSize), (rowB + subSize), (colB + subSize), subSize),
                    rowA, colA, 0, 0, subSize);

            int[][] matrixM4 = Matrix.multiply2(
                    matrixA,
                    Matrix.difference2(matrixB, matrixB, (rowB + subSize), colB, rowB, colB, subSize),
                    rowA + subSize, colA + subSize, 0, 0, subSize);

            int[][] matrixM5 = Matrix.multiply2(
                    Matrix.sum2(matrixA, matrixA, rowA, colA, rowA, (colA + subSize), subSize),
                    matrixB,
                    0, 0, rowB + subSize, colB + subSize, subSize);

            int[][] matrixM6 = Matrix.multiply2(
                    Matrix.difference2(matrixA, matrixA, (rowA + subSize), colA, rowA, colA, subSize),
                    Matrix.sum2(matrixB, matrixB, rowB, colB, rowB, (colB + subSize), subSize),
                    0, 0, 0, 0, subSize);

            int[][] matrixM7 = Matrix.multiply2(
                    Matrix.difference2(matrixA, matrixA, rowA, (colA + subSize), (rowA + subSize), (colA + subSize), subSize),
                    Matrix.sum2(matrixB, matrixB, (rowB + subSize), colB, (rowB + subSize), (colB + subSize), subSize),
                    0, 0, 0, 0, subSize);

            // recombination
            for (int i = 0; i < subSize; i++) {
                for (int j = 0; j < subSize; j++) {
                    product[i][j] = matrixM1[i][j] + matrixM4[i][j] - matrixM5[i][j] + matrixM7[i][j];
                    product[i][j + subSize] = matrixM3[i][j] + matrixM5[i][j];
                    product[i + subSize][j] = matrixM2[i][j] + matrixM4[i][j];
                    product[i + subSize][j + subSize] = matrixM1[i][j] - matrixM2[i][j] + matrixM3[i][j] + matrixM6[i][j];
                }
            }

            // return the new matrix, updated values
            return product;
        }

    }

    // current optimal strassen: avoids copying the data by passing the "start" and "end" ptrs, avoids allocating arrays
    // by passing around log(n) * 7 arrays which are reused multiple times to store M1....M7
    public static int[][] strassen3(int[][] matrixA, int[][] matrixB,
                                    int rowA, int colA,
                                    int rowB, int colB,
                                    int size, int[][][] subMatrixArray,
                                    int nOriginal) {
        // base case
        // when we get down to 1, cannot recurse any deeper
        if (size == 1) {
            int[][] product = new int[1][1];
            product[0][0] = matrixA[rowA][colA] * matrixB[rowB][colB];
            return product;
        }
        // recursive case, create smaller matrices and find their values recursively
        else {
            int subSize = size / 2;
            int[][] product = new int[size][size];
            int subMatrixArrayStartIndex = Matrix.log(nOriginal/size, 2) * 7;

            // 7 multiplications, does not copy any data
            // keeps track of "start" and "size" of the matrices in order to avoid recopying data multiple times
            // combines though generate a new matrix. in order to be created, we must look at data of matrix A and B, so
            // creating a new matrix is necessary
            subMatrixArray[subMatrixArrayStartIndex] = Matrix.strassen3(
                    Matrix.sum2(matrixA, matrixA, rowA, colA, (rowA + subSize), (colA + subSize), subSize),
                    Matrix.sum2(matrixB, matrixB, rowB, colB, (rowB + subSize), (colB + subSize), subSize),
                    0, 0, 0, 0, subSize, subMatrixArray, nOriginal);
            subMatrixArray[subMatrixArrayStartIndex + 1] = Matrix.strassen3(
                    Matrix.sum2(matrixA, matrixA, (rowA + subSize), colA, (rowA + subSize), (colA + subSize), subSize),
                    matrixB,
                    0, 0, rowB, colB, subSize, subMatrixArray, nOriginal);
            subMatrixArray[subMatrixArrayStartIndex + 2] = Matrix.strassen3(
                    matrixA,
                    Matrix.difference2(matrixB, matrixB, rowB, (colB + subSize), (rowB + subSize), (colB + subSize), subSize),
                    rowA, colA, 0, 0, subSize, subMatrixArray, nOriginal);
            subMatrixArray[subMatrixArrayStartIndex + 3] = Matrix.strassen3(
                    matrixA,
                    Matrix.difference2(matrixB, matrixB, (rowB + subSize), colB, rowB, colB, subSize),
                    rowA + subSize, colA + subSize, 0, 0, subSize, subMatrixArray, nOriginal);
            subMatrixArray[subMatrixArrayStartIndex + 4] = Matrix.strassen3(
                    Matrix.sum2(matrixA, matrixA, rowA, colA, rowA, (colA + subSize), subSize),
                    matrixB,
                    0, 0, rowB + subSize, colB + subSize, subSize, subMatrixArray, nOriginal);
            subMatrixArray[subMatrixArrayStartIndex + 5] = Matrix.strassen3(
                    Matrix.difference2(matrixA, matrixA, (rowA + subSize), colA, rowA, colA, subSize),
                    Matrix.sum2(matrixB, matrixB, rowB, colB, rowB, (colB + subSize), subSize),
                    0, 0, 0, 0, subSize, subMatrixArray, nOriginal);
            subMatrixArray[subMatrixArrayStartIndex + 6] = Matrix.strassen3(
                    Matrix.difference2(matrixA, matrixA, rowA, (colA + subSize), (rowA + subSize), (colA + subSize), subSize),
                    Matrix.sum2(matrixB, matrixB, (rowB + subSize), colB, (rowB + subSize), (colB + subSize), subSize),
                    0, 0, 0, 0, subSize, subMatrixArray, nOriginal);

            // recombination
            for (int i = 0; i < subSize; i++) {
                for (int j = 0; j < subSize; j++) {
                    product[i][j] = subMatrixArray[subMatrixArrayStartIndex][i][j] + subMatrixArray[subMatrixArrayStartIndex + 3][i][j] -
                                                    subMatrixArray[subMatrixArrayStartIndex + 4][i][j] + subMatrixArray[subMatrixArrayStartIndex + 6][i][j];
                    product[i][j + subSize] = subMatrixArray[subMatrixArrayStartIndex + 2][i][j] + subMatrixArray[subMatrixArrayStartIndex + 4][i][j];
                    product[i + subSize][j] = subMatrixArray[subMatrixArrayStartIndex + 1][i][j] + subMatrixArray[subMatrixArrayStartIndex + 3][i][j];
                    product[i + subSize][j + subSize] = subMatrixArray[subMatrixArrayStartIndex][i][j] - subMatrixArray[subMatrixArrayStartIndex + 1][i][j] +
                                                        subMatrixArray[subMatrixArrayStartIndex + 2][i][j] + subMatrixArray[subMatrixArrayStartIndex + 5][i][j];
                }
            }

            // return the new matrix, updated values
            return product;
        }

    }

    // updated strassen
    // currently works for n = 2^k
    // does not copy data necessarily
    // improved from strassen regular
    public static int[][] strassen2(int[][] matrixA, int[][] matrixB,
                                    int rowA, int colA,
                                    int rowB, int colB,
                                    int size) {
        // base case
        // when we get down to 1, cannot recurse any deeper
        if (size == 1) {
            int[][] product = new int[1][1];
            product[0][0] = matrixA[rowA][colA] * matrixB[rowB][colB];
            return product;
        }
        // recursive case, create smaller matrices and find their values recursively
        else {
            int subSize = size / 2;
            int[][] product = new int[size][size];

            // 7 multiplications, does not copy any data
            // keeps track of "start" and "size" of the matrices in order to avoid recopying data multiple times
            // combines though generate a new matrix. in order to be created, we must look at data of matrix A and B, so
            // creating a new matrix is necessary
            int[][] matrixM1 = Matrix.strassen2(
                    Matrix.sum2(matrixA, matrixA, rowA, colA, (rowA + subSize), (colA + subSize), subSize),
                    Matrix.sum2(matrixB, matrixB, rowB, colB, (rowB + subSize), (colB + subSize), subSize),
                    0, 0, 0, 0, subSize);
            int[][] matrixM2 = Matrix.strassen2(
                    Matrix.sum2(matrixA, matrixA, (rowA + subSize), colA, (rowA + subSize), (colA + subSize), subSize),
                    matrixB,
                    0, 0, rowB, colB, subSize);
            int[][] matrixM3 = Matrix.strassen2(
                    matrixA,
                    Matrix.difference2(matrixB, matrixB, rowB, (colB + subSize), (rowB + subSize), (colB + subSize), subSize),
                    rowA, colA, 0, 0, subSize);
            int[][] matrixM4 = Matrix.strassen2(
                    matrixA,
                    Matrix.difference2(matrixB, matrixB, (rowB + subSize), colB, rowB, colB, subSize),
                    rowA + subSize, colA + subSize, 0, 0, subSize);
            int[][] matrixM5 = Matrix.strassen2(
                    Matrix.sum2(matrixA, matrixA, rowA, colA, rowA, (colA + subSize), subSize),
                    matrixB,
                    0, 0, rowB + subSize, colB + subSize, subSize);
            int[][] matrixM6 = Matrix.strassen2(
                    Matrix.difference2(matrixA, matrixA, (rowA + subSize), colA, rowA, colA, subSize),
                    Matrix.sum2(matrixB, matrixB, rowB, colB, rowB, (colB + subSize), subSize),
                    0, 0, 0, 0, subSize);
            int[][] matrixM7 = Matrix.strassen2(
                    Matrix.difference2(matrixA, matrixA, rowA, (colA + subSize), (rowA + subSize), (colA + subSize), subSize),
                    Matrix.sum2(matrixB, matrixB, (rowB + subSize), colB, (rowB + subSize), (colB + subSize), subSize),
                    0, 0, 0, 0, subSize);

            // recombination
            for (int i = 0; i < subSize; i++) {
                for (int j = 0; j < subSize; j++) {
                    product[i][j] = matrixM1[i][j] + matrixM4[i][j] - matrixM5[i][j] + matrixM7[i][j];
                    product[i][j + subSize] = matrixM3[i][j] + matrixM5[i][j];
                    product[i + subSize][j] = matrixM2[i][j] + matrixM4[i][j];
                    product[i + subSize][j + subSize] = matrixM1[i][j] - matrixM2[i][j] + matrixM3[i][j] + matrixM6[i][j];
                }
            }

            // return the new matrix, updated values
            return product;
        }
    }

    // currently only works with matrixes of size n = 2^k
    // strassen's algorithm for multiply
    public static int[][] strassen(int[][] matrixA, int[][] matrixB) {
        // confirm we have matrices of the same size
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

    // print matrices
    public static void printMatrix (int[][] matrix) {
        String row = "";
        // print row by row
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                row =  row + " " + matrix[i][j];
            }

            System.out.println(row);
            row = "";
        }
    }

    public static int log(int x, int base)
    {
        return (int) (Math.log(x) / Math.log(base));
    }
}
