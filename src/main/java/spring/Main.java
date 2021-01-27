package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("spring");
        String file = "D:\\student.txt";
        Students students = context.getBean(Students.class);
        students.setName(file);
       try{
            Journal journal = context.getBean(Journal.class);
            journal.setNameFile("D:\\journal.txt");
            journal.saveMark(students.getMark(students.getStudents()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Файл не найден");
        }



    }
}
