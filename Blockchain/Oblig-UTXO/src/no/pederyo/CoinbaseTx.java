package no.pederyo;

public class CoinbaseTx {
	
    //Simplified compared to Bitcoin (nothing significant missing)
	private String coinbase; // "The Times 03/Jan/2009 Chancellor 
	                         //  on brink of second bailout for banks"
	private Output output;
	private String txHash;

	public CoinbaseTx(String coinbase, int value, String address) {
	    this.coinbase = coinbase;
	    output = new Output(value, address);
	    txHash = HashUtil.base64Encode(HashUtil.sha256Hash(this.toString()));
	}

	@Override
	public String toString() {
		return "CoinbaseTx{" +
				"coinbase='" + coinbase + '\'' +
				", output=" + output +
				", txHash='" + txHash + '\'' +
				'}';
	}

	public Output getOutput() {
		return output;
	}


	public String getTxHash() {
		return txHash;
	}
}
