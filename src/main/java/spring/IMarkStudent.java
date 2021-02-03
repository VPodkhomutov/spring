package spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public interface IMarkStudent {

    void fillAndSaveMarkStudent(List<String> listStudent);

    void saveMap(Map<String,Integer> mapMark) ;

}