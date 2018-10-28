package no.pederyo;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Input input = (Input) o;
        return prevOutputIndex == input.prevOutputIndex &&
                Objects.equals(prevTxHash, input.prevTxHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prevTxHash, prevOutputIndex);
    }
}
