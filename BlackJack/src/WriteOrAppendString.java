import java.io.*;
import java.lang.*;
import java.util.*;

public class WriteOrAppendString {

    /**
     * Method that will write text to an attached file
     *
     * @param text the text to be put on the file
     * @param file the file that will host the text
     * @throws IOException handles IOExceptions
     */

    public static void write(String text, File file) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(text);
        fw.close();
    }

    /**
     *Method that will "refresh" a file by filling it with an array of strings
     * @param arr the array of strings
     * @param file the file that will host the text
     * @throws IOException handles IOExceptions
     */

    public static void refresh(String[] arr, File file) throws IOException{
        FileWriter fw = new FileWriter(file, false);
        String txt = "";
        for(String str: arr){
            txt+=str+"\n";
        }
        fw.write(txt);
        fw.close();
    }

    public static void main(String[] args) {
        try{
            File f = new File("C:\\Users\\alefkovitz\\Desktop\\Zach's Program\\BlackJack\\data.txt");
            write("Hello World\n", f);
        } catch (IOException e){

        }
    }



}
