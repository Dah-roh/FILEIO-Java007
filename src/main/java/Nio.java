import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Nio {

    public static void main(String[] args) {
        String fileName = "";
        if(args.length==0||args[0].equalsIgnoreCase("production")){
            System.out.println("I am on!!");
            fileName = "/Users/daro/Downloads/NIO/src/main/resources/file-production.txt";

        }
        else if(args[0].equalsIgnoreCase("off")){
            System.out.println("I am off!!");
            fileName = "/Users/daro/Downloads/NIO/src/main/resources/file.txt";
        }
        String [] list = new String[2];
        Map<String, String> twoStrings = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName));){
//                bw.write("Application=Nio-application-production\n");
//            bw.write("port=8080\n");
//            bw.write("db-name=postgresTest");
            String words = "";
            while((words=br.readLine())!=null){
                System.out.println(words);
                list=words.split("=");
                twoStrings.put(list[0], list[1]);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(twoStrings.get("port"));
    }
}
