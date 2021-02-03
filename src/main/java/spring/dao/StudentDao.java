package spring.dao;

import spring.StudentMark;

import java.util.List;

public interface StudentDao {
    Integer getMarkByFioStudent(String fio);
    void addStudents(List<StudentMark> studentMarks);
}
