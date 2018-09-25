package no.pederyo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptoTest {

    private Utility util;

    @BeforeEach
    void setUp() {
        util = new Utility();
    }
    @Test
    public void affineTestSequence(){
        String test = "hei dette er en test";
        char[] encrypted = util.encryptAffine(test);
        String decrypted = util.decryptAffine(encrypted);

        assertEquals(test, decrypted );
    }

    @Test
    public void affineTestAllLetters(){
        for(int i = 97; i < 123; i++){
            char letter = (char)i;
            char[] en = util.encryptAffine(String.valueOf(letter));
            String de = util.decryptAffine(en);
            assertEquals(String.valueOf(letter),de);
        }
    }
    @Test
    public void hillCipherTest(){
        char[] test = util.encryptHill("cipher");
        System.out.println(util.decryptHill(new String(test)));
    }
}