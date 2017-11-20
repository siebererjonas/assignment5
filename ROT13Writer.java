package rainer_sieberer;

import java.io.*;

public class ROT13Writer extends FileWriter{
   
   private FileWriter out;
   
   public ROT13Writer(String filename) throws IOException{
         super(filename);
         out = new FileWriter(filename);
   }
   
   public void write(int obj) throws IOException{
      write(encode(obj));
   }
   
   public int encode(int obj) throws IOException{
      
      if(obj == ' '){
         return 32;
      }
      else if(obj > 96 && obj < 123){
         return (((obj - 97) + 13) % 26) + 97;
      }
      else if( obj > 47 && obj < 58){
         return obj;
      }
      else if(obj == 43 || obj == 47 || obj == 61){
         return obj;
      }
      else{
         System.out.println("Wrong Output Data!");
         return -1;
      }
   } 
}