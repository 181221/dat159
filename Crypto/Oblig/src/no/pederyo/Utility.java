/**
 * 
 */
package no.pederyo;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author tosindo
 *
 */
public class Utility {


    public static byte[] toHexByteArray(String self) {
        byte[] bytes = new byte[self.length() / 2];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = ((byte) Integer.parseInt(self.substring(i * 2, i * 2 + 2), 16));
        }
        return bytes;
    }

    static void printHexBytes(byte[] self, String label) {
        System.out.printf("%s: ", label);
        for (byte b : self) {
            int bb = (b >= 0) ? ((int) b) : b + 256;
            String ts = Integer.toString(bb, 16);
            if (ts.length() < 2) {
                ts = "0" + ts;
            }
            System.out.print(ts);
        }
        System.out.println();
    }
    public static String bytesToString(byte[] self) {
        String str = "";
        for (byte b : self) {
            int bb = (b >= 0) ? ((int) b) : b + 256;
            String ts = Integer.toString(bb, 16);
            if (ts.length() < 2) {
                ts = "0" + ts;
            }
            str += ts;
        }
        return str;
    }
    public static String asciiToHex(String asciiStr) {
        char[] chars = asciiStr.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char ch : chars) {
            hex.append(Integer.toHexString((int) ch));
        }

        return hex.toString();
    }
    public static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }



}
