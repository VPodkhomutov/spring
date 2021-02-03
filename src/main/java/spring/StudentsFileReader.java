package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.aspect.TimeLog;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentsFileReader {
    private String name;
    private IMarkStudent markStudent;

    @Autowired
    public StudentsFileReader(IMarkStudent markStudent) {
        this.markStudent = markStudent;
    }

    public void setName(String name) {
        this.name = name;
    }

    @TimeLog
    public void getStudents() throws IOException {
        List<String> studentsArrayList = new ArrayList<String>();
        FileReader fileReader = new FileReader(new File(name));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine())!=null) {
            studentsArrayList.add(line);
        }
        getMark(studentsArrayList);
    }

    public void getMark(List<String> students) {
         markStudent.fillAndSaveMarkStudent(students);
    }
}
