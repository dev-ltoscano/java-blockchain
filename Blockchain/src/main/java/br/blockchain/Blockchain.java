package br.blockchain;

import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ltosc
 */
public class Blockchain 
{
    private final int difficulty = 3;
    
    private int previousBlockIndex;
    private final List<Block> blockchain;
    
    public Blockchain()
    {
        this.previousBlockIndex = 0;
        this.blockchain = new ArrayList<>();
        this.blockchain.add(new Block("0", "The genesis"));
    }
    
    public void addBlock(String blockData)
    {
        Block block = new Block(blockchain.get(previousBlockIndex++).getHash(), blockData);
        block.mineBlock(difficulty);
        
        this.blockchain.add(block);
    }
    
    public boolean isValidBlockchain() 
    {
        Block currentBlock; 
        Block previousBlock;
	
	for(int i = 1; i < blockchain.size(); i++)
        {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) 
            {
                return false;
            }
            
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) 
            {
                return false;
            }
	}
        
	return true;
    }
    
    @Override
    public String toString()
    {
        return new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
    }
}
