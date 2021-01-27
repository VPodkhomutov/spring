package spring;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidateMark {

    public boolean validate(String mark){
        int i;
        try {
            i= Integer.parseInt(mark);
            return i>0&&i<6;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
