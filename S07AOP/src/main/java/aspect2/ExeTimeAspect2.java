package aspect2;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ExeTimeAspect2 {

	@Around("aspect2.CommonPointcut.commonTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[ExeTimeAspect] measure1");
		long start = System.nanoTime();
		try {
			System.out.println("[ExeTimeAspect] measure2");
			Object result = joinPoint.proceed(); //핵심기능
			return result;
		} finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(), Arrays.toString(joinPoint.getArgs()),
					(finish - start));
		}
	}

}
