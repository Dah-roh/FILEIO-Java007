import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class Nio {

    public static void main(String[] args) throws IOException {
        String fileName = "";

        //example of using args array
        if(args.length==0||args[0].equalsIgnoreCase("production")){
            System.out.println("I am on!!");
            fileName = "src/main/resources/file-production.txt";

        }
        else if(args[0].equalsIgnoreCase("off")){
            System.out.println("I am off!!");
            fileName = "/Users/daro/Downloads/NIO/src/main/resources/file.txt";
        }
        String [] list = new String[2];
        Map<String, String> twoStrings = new HashMap<>();

        //io file reading
        try (BufferedReader br = new BufferedReader(new FileReader(fileName));){
            String words = "";
            while((words=br.readLine())!=null){
                list=words.split("=");
//                twoStrings.put(list[0], list[1]);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        //example 1 replica of io file reading with nio
        Path path = Paths.get("src/main/resources/file.txt");
        Path path1 = Paths.get("src/main/file.txt");
        String[] list1 = new String[2];
        //nio
        try(BufferedReader br = Files.newBufferedReader(path)){
            String lines = "";
            while ((lines = br.readLine())!=null){
                list1 = lines.split("=");
//                twoStrings.put(list1[0], list1[1]);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

//        example 2 nio file reading with readAlllines method
        try{
            List<String> allLines = Files.readAllLines(path);
//            for (String allLine : allLines) {
//                twoStrings.put(allLine.split("=")[0], allLine.split("=")[1]);
//            }
//            allLines.forEach(str->{twoStrings.put(str.split("=")[0], str.split("=")[1]);});
//            System.out.println(twoStrings);
        }
        catch(IOException e){
            e.printStackTrace();
        }

//        example 3 nio file reading with lines method
        try {
            Files.lines(path).forEach(str->{
                if (str.equalsIgnoreCase("Application"))
                    System.out.println(str);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

//      example 4 other files methods
        if (Files.exists(path)) {
            Files.copy(path, path1, StandardCopyOption.REPLACE_EXISTING);
        }
        else{
            throw new IOException("No such directory found for the first path");
        }
        System.out.println(Files.exists(path));

//      example 5 writing to file with nio Files.write method
        List<String> writeToPath = Arrays.asList("Hey, guys!!", "lets overwrite this file data");
        Files.write(path1, writeToPath);

//        System.out.println(twoStrings.get("port"));
    }
}
