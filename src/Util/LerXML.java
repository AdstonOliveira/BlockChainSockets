/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * @author Suporte-04
 */

public class LerXML {
    public static void main(String[] args) {
        DocumentBuilder builder = null;
    //Inicialização
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(LerXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            //Importação
            Document doc = builder.parse(".\\note.xml");
            
            NodeList lista = doc.getElementsByTagName("note");
            Node no = lista.item(0);
            
            if(no.getNodeType() == Node.ELEMENT_NODE){
                Element conv = (Element) no;
                NodeList elementosConv = conv.getChildNodes();
                
                for(int i = 0; i < elementosConv.getLength(); i++){
                    Node filho = elementosConv.item(i);
                
                    if(filho.getNodeType() == Node.ELEMENT_NODE){
                        Element c = (Element) filho;
                        //Leitura do arquivo
                            switch( c.getTagName() ){
                                case "to":
                                    System.out.println("to " + c.getTextContent());
                                    break;
                                case "from":
                                    System.out.println("from " + c.getTextContent());
                                    break;
                        
                            }
                    }
                    
                }    
                
            }
            
        } catch (SAXException ex) {
            Logger.getLogger(LerXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LerXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    }
}
