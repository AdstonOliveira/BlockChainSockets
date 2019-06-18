/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Client.options.BoletimUrna;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private final BoletimUrna boletim;
    
    public LerXML(File file) throws IOException{
        this.boletim = this.lerArquivo(file);
    }
    
    public static void main(String[] args) throws IOException {
        File f = new File(".\\XMLBoletim.xml");
        LerXML xml = new LerXML(f);
        
    }
    public BoletimUrna lerArquivo(File file) throws IOException{
    //Inicialização
    BoletimUrna boletim = new BoletimUrna();
        try {
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            //Atributo resumo da raiz
            String num_resumo = doc.getDocumentElement().getAttributeNode("resumo").getNodeValue();
            
            boletim.getDados().setNum_resumo(num_resumo);
            
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
                                        dados_eleicao(c, boletim);
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
        
        return boletim;
    }
    
    public void dados_eleicao(Element c, BoletimUrna boletim){
        NodeList elementos = c.getChildNodes();

            for(int i = 0; i < elementos.getLength(); i++){
                Node filho = elementos.item(i);
                
                    if(filho.getNodeType() == Node.ELEMENT_NODE){
                        Element node = (Element) filho;
                        System.out.println("Tags " + node.getNodeName());
                        
                        switch (node.getTagName()){
                            case "tipo_eleicao":
                               this.tipo_eleicao(node, boletim);   
                            break;
                            case "dados_secao":
                                this.dados_secao(node, boletim);
                            break;
                            case "urna":
                                this.urna(node, boletim);
                            break;
                            
                        }
                        
                    }
            }
    }
    
    public void urna(Element c, BoletimUrna boletim){
        NodeList elementos = c.getChildNodes();
        boletim.getDados().getDados_urna().setId_urna((c.getAttribute("id_urna")));
        
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        Date data_abertura = new Date();
        Date data_fechamento = new Date();
        
        
            for (int i = 0; i < elementos.getLength(); i++) {
                Node filho = elementos.item(i);
                
                if(filho.getNodeType() == Node.ELEMENT_NODE){
                    Element atributo = (Element) filho;
                    
                        switch (atributo.getTagName()){
                            case "dt_abertura":
                            {
                                try {
                                    data_abertura = data.parse(atributo.getTextContent());
                                    boletim.getDados().getDados_urna().setDt_abertura(data_abertura);
                                    System.out.println("Teste da data_abert no objeto: " 
                                            + boletim.getDados().getDados_urna().getDt_abertura());
                                    } catch (ParseException ex) {
                                        Logger.getLogger(LerXML.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                            }
                            break;
                            
                            case "hr_abertura":
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(data_abertura);
                                //Split da hora cadastrada
                                String[] hora = atributo.getTextContent().split(":");
                                    calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hora[0]) );
                                    calendar.set(Calendar.MINUTE, Integer.valueOf(hora[1]) );
                                    calendar.set(Calendar.SECOND, Integer.valueOf(hora[2]) );

                                boletim.getDados().getDados_urna().setDt_abertura(calendar.getTime());
                                
                                System.out.println("Teste da data_abert com hora: " + 
                                        boletim.getDados().getDados_urna().getDt_abertura());
                            break;
                            
                            case "dt_fechamento":
                            {
                                try {
                                    data_fechamento = data.parse(atributo.getTextContent());
                                    boletim.getDados().getDados_urna().setDt_fechamento(data_fechamento);
                            
                                    System.out.println("Teste da data_fechamento: " 
                                            + boletim.getDados().getDados_urna().getDt_fechamento());
                                } catch (ParseException ex) {
                                     Logger.getLogger(LerXML.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                            
                            case "hr_encerramento":
                                Calendar enc_calendar = Calendar.getInstance();
                                enc_calendar.setTime(data_fechamento);
                                //Split da hora cadastrada
                                String[] hora_e = atributo.getTextContent().split(":");
                                    enc_calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hora_e[0]) );
                                    enc_calendar.set(Calendar.MINUTE, Integer.valueOf(hora_e[1]) );
                                    enc_calendar.set(Calendar.SECOND, Integer.valueOf(hora_e[2]) );

                                boletim.getDados().getDados_urna().setDt_fechamento(enc_calendar.getTime());
                                System.out.println("Teste da data_enc com hora: " + 
                                        boletim.getDados().getDados_urna().getDt_fechamento());
                            break;
                            case "comparecimento":
                                int comp = Integer.valueOf(atributo.getTextContent());
                                boletim.getDados().getDados_secao().setLocal(comp);
                            break;
                            case "faltosos":
                                int faltosos = Integer.valueOf(atributo.getTextContent());
                                boletim.getDados().getDados_secao().setLocal(faltosos);
                            break;
                            case "habilitados":
                                int habil = Integer.valueOf(atributo.getTextContent());
                                boletim.getDados().getDados_secao().setLocal(habil);
                            break;
                            default:
                                System.out.println("Tag invalida, não adicionada ao tipo: " + atributo.getTagName());
                            break;
                        }
                    }
            }    
    }
    
    
    public void dados_secao(Element c, BoletimUrna boletim){
        NodeList elementos = c.getChildNodes();
        boletim.getDados().getDados_secao().setLocal(Integer.valueOf(c.getAttribute("id_municipio")));
        
            for (int i = 0; i < elementos.getLength(); i++) {
                Node filho = elementos.item(i);
                
                if(filho.getNodeType() == Node.ELEMENT_NODE){
                    Element atributo = (Element) filho;
                    
                        switch (atributo.getTagName()){
                            case "zona":
                                int zona = Integer.valueOf( atributo.getTextContent() );
                                boletim.getDados().getDados_secao().setZona( zona );
                            break;
                            case "local":
                                int local = Integer.valueOf(atributo.getTextContent());
                                boletim.getDados().getDados_secao().setLocal(local);
                            break;
                            case "secao":
                                int secao = Integer.valueOf(atributo.getTextContent());
                                boletim.getDados().getDados_secao().setLocal(secao);
                            break;
                            case "aptos":
                                int aptos = Integer.valueOf(atributo.getTextContent());
                                boletim.getDados().getDados_secao().setLocal(aptos);
                            break;
                            case "comparecimento":
                                int comp = Integer.valueOf(atributo.getTextContent());
                                boletim.getDados().getDados_secao().setLocal(comp);
                            break;
                            case "faltosos":
                                int faltosos = Integer.valueOf(atributo.getTextContent());
                                boletim.getDados().getDados_secao().setLocal(faltosos);
                            break;
                            case "habilitados":
                                int habil = Integer.valueOf(atributo.getTextContent());
                                boletim.getDados().getDados_secao().setLocal(habil);
                            break;
                            default:
                                System.out.println("Tag invalida, não adicionada ao tipo: " + atributo.getTagName());
                            break;
                        }
                    }
            }
    }
    
    
    
    
    public void tipo_eleicao(Element c, BoletimUrna boletim){
        NodeList elementos = c.getChildNodes();
        
        boletim.getDados().getTipo_eleicao().setId(Integer.valueOf(c.getAttribute("id")));
        
            for (int i = 0; i < elementos.getLength(); i++) {
                Node filho = elementos.item(i);
                
                if(filho.getNodeType() == Node.ELEMENT_NODE){
                    Element atributo = (Element) filho;
                    
                        switch (atributo.getTagName()){
                            case "descricao":
                                boletim.getDados().getTipo_eleicao().setDescricao( atributo.getTextContent() );
                            break;
                            case "ano":
                                int ano = Integer.valueOf(atributo.getTextContent());
                                boletim.getDados().getTipo_eleicao().setAno(ano);
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