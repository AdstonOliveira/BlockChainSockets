package Server;

import java.util.ArrayList;
/**
 * @author Suporte-04
 */
public class Pool implements InterfacePool{
    private ArrayList<Block> pool;
    private Blockchain blockchain;
    
    public Pool(Blockchain blockchain){
       this.pool = new ArrayList();
       this.new_Block();
    }
    
    /**
     * Adiciona o bloco ao pool, para ser utilizado
     */
    @Override
    public final void new_Block() {
        Block block = new Block(this.blockchain);
        this.pool.add(block);
    }

    @Override
    public boolean add_transaction(Object transaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void transmit_to_nodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove_of_pool() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add_on_blockckain() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



















}
