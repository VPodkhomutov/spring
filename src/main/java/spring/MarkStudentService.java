package spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.aspect.TimeLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;

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
        Map<String,Integer> studentsMarkMap = new HashMap<String, Integer>();
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
            studentsMarkMap.put(fio,Integer.parseInt(mark));
        }
        saveMap(studentsMarkMap);
        System.out.println("Оценки сохранены! ");
        String fio;
        Integer returnMark;
        while (true) {
        System.out.println("Можно получить оценку студента. Введите Фамилию или exit для выхода из программы");
            fio = sc.nextLine();
            if ("exit".equalsIgnoreCase(fio)) {
                break;
            }
            System.out.println("Оценка студента "+fio+" равна: "+getMark(fio));
        }

    }

    public void saveMap(Map<String,Integer> mapMark) {
        j.saveMark(mapMark);
    }

    public Integer getMark(String fio) {
        return j.getMark(fio);
    }

}
