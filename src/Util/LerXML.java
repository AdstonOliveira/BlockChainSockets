/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

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
    public static void main(String[] args) throws IOException {
        File f = new File(".\\XMLBoletim.xml");
        lerArquivo(f);
        
    }
    public static void lerArquivo(File file) throws IOException{
    //Inicialização
        try {
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
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
                                        System.out.println("dados_eleicao: "+ c.getTextContent() );
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