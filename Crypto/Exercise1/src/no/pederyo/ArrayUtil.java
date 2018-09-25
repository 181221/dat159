package no.pederyo;

public class ArrayUtil {

    public static char[] toChar(int[] m) {
        char[] arr = new char[m.length];
        for(int i = 0; i < m.length; i++) {
            arr[i] = (char)(97+m[i]);
        }
        return arr;
    }

    public static int[] toInt(String s) {
        int[] arr = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            arr[i] = ((int)s.charAt(i) - 97);
        }
        return arr;
    }
}
