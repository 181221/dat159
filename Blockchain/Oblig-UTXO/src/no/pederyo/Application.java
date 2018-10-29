package no.pederyo;

public class Application {
    
    private static UTXO utxo = new UTXO();

	public static void main(String[] args)  {
        Wallet miner = new Wallet(HashUtil.base64Encode(HashUtil.sha256Hash("first wallet, (miner) ")), utxo);
        Wallet peders_wallet = new Wallet(HashUtil.base64Encode(HashUtil.sha256Hash("Peders wallet")), utxo);

        CoinbaseTx genesis = new CoinbaseTx("Genesis", 100, miner.getAddress());

        utxo.addOutputFrom(genesis);
        System.out.println("Block1=" + genesis.toString());


        CoinbaseTx coinbaseTx = new CoinbaseTx("regular transaction", 100, miner.getAddress());


        try {
            Transaction regularTx = miner.createTransaction(20, peders_wallet.getAddress());
            if(!(regularTx.isValid()) &&
                    utxo.inputsBelongToSender(regularTx) &&
                    utxo.inputsAndOutputsSumIsEqual(regularTx) &&
                    utxo.isUnspentOutput(regularTx)
            ) throw new Exception("Not a valid transaction");

            utxo.addAndRemoveOutputsFrom(regularTx);
            System.out.println("\nBlock2=" + coinbaseTx.toString() + "\n" + regularTx.printTransaction());
        } catch (Exception e) {
            e.printStackTrace();
        }

        utxo.addOutputFrom(coinbaseTx);


        coinbaseTx = new CoinbaseTx("new transaction", 100, miner.getAddress());

        try {
            Transaction regularTx = miner.createTransaction(20, peders_wallet.getAddress());
            if (!regularTx.isValid() &&
                    utxo.inputsBelongToSender(regularTx) &&
                    utxo.inputsAndOutputsSumIsEqual(regularTx) &&
                    utxo.isUnspentOutput(regularTx)
            ) throw new Exception("Not a valid transaction");

            utxo.addAndRemoveOutputsFrom(regularTx);
            System.out.println("\nBlock3= " + coinbaseTx.toString() + "\n" + regularTx.printTransaction());
        } catch (Exception e) {
            e.printStackTrace();
        }


        utxo.addOutputFrom(coinbaseTx);

        System.out.println("UTXO:");
        utxo.printUTXO();
        System.out.println("\nMiners Wallet: \n" + miner.toString());
        System.out.println("\nMyWallet: " + "\n" + peders_wallet.toString());
    }

}
