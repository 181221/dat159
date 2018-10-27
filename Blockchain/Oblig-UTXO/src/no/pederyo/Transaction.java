package no.pederyo;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Transaction {

    //Simplified compared to Bitcoin
	private List<Input> inputs = new ArrayList<>();
	private List<Output> outputs = new ArrayList<>();
	
	//If we make the assumption that all the inputs belong to the
	//same key, we can have one signature for the entire transaction, 
	//and not one for each input. This simplifies things a lot 
	//(more than you think)!
	private PublicKey senderPublicKey;

	private byte[] signature;
	
	private String txHash;
	
	public Transaction(PublicKey senderPublicKey) {
		this.senderPublicKey = senderPublicKey;
	}
	
	public void addInput(Input input) {
		inputs.add(input);
	}
	
	public void addOutput(Output output) {
		outputs.add(output);
	}

	public void signTxUsing(PrivateKey privateKey) {
		signature = DSAUtil.signWithDSA(privateKey, inputs() + outputs());
	}

	public void calculateTxHash() {
		txHash = HashUtil.base64Encode(HashUtil.sha256Hash(this.toString().getBytes()));
	}


	// 2. The second "block" contains two transactions, the mandatory coinbase
	//    transaction and a regular transaction. The regular transaction shall
	//    send ~20% of the money from the "miner"'s address to the other address.

	//    Validate the regular transaction created by the "miner"'s wallet:
	//      - All the content must be valid (not null++)!!!
	//      - All the inputs are unspent and belongs to the sender
	//      - There are no repeating inputs!!!
	//      - All the outputs must have a value > 0
	//      - The sum of inputs equals the sum of outputs
	//      - The transaction is correctly signed by the sender
	//      - The transaction hash is correct

	public boolean isValid() {
	    //TODO Complete validation of the transaction. Called by the Application.
	    return true;
	}


	public List<Input> getInputs() {
		return inputs;
	}

	public void setInputs(List<Input> inputs) {
		this.inputs = inputs;
	}

	public List<Output> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<Output> outputs) {
		this.outputs = outputs;
	}

	public PublicKey getSenderPublicKey() {
		return senderPublicKey;
	}

	public void setSenderPublicKey(PublicKey senderPublicKey) {
		this.senderPublicKey = senderPublicKey;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public String getTxHash() {
		return txHash;
	}

	public void setTxHash(String txHash) {
		this.txHash = txHash;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"inputs=" + inputs +
				", outputs=" + outputs +
				", senderPublicKey=" + senderPublicKey +
				", signature=" + Arrays.toString(signature) +
				", txHash='" + txHash + '\'' +
				'}';
	}

	private String inputs(){
		return inputs.stream().map(Input::toString).collect(Collectors.joining("\n\t\t"));
	}
	private String outputs(){
		return outputs.stream().map(Output::toString).collect(Collectors.joining("\n\t\t"));
	}
}
