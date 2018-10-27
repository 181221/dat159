package no.pederyo;

import java.util.HashMap;
import java.util.Map;

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
		tx.getOutputs().forEach( index -> map.put(new Input(tx.getTxHash(), tx.getOutputs().indexOf(index)), index));
		tx.getInputs().forEach( index -> map.remove(index));
    }

	public Map<Input, Output> getMap() {
		return map;
	}

	public void setMap(Map<Input, Output> map) {
		this.map = map;
	}
}
