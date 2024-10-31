package velo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.System.out;

public class ReadFromFile {
    private String filepath;
    public ReadFromFile(String filepath){
     this.filepath=filepath;
    }
    public void readFile(ListStorage<String>listStorage,MapStorage<String>mapStorage){
        try(BufferedReader in=new BufferedReader(new FileReader(filepath))){
           String line;
           while((line =in.readLine())!=null){
               if(line.startsWith("Id:")) {
                   mapStorage.put(line,line);
               }else if(line.contains("Id")) {
                   listStorage.add(line);
               }
           }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
