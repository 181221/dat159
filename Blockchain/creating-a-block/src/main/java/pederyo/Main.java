package pederyo;

public class Main {
	
	public static void main(String[] args) {
		Blockchain blockchain = new Blockchain(5);

		Miner miner = new Miner(blockchain);

		System.out.println(miner.createAndMineNewBlock("First block of the chain"));
		System.out.println(miner.createAndMineNewBlock("Second block of the chain"));
		System.out.println(miner.createAndMineNewBlock("Third block of the chain"));
		System.out.println(miner.createAndMineNewBlock("Fourth block of the chain"));
		System.out.println(miner.createAndMineNewBlock("Fifth block of the chain"));

		System.out.println(blockchain.isValidChain());
	}
	
}
