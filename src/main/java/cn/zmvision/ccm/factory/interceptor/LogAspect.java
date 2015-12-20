package cn.zmvision.ccm.factory.interceptor;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
public class LogAspect {
	private final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private ObjectMapper mapper = new ObjectMapper();

	@Around("execution(* cn.zmvision.ccm.factory.**.controller..*.*(..))")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间

		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();

		// 获取输入参数
		Map<?, ?> inputParamMap = request.getParameterMap();
		// 获取请求地址
		String requestPath = request.getRequestURI();

		Object principal = SecurityUtils.getSubject().getPrincipal();

		logger.info("[{}] {}, principal:{}, request:{}",
				sdf.format(startTimeMillis), requestPath, principal,
				mapper.writeValueAsString(inputParamMap));

		return pjp.proceed();
	}
}
