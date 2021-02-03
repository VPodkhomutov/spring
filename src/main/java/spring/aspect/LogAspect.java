package spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
@Aspect
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
    private Map<String,Integer> hashMark = new HashMap<>();

    @Around("@annotation(spring.aspect.TimeLog)")
    public void logSaveMark(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        joinPoint.proceed();
        long procTime =  System.currentTimeMillis()-start;
        LOGGER.info("TimeLog: время "+joinPoint.toShortString()+" равно :"+procTime);
    }

    @Around(value = "execution(* getMark(..))")
    public Integer logCacheGetMark(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String fio;
        Integer mark=0;
        Object[] args = joinPoint.getArgs();
        fio = (String) args[0];
        if (hashMark.containsKey(fio)) {
            System.out.println("оценка "+fio+" взята из кэша");
            mark = hashMark.get(fio);
        } else {
            mark = (Integer) joinPoint.proceed();
            hashMark.put(fio,mark);        }
        long procTime =  System.currentTimeMillis()-start;
        LOGGER.info("TimeLog: время "+joinPoint.toShortString()+" равно :"+procTime);
        return mark;
    }
}
