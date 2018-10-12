package no.pederyo;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DES {
    private SecretKeySpec key;
    Cipher encCipher, decCipher;
    String strKey = "0e329232ea6d0d73";
    byte[] keyBytes;

    public DES() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        keyBytes = Utility.toHexByteArray(strKey);
        key = new SecretKeySpec(keyBytes,"DES");
        encCipher = Cipher.getInstance("DES");
        encCipher.init(Cipher.ENCRYPT_MODE, key);
        decCipher = Cipher.getInstance("DES");
        decCipher.init(Cipher.DECRYPT_MODE, key);

    }

    public byte[] encryptDes(String str) throws BadPaddingException, IllegalBlockSizeException {
        byte[] encBytes = encCipher.doFinal(str.getBytes());
        return Base64.getEncoder().encode(encBytes);
    }
    public byte[] decryptDes(byte[] encodedBytes) throws BadPaddingException, IllegalBlockSizeException {
        return decCipher.doFinal(Base64.getDecoder().decode(encodedBytes));
    }
}
