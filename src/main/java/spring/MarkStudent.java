package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class MarkStudent {
    ValidateMark validateMark;

    @Autowired
    public MarkStudent(ValidateMark validateMark) {
        this.validateMark = validateMark;
    }

    public Map<String,Integer> fillMarkStudent(List<String> listStudent){
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
    return studentsMarkMap;
    }

}
