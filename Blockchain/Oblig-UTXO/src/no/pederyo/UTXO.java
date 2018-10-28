package no.pederyo;

import java.util.*;

public class UTXO {
    
    //Why is this a Map and not a Set?
    //  The values in this map are the UTXOs (unspent Outputs)
    //  When removing UTXOs, we need to identify which to remove.
    //  Since the Inputs are references to UTXOs, we can use those
    //  as keys.
	private Map<Input, Output> map = new HashMap<>();
	
	public void printUTXO() {
		map.forEach((input, output) -> System.out.println(input.toString() + " " + output.toString()));
	}
	
	public void addOutputFrom(CoinbaseTx ctx) {
	    map.put(new Input(ctx.getTxHash(),0), ctx.getOutput());
	}

    public void addAndRemoveOutputsFrom(Transaction tx) {
		tx.getInputs().forEach(input ->
				map.entrySet().removeIf(outputEntry -> outputEntry.getValue().getAddress().equals(input.getPrevTxHash())));
		tx.getOutputs().forEach(index -> map.put(new Input(tx.getTxHash(), tx.getOutputs().indexOf(index)), index));
	}

    public boolean isUnspentOutput(Transaction tx){
		for(Output o : tx.getOutputs()){
			for(Map.Entry<Input, Output> i : map.entrySet()){
				if(i.getKey().getPrevTxHash().equals(o.getAddress())){
					return false;
				}
			}
		}
		return true;
	}

	public boolean inputsAndOutputsSumIsEqual(Transaction tx){
		String hash = tx.getInputs().get(0).getPrevTxHash();
		long sumInputs = 0;
		for(Map.Entry<Input, Output> k : map.entrySet()) {
			if(k.getValue().getAddress().equals(hash)){
				sumInputs += k.getValue().getValue();
			}
		}
		long sumValues = tx.getOutputs().stream().mapToLong(Output::getValue).sum();
		return sumInputs == sumValues;
	}

	public boolean inputsBelongToSender(Transaction tx) {
		return tx.getInputs().stream().anyMatch(input -> input.getPrevTxHash().equals(HashUtil.addressFromPublicKey(tx.getSenderPublicKey())));
	}

	public Map<Input, Output> getMap() {
		return map;
	}

	public void setMap(Map<Input, Output> map) {
		this.map = map;
	}
}
