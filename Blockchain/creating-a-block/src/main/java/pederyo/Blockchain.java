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
		int antall = 64- miningDifficulty;
		miningTarget = "^0{"+miningDifficulty+"}(\\d|\\D){"+antall+"}$";
	}
	
	public String getHashLastBlock(){
		if(listOfBlocks.isEmpty()){
			return new String(new char[64]).replace("\0","0");
		}
		return listOfBlocks.get(listOfBlocks.size()-1).getHash();
	}

	public boolean validateAndAppendNewBlock(Block b) {
		boolean isValid = b.isValidAsNextBlock(getHashLastBlock(), miningTarget);
		if(isValid) listOfBlocks.add(b);
		return isValid;

	}
	
	public boolean isValidChain() {
		for(int i = 1; i < listOfBlocks.size(); i++){
			if(!listOfBlocks.get(i).isValidAsNextBlock(listOfBlocks.get(i-1).getHash(), miningTarget)) return false;
		}
		return true;
	}

	public String getMiningTarget() {
		return miningTarget;
	}

	public List<Block> getListOfBlocks() {
		return listOfBlocks;
	}

	public void setListOfBlocks(List<Block> listOfBlocks) {
		this.listOfBlocks = listOfBlocks;
	}
}
