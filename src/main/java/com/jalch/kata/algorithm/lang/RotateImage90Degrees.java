package com.jalch.kata.algorithm.lang;

public class RotateImage90Degrees {

    // =================================================================
    // Given an N x N matrix, rotate the matrix 90 degrees clockwise. 
    // The performed rotation should be in place, i.e., the given matrix 
    // is modified directly without allocating another matrix.
    // =================================================================

    // Time complexity O(n^2) -> Benchmark(N=1000) = 214.84 ms
    // Space complexity O(N) - tempArr per grid.
    public static int[][] rotateWithTempArr(int[][] matrix) {
        if (matrix == null || matrix.length <= 1)
            return matrix;
        int gridLength = matrix.length;
        int topIndex = 0;
        int rigthIndex = matrix.length - 1;
        int leftIndex = 0;
        int bottomIndex = matrix.length - 1;
        while (gridLength > 1) {
            int[] tempArr = new int[gridLength - 1];
            // Top row collect gridLength-1th elements.
            for (int i = 0; i < tempArr.length; i++) {
                tempArr[i] = matrix[topIndex][i + leftIndex];
            }
            // Right column collect and write gridLength-1th elements.
            for (int i = 0; i < tempArr.length; i++) {
                int cellValue = matrix[topIndex + i][rigthIndex];
                matrix[topIndex + i][rigthIndex] = tempArr[i];
                tempArr[i] = cellValue;
            }
            // Bottom row collect and write gridLength-1th elements.
            for (int i = 0; i < tempArr.length; i++) {
                int cellValue = matrix[bottomIndex][rigthIndex - i];
                matrix[bottomIndex][rigthIndex - i] = tempArr[i];
                tempArr[i] = cellValue;
            }
            // Left row collect and write gridLength-1th elements.
            for (int i = 0; i < tempArr.length; i++) {
                int cellValue = matrix[bottomIndex - i][leftIndex];
                matrix[bottomIndex - i][leftIndex] = tempArr[i];
                tempArr[i] = cellValue;
            }
            // Top row write gridLength-1th elements.
            for (int i = 0; i < tempArr.length; i++) {
                matrix[topIndex][leftIndex + i] = tempArr[i];
            }
            topIndex++;
            leftIndex++;
            rigthIndex--;
            bottomIndex--;
            // Reduce the gridLenght by 2
            gridLength -= 2;
        }
        return matrix;
    }

    // BEST
    // Time complexity O(n^2) -> Benchmark(N=1000) = 178.79 ms
    // Space complexity O(1) - no extra space used that grows as N grows.
    public static int[][] rotateByLayers(int[][] matrix) {
        if (matrix == null || matrix.length <= 1)
            return matrix;
        int n = matrix.length;
        // Iterate through each layer from outer to inner
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                // 1. Save top element
                int top = matrix[first][i];
                // 2. Move left to top
                matrix[first][i] = matrix[last - offset][first];
                // 3. Move bottom to left
                matrix[last - offset][first] = matrix[last][last - offset];
                // 4. Move right to bottom
                matrix[last][last - offset] = matrix[i][last];
                // 5. Move top to right
                matrix[i][last] = top;
            }
        }
        return matrix;
    }

    // Time complexity O(n^2) -> Benchmark(N=1000) = 252.07 ms
    // Space complexity O(1) - no extra space used that grows as N grows.
    public static int[][] rotateByTransposingAndRowReversal(int[][] matrix) {
        if (matrix == null || matrix.length <= 1)
            return matrix;
        int n = matrix.length;
        // Transposing.
        for(int r = 0; r < n; r++){
            //Only the upper triangle.
            for(int c = r+1; c < n; c++){
                int temp = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = temp;
            }   
        }
        //Reversing each row
        for(int r = 0; r < n; r++){
            int left = 0;
            int rigth = n-1;
            while(left < rigth){
                int temp = matrix[r][left];
                matrix[r][left] = matrix[r][rigth];
                matrix[r][rigth] = temp;
                left++;
                rigth--;
            }
        }

        return matrix;
    }
    
}
