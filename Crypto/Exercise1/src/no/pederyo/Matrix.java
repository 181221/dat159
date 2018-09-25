package no.pederyo;


public class Matrix {
    private int[] matrix;
    private int[] inverse;
    private int det;

    public Matrix(){
        matrix = new int[] {15, 0, 19, 7};
        det = calculateDet();
        try {
            inverse = inverse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Matrix(int[] matrix) {
        this.matrix = matrix;
    }

    public int[] multiply(int[] m){
        int[] matrixKey = m;
        int[] multipliedMatrix = new int[matrix.length];
        int j = 0;
        for(int i = 0; i < matrix.length/2; i++, j+=2){
            int vecFirstRow = (matrixKey[0] * matrix[j] + matrixKey[1] * matrix[j+1]) % 26;
            int vecSecondRow = (matrixKey[2] * matrix[j] + matrixKey[3] * matrix[j+1]) % 26;
            multipliedMatrix[j] = vecFirstRow < 0 ? vecFirstRow + 26 : vecFirstRow;
            multipliedMatrix[j+1] = vecSecondRow < 0 ? vecSecondRow + 26 : vecSecondRow;
        }
        return multipliedMatrix;
    }
    public int[] inverse() throws Exception {
        if(checkInverse()){
            int[] inverse = new int[matrix.length];
            inverse[0] = (matrix[3] % 26);
            inverse[1] = -matrix[1] % 26;
            inverse[2] = (-matrix[2] % 26);
            inverse[3] = matrix[0] % 26;
            return inverse;
        }
        throw new Exception("Matrix does not have an inverse");

    }

    private boolean checkInverse(){
        return det != 0 && Utility.gcd(det, 26) == 1;
    }

    private int calculateDet(){
        if(matrix.length > 4) {
            throw new IllegalArgumentException("Matrix can only be 2d");
        }
        return (matrix[0]* matrix[3])-(matrix[1]* matrix[2]);
    }

    public int[] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[] matrix) {
        this.matrix = matrix;
    }

    public int[] getInverse() {
        return inverse;
    }

    public void setInverse(int[] inverse) {
        this.inverse = inverse;
    }
}
