package no.pederyo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static no.pederyo.Utility.asciiToHex;

class DESTEST {
    DES des;

    @BeforeEach
    void setup() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        des = new DES();
    }

    @Test
    void encryptDes() throws BadPaddingException, IllegalBlockSizeException {
        byte[] tester = des.decryptDes(des.encryptDes(asciiToHex("heisann")));
        System.out.println(Utility.hexToAscii(Utility.bytesToString(tester)));
    }

    @Test
    void decryptDES() {
    }

}