package no.pederyo;

public class Input {

    //Simplified compared to Bitcoin
    //The signature is moved to Transaction, see comment there.
	private String prevTxHash;
	private int prevOutputIndex;
	
	public Input(String prevTxHash, int prevOutputIndex) {
	    this.prevTxHash = prevTxHash;
	    this.prevOutputIndex = prevOutputIndex;
	}

	public String getPrevTxHash() {
		return prevTxHash;
	}

	public void setPrevTxHash(String prevTxHash) {
		this.prevTxHash = prevTxHash;
	}

	public int getPrevOutputIndex() {
		return prevOutputIndex;
	}

	public void setPrevOutputIndex(int prevOutputIndex) {
		this.prevOutputIndex = prevOutputIndex;
	}

	@Override
	public String toString() {
		return "Input{" +
				"prevTxHash='" + prevTxHash + '\'' +
				", prevOutputIndex=" + prevOutputIndex +
				'}';
	}
}
