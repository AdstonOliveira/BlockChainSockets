/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Client.options.BoletimUrna;
import java.io.File;
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
    private final BoletimUrna boletim = new BoletimUrna();
    
    public static void main(String[] args) throws IOException {
        File f = new File(".\\XMLBoletim.xml");
        LerXML xml = new LerXML();
        xml.lerArquivo(f);
        
    }
    public void lerArquivo(File file) throws IOException{
    //Inicialização
        try {
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            //Atributo resumo da raiz
            System.out.println("Root element id:" + doc.getDocumentElement().getAttributeNode("resumo").getNodeValue());
            //long num_resumo = Integer.valueOf(doc.getDocumentElement().getAttributeNode("resumo").getNodeValue());
            this.boletim.getDados().setNum_resumo(1);
            
            //Importação
            NodeList lista = doc.getElementsByTagName("boletim");
            
            for(int j = 0; j < lista.getLength(); j++){
                
                Node no = lista.item(j);
                
                
                if(no.getNodeType() == Node.ELEMENT_NODE){
                    Element conv = (Element) no;
                    NodeList elementosConv = conv.getChildNodes();

                    for(int i = 0; i < elementosConv.getLength(); i++){
                        Node filho = elementosConv.item(i);
                        
                            if(filho.getNodeType() == Node.ELEMENT_NODE){
                                Element c = (Element) filho;
                        //Leitura do arquivo ++ ainda nao ta funcionando corretamente
                                switch( c.getTagName() ){
                                    case "dados_eleicao":
                                        dados_eleicao(c);
                                        //System.out.println("dados_eleicao: "+ c.getTextContent() );
                                    break;
                                    
                                }
                            }
                        }    
                }
            }
        } catch (SAXException | ParserConfigurationException ex) {
            Logger.getLogger(LerXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dados_eleicao(Element c){
        NodeList elementos = c.getChildNodes();

            for(int i = 0; i < elementos.getLength(); i++){
                Node filho = elementos.item(i);
                
                    if(filho.getNodeType() == Node.ELEMENT_NODE){
                        Element node = (Element) filho;
                        System.out.println("Tags " + node.getNodeName());
                        
                        switch (node.getTagName()){
                            case "tipo_eleicao":
                               tipo_eleicao(node);   
                            break;
                            case "dados_secao":
                                
                            break;
                            
                        }
                        
                    }
            }
    }
    
    public void dados_secao(Element c){
        
    }
    
    
    
    
    public void tipo_eleicao(Element c){
        NodeList elementos = c.getChildNodes();
        
        this.boletim.getDados().getTipo_eleicao().setId(Integer.valueOf(c.getAttribute("id")));
        
            for (int i = 0; i < elementos.getLength(); i++) {
                Node filho = elementos.item(i);
                
                if(filho.getNodeType() == Node.ELEMENT_NODE){
                    Element atributo = (Element) filho;
                    
                        switch (atributo.getTagName()){
                            case "descricao":
                                this.boletim.getDados().getTipo_eleicao().setDescricao( atributo.getTextContent() );
                            break;
                            case "ano":
                                int ano = Integer.valueOf(atributo.getTextContent());
                                this.boletim.getDados().getTipo_eleicao().setAno(ano);
                            break;
                            default:
                                System.out.println("Tag invalida, não adicionada ao tipo");
                            break;
                        }
                    }
            }
            
    }
}
/*
public void XML(){

 try {
 
	File fXmlFile = new File("producao.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
 
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList nList = doc.getElementsByTagName("staff");
 
	System.out.println("----------------------------");
 
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
 
			System.out.println("Staff id : " + eElement.getAttribute("guest"));
			System.out.println("First Name : " + eElement.getElementsByTagName("fname").item(0).getTextContent());
			
 
		}
	}
    } catch (Exception e) {
    }
}
*/