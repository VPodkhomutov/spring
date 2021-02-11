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
        List<StudentMark> studentMarks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String mark;
        for (String fio: listStudent) {
            LOGGER.info("Введите оценку студента "+fio);
            mark = sc.nextLine();
            while (!validateMark.validate(mark)) {
                LOGGER.info("Введенная оценка не является числом от 1 до 5");
                LOGGER.info("Введите новую оценку студента "+fio);
                mark = sc.nextLine();
            }
            studentMarks.add(new StudentMark(fio, Integer.parseInt(mark)));
        }
        saveToDatabase(studentMarks);
        LOGGER.info("Оценки сохранены! ");
        String fio;
        Integer returnMark;
        while (true) {
            LOGGER.info("Можно получить оценку студента. Введите Фамилию или exit для выхода из программы");
            fio = sc.nextLine();
            if ("exit".equalsIgnoreCase(fio)) {
                break;
            }
            returnMark=getMark(fio);
            if (returnMark.equals(0)) {
                LOGGER.info("Оценок у студента {} нет", fio);
            }
             else{
                LOGGER.info("Оценка студента {} равна: {}" ,fio, returnMark);
            }
        }
    }


    public void saveToDatabase(List<StudentMark> studentMarks) {
        j.saveMarkDatabase(studentMarks);
    }

    public Integer getMark(String fio) {
        return j.getMark(fio);
    }

}
