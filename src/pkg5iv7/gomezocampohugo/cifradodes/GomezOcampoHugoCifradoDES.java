package pkg5iv7.gomezocampohugo.cifradodes;

/**
 *
 * @author hugog
 */
import java.io.*;

import javax.crypto.*;

import javax.crypto.spec.*;   //generar las llaves del cifrado

import java.security.*;      //trae las instancias del tipo de cifrado

public class GomezOcampoHugoCifradoDES{
    
    
    public void encriptar(String args , String Key) throws Exception {


        if(args == null){
            mensajeAyuda();
            System.exit(1);
        }

            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            DESKeySpec kspec = new DESKeySpec(Key.getBytes());
            SecretKey subllave = skf.generateSecret(kspec);
        
        Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding"); 
        
        cifrado.init(Cipher.ENCRYPT_MODE, subllave);
        

        
        byte[] buffer = new byte[1000]; 
        
        byte[] bufferCifrado;
        
        FileOutputStream out;
        try (FileInputStream in = new FileInputStream(args)) {
            out = new FileOutputStream(args+".cifrado");
            int bytesleidos = in.read(buffer, 0, 1000);
            while(bytesleidos != -1){
                
                bufferCifrado = cifrado.update(buffer, 0, bytesleidos);
                
                out.write(bufferCifrado);
                
                bytesleidos = in.read(buffer, 0, 1000);
            }   bufferCifrado = cifrado.doFinal();
            out.write(bufferCifrado);
        }
        out.close();
        //Limpioaaaaaaaaaaaaaaaaaaaaaaaaxddddd:v
        
        
        
        
    }

    public void Descifrar(String text , String Key)throws Exception {
        
        if(text == null){
            mensajeAyuda();
            System.exit(1);
        }
        
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        DESKeySpec kspec = new DESKeySpec(Key.getBytes());
        SecretKey subllave = skf.generateSecret(kspec);
        
        
        Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");  //AES, RSA, cualquier otro tipo de cifrado

        //System.out.println("2.- Cifrar con DES el fichero " + text + ", dejar el resultado en: " +text+".cifrado");
        
        cifrado.init(Cipher.DECRYPT_MODE, subllave);

        byte[] bufferPlano; //quiero ir leyendo de 1000 en 1000 bits del fichero
        byte[] buffer  = new byte[1000]; //aqui voy almacenar el resultado
        
        FileInputStream in = new FileInputStream(text);
        
        FileOutputStream out = new FileOutputStream(text+".cifrado");
        
        int bytesleidos = in.read(buffer, 0, 1000);
        while(bytesleidos != -1){

            bufferPlano = cifrado.update(buffer, 0, bytesleidos);

            out.write(bufferPlano);

            bytesleidos = in.read(buffer, 0, 1000);
        }

        bufferPlano = cifrado.doFinal();
        out.write(bufferPlano); //escribir el final del texto cifrado si lo hay
        
        in.close();
        out.close();
    }

    
    public static void mensajeAyuda() {
        System.out.println("Ejemplo de un cifrado DES utilizando librerias Crypto y Security");
        System.out.println("Necesita la carga de un archivo en txt, no se te olvide ¬¬ agregarlo");
        System.out.println("Con amor yo");
    }

    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
    
}
