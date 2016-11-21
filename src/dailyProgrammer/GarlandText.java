package dailyProgrammer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
* A function garland that, given a lowercase word, returns the degree of the
* word if it's a garland word, and 0 otherwise.
*
* @author Josh Wein
*/
public class GarlandText {

    public static void main(String[] args) throws FileNotFoundException {
        File wordList = new File("enable1.txt");
        Scanner words = new Scanner(wordList);
        String word = "", max = "";
        int maxG = 0;
        while (words.hasNextLine()) {            
            word = words.nextLine();
            int test = garland(word);
            if(maxG < test){
                maxG = test;
                max = word;
                chain(word);
            }
        }
        System.out.println("Max garland " + max +  -  + maxG);
    }
    static int garland(String word) {        
        for (int pointer = 1; pointer < word.length(); pointer++) {
            int temp = pointer, gCount, k;            
            if (word.charAt(0) == word.charAt(pointer)) {
               for(gCount = 1, k = pointer, pointer = 0;
                   word.charAt(k) == word.charAt(pointer) && k != word.length()-1;
                   pointer++, gCount++, k++){}
               if(k == word.length()-1 && word.charAt(k) == word.charAt(pointer)){
                   return gCount;
               }
            }
            pointer = temp;
        }
        return 0;
    }
    
    static void chain(String word){
        int gar = garland(word);
        System.out.print(word.substring(0, gar));
        for(int i = 0; i < 10; i++){
            System.out.print(word.substring(gar, word.length()));
        }
        System.out.println();
    }
}
