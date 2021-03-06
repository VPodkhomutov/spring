package spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
    private Map<String,Integer> hashMark = new HashMap<>();

    @Around("@annotation(spring.aspect.TimeLog)")
    public void logSaveMark(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {joinPoint.proceed();}
        catch (Exception e) {e.printStackTrace();}
        finally {
            long procTime =  System.currentTimeMillis()-start;
            LOGGER.info("TimeLog: время "+joinPoint.toShortString()+" равно :"+procTime);
        }

    }

    @Around(value = "execution(* getMark(..))")
    public Integer logCacheGetMark(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String fio;
        Integer mark=0;
        Object[] args = joinPoint.getArgs();
        fio = (String) args[0];
        if (hashMark.containsKey(fio)) {
            LOGGER.info("оценка {} взята из кэша", fio);
            mark = hashMark.get(fio);
        } else {
            mark = (Integer) joinPoint.proceed();
            hashMark.put(fio,mark);        }
        long procTime =  System.currentTimeMillis()-start;
        LOGGER.info("TimeLog: время "+joinPoint.toShortString()+" равно :"+procTime);
        return mark;
    }
}
