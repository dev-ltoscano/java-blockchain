package br.blockchain;

import br.blockchain.security.SHAAlgorithm;
import java.util.Date;

/**
 *
 * @author ltosc
 */
public class Block 
{
    private String data;
    
    private String previousHash;
    private long timestamp;
    private int nonce;
    private String hash;
    
    public Block(String previousBlockHash, String blockData)
    {
        this.data = blockData;
        
        this.previousHash = previousBlockHash;
        this.timestamp = new Date().getTime();
        
        
        this.hash = calculateHash();
    }
    
    public String calculateHash()
    {
        return SHAAlgorithm.apply(data
                        .concat(previousHash)
                        .concat(Long.toString(timestamp))
                        .concat(Integer.toString(nonce)),
                SHAAlgorithm.SHAType.SHA256);
    }
    
    public void mineBlock(int difficulty) 
    {
        String target = new String(new char[difficulty]).replace('\0', '0');
        
        while (!hash.substring(0, difficulty).equals(target)) 
        {
            nonce++;
            hash = calculateHash();
        }
        
        System.out.println("Mined block: " + hash);
    }
    
    /**
     * @return the previousHash
     */
    public String getPreviousHash() {
        return previousHash;
    }

    /**
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }
}
