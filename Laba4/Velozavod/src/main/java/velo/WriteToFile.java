package velo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    private String filepath;
   public WriteToFile(String filepath){
        this.filepath=filepath;
    }
    public void  WriteToFile(String thing){
       try(BufferedWriter out=new BufferedWriter(new FileWriter(filepath,true))){
        out.write(thing);
        out.newLine();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }
}
