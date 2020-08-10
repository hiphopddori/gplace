package kr.smartscore.gplace.web.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ThrowableExceptionHandlerAspect {
    private static final Logger logger = LoggerFactory.getLogger(ThrowableExceptionHandlerAspect.class);

    @AfterThrowing(pointcut = "execution(public kr.smartscore.gplace.web.ResultInfo *(..))", throwing = "ex")
    public void resultFlagExceptHandler(JoinPoint joinPoint , Exception ex) throws Throwable{
        /*
        String sDesc = ExceptionUtils.getMessage(ex);
        Object targetObject  = joinPoint.getTarget();
        Signature signature = joinPoint.getSignature();

        String sTargetName = targetObject.toString();
        String sMethodName = signature.toString();

        String sLog = "[Target]:"+sTargetName + System.getProperty("line.separator");
        sLog+="[Method]:"+sMethodName + System.getProperty("line.separator");
        sLog+="[Exception]:"+sDesc + System.getProperty("line.separator");
        */
        logger.error("ERROR");
        throw new ResponseException("테스트 입니다.");
    }
}
