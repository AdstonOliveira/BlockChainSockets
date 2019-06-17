package Blockchain;

import java.util.ArrayList;
/**
 * @author Suporte-04
 */
public final class Blockchain {
    private ArrayList<Block> blockchain = new ArrayList();
    private final Pool pool;
    
    public Blockchain(){
        this.pool = new Pool(this);
    }
    
    
    
    
    
    
    
    
}
