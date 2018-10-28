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
		if(inputs.contains(input))
            try {
                throw new Exception("Inputs exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
        else inputs.add(input);
	}
	
	public void addOutput(Output output) throws Exception {
	    if(outputs.contains(output))
            throw new Exception("Inputs exists");
	    else
	        outputs.add(output);
    }

	public void signTxUsing(PrivateKey privateKey) {
		signature = DSAUtil.signWithDSA(privateKey, inputs() + outputs());
	}

	public void calculateTxHash() {
		txHash = HashUtil.base64Encode(HashUtil.sha256Hash(this.toString()));
	}

	public boolean isValid() {
        return contentIsNotNull() &&
                noRepeatingInputs() &&
                outputsLargerThanZero() &&
                isSignedBySender() &&
                transactionHashIsCorrect();
	}

	private boolean contentIsNotNull() {
	    return senderPublicKey != null &&
                signature != null &&
                signature.length != 0 &&
                txHash != null &&
                !inputs.isEmpty() &&
                !outputs.isEmpty();
    }


    private boolean noRepeatingInputs(){
	    return inputs.stream().noneMatch(input -> inputs.indexOf(input) == -1);
    }

    private boolean outputsLargerThanZero(){
	    return outputs.stream().allMatch(output -> output.getValue() > 0 && output.getValue() < 21000000);
    }

    private boolean isSignedBySender(){
	    return DSAUtil.verifyWithDSA(senderPublicKey,inputs() + outputs(), signature);
    }

    private boolean transactionHashIsCorrect(){
	    return txHash.equals(HashUtil.base64Encode(HashUtil.sha256Hash(this.toString())));
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
				"inputs=" + inputs() +
				", outputs=" + outputs() +
				", senderPublicKey=" + senderPublicKey +
				", signature=" + Arrays.toString(signature) +
				'}';
	}
    public String printTransaction() {
        return "Transaction{" +
                "inputs=" + inputs() +
                ", outputs=" + outputs() +
                '}';
    }

	private String inputs(){
		return inputs.stream().map(Input::toString).collect(Collectors.joining("\n\t\t"));
	}
	private String outputs(){
		return outputs.stream().map(Output::toString).collect(Collectors.joining("\n\t\t"));
	}
}
