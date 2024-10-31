package velo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WriteToFile {
    private String filepath;
    public WriteToFile(String filepath){
        this.filepath=filepath;
    }
    public WriteToFile(){
    }
    public void  WriteToFile(String thing){
        try(BufferedWriter out=new BufferedWriter(new FileWriter(filepath,true))){
            out.write(thing);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
