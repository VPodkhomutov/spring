package spring;

import org.springframework.stereotype.Repository;
import spring.aspect.TimeLog;
import spring.dao.StudentDao;

import java.io.*;
import java.util.List;
import java.util.Map;

@Repository
public class Journal {
    private StudentDao studentDao;

    public Journal( StudentDao studentDao) {
        this.studentDao = studentDao;
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

    @TimeLog
    public void saveMarkDatabase(List<StudentMark> studentMark)  {
        studentDao.addStudents(studentMark);
    }

    public Integer getMark(String fio) {
        return studentDao.getMarkByFioStudent(fio);
    }
}
