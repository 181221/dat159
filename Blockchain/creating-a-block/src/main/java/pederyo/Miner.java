package pederyo;

public class Miner {
	
	private Blockchain chain;
	
	public Miner(Blockchain chain) {
		this.chain = chain;
	}

	public Block createAndMineNewBlock(String data) {
		Block b = new Block(chain.getHashLastBlock(), data);
		b.mine(chain.getMiningTarget());
		if (chain.validateAndAppendNewBlock(b)) {
			System.out.print(" Valid ");
		} else {
			System.out.print(" Not Valid ");
		}
		return b;
	}		
	
		
}
