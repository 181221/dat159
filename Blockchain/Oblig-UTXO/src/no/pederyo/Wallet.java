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
        keyPair = DSAUtil.generateRandomDSAKeyPair();
    }

    public String getAddress() {
        return HashUtil.addressFromPublicKey(keyPair.getPublic());
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public Transaction createTransaction(long value, String address) throws Exception {

        Map<Input, Output> myUtxo = collectMyUtxo();

        long balance = calculateBalance(myUtxo.values());

        if(value > balance) throw new Exception("Insufficient founds!");

        long change = balance - value;

        Transaction transaction = new Transaction(getPublicKey());

        myUtxo.forEach((input, output) ->
            transaction.addInput(new Input(output.getAddress(), input.getPrevOutputIndex()))
        );

        Output outputSpend = new Output(value, address);

        transaction.addOutput(outputSpend);

        if(change > 0)
            transaction.addOutput(new Output(change, getAddress()));

        transaction.signTxUsing(keyPair.getPrivate());

        transaction.calculateTxHash();

        return transaction;
    }


    public long getBalance() {
        return calculateBalance(collectMyUtxo().values());
    }

    private Map<Input, Output> collectMyUtxo() {
        return utxoMap.entrySet().stream()
                .filter(map -> map.getValue().getAddress().equals(getAddress()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private long calculateBalance(Collection<Output> outputs) {
        return outputs.stream().mapToLong(Output::getValue).sum();
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id='" + id + '\'' +
                "address=" + getAddress()+ '\'' +
                ", Balance=" + getBalance() +
                '}';
    }
}
