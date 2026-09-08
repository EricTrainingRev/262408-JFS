package com.revature.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class NewWay {

    static InputStream readStream = NewWay.class.getClassLoader().getResourceAsStream("to-read.txt");
    static String fileToRead = "C:\\Users\\EricSuminski\\Desktop\\temp\\src\\main\\resources\\to-read.txt";
    static String fileToWrite = "C:\\Users\\EricSuminski\\Desktop\\temp\\src\\main\\resources\\write-output.txt";
    static String writeContent = "This content is written\nto the new file\nwe make!";
    static String appendContent = " And this gets appended on.";

    public static void main(String[] args) {
        try{
            readFile(readStream);
            readFile(fileToRead);
            writeFile(fileToWrite, writeContent, false);
            readFile(fileToWrite);
            writeFile(fileToWrite, appendContent, true);
            readFile(fileToWrite);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /*
      Reads and displays text content from an InputStream.

      This method validates that a stream was provided, then wraps the stream
      in an InputStreamReader configured for UTF-8 decoding so raw bytes can be
      converted into characters. The InputStreamReader is further wrapped in a
      BufferedReader to improve reading performance and provide convenient
      line-by-line access through readLine().

      The method reads until the end of the stream is reached and writes each
      line to the standard output stream (System.out).

      Resources used:
      - InputStream: Source of the file or text data.
      - InputStreamReader: Converts UTF-8 encoded bytes into characters.
      - BufferedReader: Buffers data and supports line-by-line reading.
      - System.out: Writes output to the console.

      Resource management:
      The try-with-resources statement automatically closes the BufferedReader
      when processing is complete. Closing the BufferedReader also closes the
      underlying InputStreamReader and InputStream.
     */
    public static void readFile(InputStream readStream) throws IOException {
        if (readStream == null ){
            throw new IOException("readStream is null");
        }
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(readStream, StandardCharsets.UTF_8))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
    }

    /*
      Reads the entire contents of a file and prints them to the console.

      This method uses the Java NIO Files utility class to read all text from
      the specified file into memory as a single String. The resulting content
      is then written to the standard output stream.

      This approach is simple and efficient for small to moderately sized files,
      but loads the entire file into memory before printing.

      Resources used:
      - Path: Represents the filesystem location of the file.
      - Files.readString(): Reads all file content into a String.
      - System.out: Writes the file contents to the console.

      Resource management:
      File handles are managed internally by the NIO API and are automatically
      released when the read operation completes.
     */
    public static void readFile(String filePath) throws IOException {
        System.out.println(Files.readString(Path.of(filePath)));
    }

    /*
      Writes text content to a file using UTF-8 encoding.

      The write behavior is controlled by the append flag. When append is true,
      new content is added to the end of the existing file. When append is false,
      any existing file content is replaced.

      The file is created automatically if it does not already exist.

      Resources used:
      - Path: Represents the target file location.
      - Files.write(): Performs the file write operation.
      - StandardCharsets.UTF_8: Encodes text as UTF-8 bytes.
      - StandardOpenOption.CREATE: Creates the file if necessary.
      - StandardOpenOption.APPEND: Adds content to the end of the file.
      - StandardOpenOption.TRUNCATE_EXISTING: Replaces existing file content.

      Resource management:
      File handles and I/O resources are managed internally by Files.write()
      and are released when the operation completes.
     */
    public static void writeFile(String filePath, String content, boolean append) throws IOException {
        StandardOpenOption writeOption = append ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING;
        Files.write(Path.of(filePath), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, writeOption);
    }
}
