package pederyo;

public class Block {

	private long nonce;	 // The nonce for this block.
	private String data; // The data for this block.
	private String prev; // The hash of the previous block in the blockchain.
	private String hash; // The calculated hash for this block.

	public Block(String hashLastBlock, String data) {
		// TODO
		// Initializing *ALL* the instance variables to a valid state.
		// The nonce can be 0. No mining required at this stage.
		// Use the helper method calculateHash() to calculate the hash.
		prev = hashLastBlock;
		nonce = 0;
		this.data = data;
		hash = calculateHash();

	}

	public void mine(String miningTarget) {
		// TODO
		// Given the miningTarget, mine until the calculated hash matches the target.
		// The target is a regular expression, for example "^0{5}.*" which implies
		// that the hash must start with 5 zeros.
		while(!hash.matches(miningTarget)){
			nonce += 1;
			hash = calculateHash();
		}
	}
	
	public boolean isValidAsNextBlock(String hashLastBlock, String miningTarget) {
		// TODO
		// A complete validation of the block, including prev matching
		// the hash of the last block in the blockchain, and that the block is
		// mined according to the mining target.
		;
		return hashLastBlock.matches(miningTarget) && prev.equals(hashLastBlock); // mined according to the mining target.

	}
	
	private String calculateHash() {
		// TODO
		// Calculating the hash for this block based on the other instance variables.
		// The hash is returned as a HEX-String.
		return Utility.toHex(Utility.hash256(prev + nonce + data));
	}

	public long getNonce() {
		return nonce;
	}

	public void setNonce(long nonce) {
		this.nonce = nonce;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public String toString() {
		return "Block{" +
				"nonce=" + nonce +
				", data='" + data + '\'' +
				", prev='" + prev + '\'' +
				", hash='" + hash + '\'' +
				'}';
	}
}