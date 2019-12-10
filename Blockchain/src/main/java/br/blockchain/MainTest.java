package br.blockchain;

/**
 *
 * @author ltosc
 */
public class MainTest 
{
    public static void main(String[] args)
    {
        Blockchain blockchain = new Blockchain();
        
        for(int i = 0; i < 1; i++)
        {
            blockchain.addBlock("Hello, I am a new block");
        }
        
        System.out.println("Blockchain is valid: " + blockchain.isValidBlockchain());
        System.out.println(blockchain.toString());
    }
}
