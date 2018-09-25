/**
 * 
 */
package no.pederyo;

import java.util.Arrays;

/**
 * @author tosindo
 *  Affinecipher:
 *  y = ek(x) = ax +bmod26
 *  k = (a,b), with the restriction:gcd(a,26) = 1
 *  decrypt x = ia(y-b)mod26
 *
 *  step 1. Calculate A * B mod C for B values 0 through C-1
 *  step 2. The modular inverse of A mod C is the B value that makes A * B mod C = 1
 *
 *  Hillcipher:
 *  Polyaplhabetic cipher - Requires at least 2 keys.
 *  Uses a linear combination of the key with the plaintext to form
 *  the ciphertext
 *
 *  The matrix k must be invertible
 *  if det k = 0 then k is not invertible
 *  check if k has an inverse in modulo N by checking if gcd(det K, N) = 1
 *
 */
public class Utility {
    private int key1 = 5;
    private int key2 = 7;
    private int inverse = 21;
    private Matrix matrixKey;
    public Utility(){
        matrixKey = new Matrix();
    }

    public char[] encryptAffine(String s){
        char[] encryptedChar = new char[s.length()];
        for(int i = 0; i < s.length(); i ++)
            encryptedChar[i] = (char)(modEncrypt(s.charAt(i)));
        return encryptedChar;
    }

    public String decryptAffine(char[] s){
        String decryptedText = "";
        for(int i = 0; i < s.length; i++){
            decryptedText += (char)(modDecrypt(s[i]));
        }
        return decryptedText;
    }

    public char[] encryptHill(String s){
        Matrix matrix = new Matrix(ArrayUtil.toInt(s));
        return ArrayUtil.toChar((matrix.multiply(matrixKey.getMatrix())));
       // return ArrayUtil.toChar(matrix.multiply(ArrayUtil.toInt(s), Matrix.KeyType.DEFAULT));
    }

    public String decryptHill (String s){
        Matrix matrix = new Matrix(ArrayUtil.toInt(s));
        return new String(ArrayUtil.toChar(matrix.multiply(matrixKey.getInverse())));
        //int[] deCrypted = matrix.toInt(s);
        //return new String(matrix.toChar(matrix.multiply(deCrypted, KeyType.INVERSE)));
       // return new String(ArrayUtil.toChar(matrix.multiply(ArrayUtil.toInt(s), Matrix.KeyType.INVERSE)));
    }

    private int modEncrypt(int k){
        if(k == 32)
            return 33;
        return 97 + (key1 * (k-97) + key2) % 26;
    }

    private int modDecrypt(int k){
        int x = inverse * ((k-97)-key2) % 26;
        if(k == 33)
            return 32;
        return x < 0 ? ((x+26) % 26)+97 : x+97 ;
    }

    public static int gcd(int a,int b){
        if (b==0) return a;
        return gcd(b,a % b);
    }

    public int inverse(int a, int c) {
        for(int i = 0; i < c; i++){
            if((a*i%c == 1))
                return i;
        }
        return -1;
    }
}
