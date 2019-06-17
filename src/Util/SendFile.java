package Util;

import Client.Cliente;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author Suporte-04
 */
public class SendFile {
  /*  public void sendFile(String fileName) throws IOException {

        try {

            OutputStream os = cliente.getOutputStream();


            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(myFile.getName());
            dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
            dos.flush();

            System.out.println("Arquivo "+fileName+" enviado para cliente.");

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não existe!");
        } 
}*/
    public static boolean send(File file, Cliente client) throws FileNotFoundException, IOException{
        FileInputStream in = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(in);
        
        byte[] mybytearray = new byte[(int) file.length()];
        
        long size = mybytearray.length+"file::".length();
        
        DataInputStream dis = new DataInputStream(bis);
        dis.readFully(mybytearray, 0, mybytearray.length);
        
        
        OutputStream out = client.getConexao().getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out);
       
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeUTF(file.getName());
        dos.writeLong(size);
        dos.write(mybytearray, 0, (int) size);
        dos.flush();

        
        System.out.println("Arquivo "+file+" enviado para cliente.");
        
    
        return true;
    }



    
}
