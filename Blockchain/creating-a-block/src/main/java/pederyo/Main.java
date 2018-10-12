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


		// TODO
		
		/*
		 * Create a blockchain and a miner, add some blocks and validate the chain.
		 * You should also System.print out the blocks as soon as the are appended,
		 * and print out the final validation result. See output.txt for example
		 * output for a solution to this assignment.
		 */
	}
	
}
