package no.pederyo;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Wallet {

    private String id;
    private KeyPair keyPair;
    
    //A refererence to the "global" complete utxo-set
    private Map<Input, Output> utxoMap;

    public Wallet(String id, UTXO utxo) {
        this.id = id;
        utxoMap = utxo.getMap();
    }

    public String getAddress() {
        return id;
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public Transaction createTransaction(long value, String address) throws Exception {
        // 1. Collect all UTXO for this wallet and calculate balance
        long amount = getBalance();

        // 2. Check if there are sufficient funds --- Exception?
        if(value > amount) throw new Exception("Insufficient founds!");

        // 3. Choose a number of UTXO to be spent --- Strategy?
        Map<Input, Output> myUtxo = collectMyUtxo(); // collecting all my utxo and spending them all.

        // 4. Calculate change
        long change = value - amount;

        // 5. Create an "empty" transaction
        Transaction transaction = new Transaction(getPublicKey());

        // 6. Add chosen inputs
        // For each UTXO that will be consumed to make this payment, the wallet creates one input pointing to the UTXO and unlocks it with an unlocking script.
        for (Output output : myUtxo.values())
            transaction.addInput(new Input(output.getAddress(), 0));

        Output outputSpend = new Output(amount - value, getAddress());

        if(amount > value)
            transaction.addOutput(new Output(amount - value, getAddress()));
        // 7. Add 1 or 2 outputs, depending on change
        transaction.addOutput(outputSpend);
        // 8. Sign the transaction
        transaction.signTxUsing(keyPair.getPrivate());
        // 9. Calculate the hash for the transaction
        transaction.calculateTxHash();
        // 10. return
        return transaction;
        
        // PS! We have not updated the UTXO yet. That is normally done
        // when appending the block to the blockchain, and not here!
        // Do that manually from the Application-main.
    }


    public long getBalance() {
        return calculateBalance(collectMyUtxo().values());
    }

    private Map<Input, Output> collectMyUtxo() {
        return utxoMap.entrySet().stream()
                .filter(map -> map.getValue().getAddress().equals(id))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private long calculateBalance(Collection<Output> outputs) {
        return outputs.stream().map(Output::getValue).count();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public Map<Input, Output> getUtxoMap() {
        return utxoMap;
    }

    public void setUtxoMap(Map<Input, Output> utxoMap) {
        this.utxoMap = utxoMap;
    }



}
