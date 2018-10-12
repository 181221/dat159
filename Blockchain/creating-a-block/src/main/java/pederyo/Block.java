package pederyo;

public class Block {

	private long nonce;	 // The nonce for this block.
	private String data; // The data for this block.
	private String prev; // The hash of the previous block in the blockchain.
	private String hash; // The calculated hash for this block.

	public Block(String hashLastBlock, String data) {
		prev = hashLastBlock;
		nonce = 0;
		this.data = data;
		hash = calculateHash();

	}

	public void mine(String miningTarget) {
		while(!hash.matches(miningTarget)){
			nonce += 1;
			hash = calculateHash();
		}
	}
	
	public boolean isValidAsNextBlock(String hashLastBlock, String miningTarget) {
		return hashLastBlock.matches(miningTarget) && prev.equals(hashLastBlock);

	}
	
	private String calculateHash() {
		return Utility.toHex(Utility.hash256(prev + nonce + data));
	}

	public String getPrev() {
		return prev;
	}

	public String getHash() {
		return hash;
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
