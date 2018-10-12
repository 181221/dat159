package pederyo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.*;

class BlockchainTest {

    Blockchain blockchain;
    Miner miner;
    @BeforeEach
    void setUp() {
        blockchain = new Blockchain(3);
        miner = new Miner(blockchain);

    }

    @Test
    void hash(){
        miner.createAndMineNewBlock("FirstBlock");
        Block b = new Block("00000000000000000000000000000000", "Test Block");
        Utility.toHex(Utility.hash256("00000000000000000000000000000000" + "0" + "Test"));

    }
    @Test
    void regexValidation(){
        int miningDifficulty = 5;
        String mingTargetFormatted = "^0{"+miningDifficulty+"}(\\d|\\D){27}$";

        String match = "00000000000000000000asd212312312";
        String match1 = "000000000000000&0}00as{212312312";

        String match2 = "00000000000000000000000000000000";


        String notmatch = "10000000000000000000asd212312312";
        String notmatch1 = "00030000000000000000asd212312312";
        String notmatch2 = "00000000000000000000asd2123123121";



        assertTrue(match.matches(mingTargetFormatted));
        assertTrue(match1.matches(mingTargetFormatted));
        assertTrue(match2.matches(mingTargetFormatted));

        assertFalse(notmatch.matches(mingTargetFormatted));
        assertFalse(notmatch1.matches(mingTargetFormatted));
        assertFalse(notmatch2.matches(mingTargetFormatted));
    }
}