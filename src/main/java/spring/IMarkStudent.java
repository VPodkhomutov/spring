package spring;

import java.util.List;
import java.util.Map;

public interface IMarkStudent {

    void fillAndSaveMarkStudent(List<String> listStudent);

    void saveMap(Map<String,Integer> mapMark) ;

}