/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
/**
 *
 * @author Suporte-04
 */
public interface InterfacePool {
    //Disponibilizar novo bloco
    void new_Block();
    
    //Adicionar transação a bloco existente
    boolean add_transaction(Object transaction);
    
    //Enviar Bloco para validação
    void transmit_to_nodes();
    
    //remove do pool
    boolean remove_of_pool();
    
    //Adicionar bloco validado a Blockchain
    boolean add_on_blockckain();
    
    
    
    
    
    
    
    
}
