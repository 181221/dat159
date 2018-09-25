package no.pederyo;

public class Matrix {
    private int[] key;
    private int[] keyInverse;

    public Matrix(){
        key = new int[] {15, 0, 19, 7};
        keyInverse = inverse(key);
    }
    public Matrix(int[] key) throws Exception {
        this.key = key;
        if(checkInverse(key))
            this.keyInverse = inverse(key);
        else
            throw new Exception("Matrix is not invertable");
    }

    public int[] multiply(int[] n){
        // matrix must be 2byZ
        int[] matrix = new int[n.length];
        int j = 0;
        for(int i = 0; i < n.length/2; i++, j+=2){
            int test = (keyInverse[0] * n[j] + keyInverse[1] * n[j+1]) % 26;
            int test1 = (keyInverse[2] * n[j] + keyInverse[3] *n[j+1]) % 26;
            matrix[j] = test < 0 ? test + 26 : test;
            matrix[j+1] = test1 < 0 ? test1 + 26 : test1;
        }
        return matrix;
    }
    public int[] inverse(int[] m){
        int[] inverse = new int[m.length];
        inverse[0] = m[3] % 26;
        inverse[1] = -m[1] % 26;
        inverse[2] = -m[2] % 26;
        inverse[3] = m[0] % 26;
        return inverse;
    }

    public char[] toChar(int[] m) {
        char[] arr = new char[m.length];
        for(int i = 0; i < m.length; i++) {
            arr[i] = (char)(97+m[i]);
        }
        return arr;
    }

    public int[] toInt(String s) {
        int[] arr = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            arr[i] = ((int)s.charAt(i) - 97);
        }
        return arr;
    }

    public int[] calculateMatrix(int i, String s){
        int[] vector = new int[2];
        vector[0] = s.charAt(i)-97;
        vector[1] = s.charAt(i+1)-97;
        int math = key[0] * vector[0] + key[1] * vector[1];
        int math2 = key[2] * vector[0] + key[3] * vector[1];
        int matrixValue1 = math < 0 ? (math+26) % 26 : math % 26;
        int matrixValue2 = math2 < 0 ? (math2+26) % 26 : math2 % 26;
        return new int[]{matrixValue1, matrixValue2};
        //return new char[] {(char)(97+matrixValue1), (char)(97+matrixValue2)};
    }


    private boolean checkInverse(int[] matrix){
        int det = calculateDet(matrix);
        return det != 0 && Utility.gcd(det, 26) == 1;
    }

    private int calculateDet(int[] matrix){
        if(matrix.length > 4) {
            throw new IllegalArgumentException("Matrix can only be 2d");
        }
        return (matrix[0]*matrix[3])-(matrix[2]*matrix[3]);
    }

    public int[] getKey() {
        return key;
    }

    public void setKey(int[] key) {
        this.key = key;
    }

    public int[] getKeyInverse() {
        return keyInverse;
    }

    public void setKeyInverse(int[] keyInverse) {
        this.keyInverse = keyInverse;
    }
}
