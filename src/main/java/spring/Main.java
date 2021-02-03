package spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        //ApplicationContext context = new AnnotationConfigApplicationContext("spring");
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        String file = "D:\\student.txt";
        StudentsFileReader students = context.getBean(StudentsFileReader.class);

        students.setName(file);

        try{
           students.getStudents();
           } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Файл не найден");
        }
    }
}
