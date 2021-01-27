package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Students {
    private String name;
    private MarkStudent markStudent;

    @Autowired
    public Students(MarkStudent markStudent) {
        this.markStudent = markStudent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStudents() throws IOException {
        List<String> studentsArrayList = new ArrayList<String>();
        FileReader fileReader = new FileReader(new File(name));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine())!=null) {
            studentsArrayList.add(line);
        }
        return studentsArrayList;
    }

    public Map<String,Integer> getMark(List<String> students) {
        return markStudent.fillMarkStudent(students);
    }
}
