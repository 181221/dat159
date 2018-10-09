package pederyo;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


public class Blockchain {

	private int miningDifficulty; // The number of leading zeros in block hashes
	private String miningTarget;  // The reg-exp representing the mining target
	private List<Block> listOfBlocks; // The list of blocks
	
	public Blockchain(int miningDifficulty) {
		listOfBlocks = new ArrayList<Block>(100);
		this.miningDifficulty = miningDifficulty;
		miningTarget = "^0{"+this.miningDifficulty+"}(\\d|\\D){27}$";
	}
	
	public String getHashLastBlock(){
		if(listOfBlocks.isEmpty()){
			return "0";
		}
		return listOfBlocks.get(listOfBlocks.size()-1).getHash();
	}

	public boolean validateAndAppendNewBlock(Block b) {
		// TODO
		// Validate and append to chain if valid.
		// Return whether everything went OK and the block was appended.
		return true;
	}
	
	public boolean isValidChain() {
		// TODO
		// Validate the entire chain.
		return true;
	}

	// getters and setters
	
}
