package spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.aspect.TimeLog;
import java.util.*;

@Component
public class MarkStudentService implements IMarkStudent {
    private ValidateMark validateMark;
    private Journal j;
    private static final Logger LOGGER = LoggerFactory.getLogger(MarkStudentService.class);

    @Autowired
    public MarkStudentService(ValidateMark validateMark, Journal j) {
        this.validateMark = validateMark;
        this.j = j;
    }

    @TimeLog
    @Override
    public void fillAndSaveMarkStudent(List<String> listStudent){
        //Map<String,Integer> studentsMarkMap = new HashMap<String, Integer>();
        List<StudentMark> studentMarks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String mark;
        for (String fio: listStudent) {
            System.out.println("Введите оценку студента "+fio);
            mark = sc.nextLine();
            while (!validateMark.validate(mark)) {
                System.out.println("Введенная оценка не является числом от 1 до 5");
                System.out.println("Введите новую оценку студента "+fio);
                mark = sc.nextLine();
            }
            //studentsMarkMap.put(fio,Integer.parseInt(mark));
            studentMarks.add(new StudentMark(fio, Integer.parseInt(mark)));
        }
        //saveMap(studentsMarkMap);
        saveToDatabase(studentMarks);
        System.out.println("Оценки сохранены! ");
        String fio;
        Integer returnMark;
        while (true) {
        System.out.println("Можно получить оценку студента. Введите Фамилию или exit для выхода из программы");
            fio = sc.nextLine();
            if ("exit".equalsIgnoreCase(fio)) {
                break;
            }
            returnMark=getMark(fio);
            if (returnMark.equals(0)) {
                System.out.println("Оценок у студента " + fio + " нет");
            }
             else{
            System.out.println("Оценка студента " + fio + " равна: " + returnMark);
            }
        }
    }

    public void saveMap(Map<String,Integer> studentsMarkMap) {
        j.saveMark(studentsMarkMap);
    }

    public void saveToDatabase(List<StudentMark> studentMarks) {
        j.saveMarkDatabase(studentMarks);
    }

    public Integer getMark(String fio) {
        return j.getMark(fio);
    }

}
