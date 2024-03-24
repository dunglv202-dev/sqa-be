package vn.edu.ptit.sqa.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import vn.edu.ptit.sqa.dto.Error;
import vn.edu.ptit.sqa.helper.MessageHelper;

@Aspect
@Component
@RequiredArgsConstructor
public class ExceptionHandlerAspect {
    private final MessageHelper messageHelper;

    @Pointcut("execution(* vn.edu.ptit.sqa.exception.DefaultExceptionHandler.handle*(..))")
    private void exceptionHandlerMethod() {}

    @AfterReturning(pointcut = "exceptionHandlerMethod()", returning = "error")
    public void resolveClientMessage(Error error) {
        String rawMessage = error.getError();
        error.setError(messageHelper.getLocalizedMessage(rawMessage));
    }
}
