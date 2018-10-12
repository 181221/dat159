package pederyo;

import java.util.ArrayList;
import java.util.List;


public class Blockchain {

	private int miningDifficulty; // The number of leading zeros in block hashes
	private String miningTarget;  // The reg-exp representing the mining target
	private List<Block> listOfBlocks; // The list of blocks
	
	public Blockchain(int miningDifficulty) {
		listOfBlocks = new ArrayList<Block>(100);
		this.miningDifficulty = miningDifficulty;
		miningTarget = "^0{"+this.miningDifficulty+"}(\\d|\\D){59}$";
	}
	
	public String getHashLastBlock(){
		if(listOfBlocks.isEmpty()){
			return "0000000000000000000000000000000000000000000000000000000000000000";
		}
		return listOfBlocks.get(listOfBlocks.size()-1).getHash();
	}

	public boolean validateAndAppendNewBlock(Block b) {
		// TODO
		// Validate and append to chain if valid.
		// Return whether everything went OK and the block was appended.
		boolean isValid = b.isValidAsNextBlock(getHashLastBlock(), miningTarget);
		if(isValid) listOfBlocks.add(b);
		return isValid;

	}
	
	public boolean isValidChain() {
		// TODO
		// Validate the entire chain.
		return true;
	}





	public int getMiningDifficulty() {
		return miningDifficulty;
	}

	public void setMiningDifficulty(int miningDifficulty) {
		this.miningDifficulty = miningDifficulty;
	}

	public List<Block> getListOfBlocks() {
		return listOfBlocks;
	}

	public void setListOfBlocks(List<Block> listOfBlocks) {
		this.listOfBlocks = listOfBlocks;
	}

	public String getMiningTarget() {
		return miningTarget;
	}

	public void setMiningTarget(String miningTarget) {
		this.miningTarget = miningTarget;
	}
	
}
