package spring;

import org.springframework.stereotype.Component;
import java.io.*;
import java.util.Map;

@Component
public class Journal {
    String nameFile;

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
    public void saveMark(Map<String,Integer> studentsMarkMap) throws IOException {

        try ( PrintWriter writer = new PrintWriter(new File(nameFile)) ) {
            for ( Map.Entry<String, Integer> entry : studentsMarkMap.entrySet() ) {
                writer.write("Студент: "+ entry.getKey() + "   оценка: " + entry.getValue() + System.lineSeparator());
                writer.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
