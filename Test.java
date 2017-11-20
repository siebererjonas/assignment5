//package rainer_sieberer;

import java.io.*;
import rainer_sieberer.*;

public class Test{
   
   public static void main(String[] args){
      
      //MORSE READER TEST --------------------------------------------------------------
      
      InputStream in = Test.class.getResourceAsStream("sample.txt");
      
      MorseReader mr = new MorseReader(in);
      
      String result = "";
      
      while(mr.ready()){
      
         try{
            result += (char) mr.read();
         }
         catch(IOException e){
         }
      }
      
      System.out.println(result);
      System.out.println("\n\n");
      
      
      //ROT13 WRITER TEST --------------------------------------------------------------
      
      ROT13Writer rw = null;
      
      try{
         rw = new ROT13Writer("output.txt");
      }
      catch(IOException e){
         System.out.println("unexpected ERROR");
      }
      
      try{
         
         for(int i = 0; i < result.length(); i++){
            rw.write(result.charAt(i));
         }
      }
      catch(IOException e){
      }
      
      System.out.println("sample now saved as ROT13 in output.txt!");
      
      try{
         mr.close();
         rw.close();
      }
      catch(Exception e){
      }
   }
}