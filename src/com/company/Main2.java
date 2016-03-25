import java.util.*;
import java.io.*;

/**
 * Reads ASCII files for matrix multiplication, should be run for diagonal matrix output for assignment
 * Created by Ben on 3/24/2016.
 */
public class Main2 {
    public static void main(String[] args) {
        int dimension = Integer.parseInt(args[1]);
        String inputFile = args[2];

        try {
            File file = new File(inputFile);
            Scanner in = new Scanner(file);

            int padSize = Matrix.valueToPad(64, dimension);

            int[][] matrix1 = new int[padSize][padSize];
            int[][] matrix2 = new int[padSize][padSize];

            for (int i = 0; i < padSize; i++) {
                for (int j = 0; j < padSize; j++) {
                    // Static padding
                    if (i >= dimension || j >= dimension) {
                        matrix1[i][j] = 0;
                    }
                    else {
                        matrix1[i][j] = Integer.parseInt(in.nextLine());
                    }
                }
            }

            for (int i = 0; i < padSize; i++) {
                for (int j = 0; j < padSize; j++) {
                    if (i >= dimension || j >= dimension) {
                        matrix1[i][j] = 0;
                    }
                    else {
                        matrix2[i][j] = Integer.parseInt(in.nextLine());
                    }
                }
            }

            int[][] finalMatrix = Matrix.strassen5(64, matrix1, matrix2, 0, 0, 0, 0, padSize, new int[Matrix.log((int) Math.ceil((double) padSize / 96), 2) * 7 + 7][][], padSize);

            for (int i = 0; i < dimension; i++) {
                System.out.println(finalMatrix[i][i]);
            }

            System.out.println("");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
