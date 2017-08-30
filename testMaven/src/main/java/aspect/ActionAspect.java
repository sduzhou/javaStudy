package aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ActionAspect {
	
	@Pointcut("@annotation(aspect.UserAccess)")
	public void controllerAspect() {
		System.out.println("controllerAspect");
	}
	
	@Around("@annotation(userAccess)")
	public void doControllerAround(ProceedingJoinPoint point, UserAccess userAccess) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpServletResponse reponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		System.out.println("doControllerAround,"+"uri="+uri+",context="+context);
		System.out.println(userAccess.needLogin());
		point.proceed();
		
	}


}
