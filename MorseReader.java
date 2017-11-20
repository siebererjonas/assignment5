package rainer_sieberer;

import java.io.*;
import java.util.*;

public class MorseReader extends FilterInputStream{
   
   int next;
   MorseTree decoder = createDecoder();
   
   public MorseReader(InputStream in){
      super(in);
      
      try{
         do{
            next = in.read();
         }while(next != '.' && next != '-');
      }
      catch(IOException e){
         System.out.println("Wrong Input!");
      }
   }
   
   public int read() throws IOException{
      
      int[] letter = new int[5];
      int point = 0;
      
      if(next == ' '){
         next = in.read();
         return ' ';
      }
      
      while(ready()){
         
         int c = next;
         next = in.read();
         
         switch (c){
            case '.':
               letter[point] = c;
               point++;
               break;
            case '-':
               letter[point] = c;
               point++;
               break;
            case ' ':
               return decode(letter);
            case -1:
               return decode(letter);
            default:
               System.out.println("Wrong Input!");
         }      
      }
      return decode(letter);
   }
   
   public boolean ready(){
      return next != -1;
   }
   
   public int decode(int[] code){
      
      int i = 0;
      MorseTree current = decoder;
      
      while(i < 4 && code[i + 1] != 0){
      
         if(code[i] == '.'){
            current = current.getLeftTree();
         } else{
            current = current.getRightTree();
         }
         
         i++;
      }
      
      if(code[i] == '.'){
         i = current.leftEl;
      } else{
         i = current.rightEl;
      }
      
      if(i == 0){
         System.out.println("Wrong Input");
      }
      
      return i;
   }
   
   private MorseTree createDecoder(){
      
      MorseTree root = new MorseTree(101, 116);
      root.setLeft(new MorseTree(105, 97));
      root.setRight(new MorseTree(110, 109));
      root.left.setLeft(new MorseTree(115, 117));
      root.left.setRight(new MorseTree(114, 119));
      root.right.setLeft(new MorseTree(100, 107));
      root.right.setRight(new MorseTree(103, 111));
      root.left.left.setLeft(new MorseTree(104, 118));
      root.left.left.setRight(new MorseTree(102, -1));
      root.left.right.setLeft(new MorseTree(108, -1));
      root.left.right.setRight(new MorseTree(112, 106));
      root.right.left.setLeft(new MorseTree(98, 120));
      root.right.left.setRight(new MorseTree(99, 121));
      root.right.right.setLeft(new MorseTree(122, 113));
      root.right.right.setRight(new MorseTree(-1, -1));
      
      root.left.left.left.setLeft(new MorseTree(53, 52));
      root.left.left.left.setRight(new MorseTree(0, 51));
      root.left.left.right.setLeft(new MorseTree(0, 0));
      root.left.left.right.setRight(new MorseTree(0, 50));
      root.left.right.left.setLeft(new MorseTree(0, 0));
      root.left.right.left.setRight(new MorseTree(43, 0));
      root.left.right.right.setLeft(new MorseTree(0, 0));
      root.left.right.right.setRight(new MorseTree(0, 49));
      root.right.left.left.setLeft(new MorseTree(54, 61));
      root.right.left.left.setRight(new MorseTree(47, 0));
      root.right.left.right.setLeft(new MorseTree(0, 0));
      root.right.left.right.setRight(new MorseTree(0, 0));
      root.right.right.left.setLeft(new MorseTree(55, 0));
      root.right.right.left.setRight(new MorseTree(0, 0));
      root.right.right.right.setLeft(new MorseTree(56, 0));
      root.right.right.right.setRight(new MorseTree(57, 48));
      
      return root;
   }
   
   private class MorseTree{
      
      int leftEl;
      int rightEl;
      MorseTree left;
      MorseTree right;
      
      private MorseTree(int newLeft, int newRight){
         leftEl = newLeft;
         rightEl = newRight;
      }

      
      private void setLeft(MorseTree newLeftTree){
         this.left = newLeftTree;
      }
      
      private void setRight(MorseTree newRightTree){
         this.right = newRightTree;
      }
      
      private MorseTree getLeftTree(){
         return left;
      }
      
      private MorseTree getRightTree(){
         return right;
      }
   }
}