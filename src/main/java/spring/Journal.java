package spring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import spring.aspect.TimeLog;

import java.io.*;
import java.util.Map;

@Repository
public class Journal {
    private String nameFile;

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    @TimeLog
    public void saveMark(Map<String,Integer> studentsMarkMap)  {

        try ( PrintWriter writer = new PrintWriter(new File("D:\\journal.txt")) ) {
            for ( Map.Entry<String, Integer> entry : studentsMarkMap.entrySet() ) {
                writer.write("Студент: "+ entry.getKey() + "   оценка: " + entry.getValue() + System.lineSeparator());
                writer.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getMark(String fio) {
        try {Thread.sleep(1500);}
        catch (Exception e){ e.printStackTrace();}
        return 4;
    }
}
