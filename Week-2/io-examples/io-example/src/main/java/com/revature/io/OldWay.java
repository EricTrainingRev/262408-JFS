package com.revature.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OldWay {

    static String fileToRead = "C:\\Users\\EricSuminski\\Desktop\\temp\\src\\main\\resources\\to-read.txt";
    static String fileToWrite = "C:\\Users\\EricSuminski\\Desktop\\temp\\src\\main\\resources\\write-output.txt";
    static String writeContent = "This content is written\nto the new file\nwe make!";

    public static void main(String[] args) {
        try{
            readFile(fileToRead);
            writeFile(fileToWrite, writeContent, false);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }
    /*
        Reads the contents of a text file character by character and prints
        them to the console.

        Classes used:
        - FileReader: A character stream class used to read text files.
                      It converts bytes from the file into characters.
        - IOException: Thrown when an input/output error occurs while
                       accessing the file.

        How it works:
        1. Creates a FileReader using the provided file path.
        2. Uses a try-with-resources statement to automatically close the
           FileReader when finished.
        3. Reads one character at a time using reader.read().
        4. The read() method returns an integer representing the character's
           Unicode value, or -1 when the end of the file is reached.
        5. Each character is cast to a char and printed to the console.
     */
    public static void readFile(String filePath) throws IOException {
        try(FileReader reader = new FileReader(filePath)){
            int character;
            while((character = reader.read()) != -1){
                System.out.print((char) character);
            }
        }
    }

    /*
          Writes text content to a file.

          Classes used:
          - FileWriter: A character stream class used to write text data to files.
          - IOException: Thrown when an input/output error occurs while
            writing to the file.

          How it works:
          1. Creates a FileWriter for the specified file.
          2. The append parameter controls whether content is appended to the
             existing file (true) or overwrites the file contents (false).
          3. Uses a try-with-resources statement to automatically close the
             FileWriter after writing.
          4. Writes the provided content string to the file using writer.write().
     */
    public static void writeFile(String filePath, String content, boolean append) throws IOException{
        try(FileWriter writer = new FileWriter(filePath, append)){
            writer.write(content);
        }
    }



}
