package com.spring.security.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-08 11:34:00
 * 描    述：系统服务组件Aspect切面Bean
 *
 * 实现AOP的切面主要有以下几个要素：
 *   使用@Aspect注解将一个java类定义为切面类
 *   使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
 *   根据需要在切入点不同位置的切入内容
 *   使用@Before在切入点开始处切入内容
 *   使用@After在切入点结尾处切入内容
 *   使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
 *   使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
 *   使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 *
 * AOP切面中的同步问题
 *   在WebLogAspect切面中，分别通过doBefore和doAfterReturning两个独立函数实现了切点头部和切点返回后执行的内容，
 *   若我们想统计请求的处理时间，就需要在doBefore处记录时间，并在doAfterReturning处通过当前时间与开始处记录的时间计
 *   算得到请求处理的消耗时间。
 *   那么我们是否可以在WebLogAspect切面中定义一个成员变量来给doBefore和doAfterReturning一起访问呢？是否会有同步
 *   问题呢？的确，直接在这里定义基本类型会有同步问题，所以我们可以引入ThreadLocal对象，像下面这样进行记录：
 *
 * AOP切面的优先级
 *   由于通过AOP实现，程序得到了很好的解耦，但是也会带来一些问题，比如：我们可能会对Web层做多个切面，校验用户，校验头信息
 *   等等，这个时候经常会碰到切面的处理顺序问题。
 *   所以，我们需要定义每个切面的优先级，我们需要@Order(i)注解来标识切面的优先级。i的值越小，优先级越高。假设我们还有一
 *   个切面是CheckNameAspect用来校验name必须为didi，我们为其设置@Order(10)，而上文中WebLogAspect设置为
 *   //@Order(5)，所以WebLogAspect有更高的优先级，这个时候执行顺序是这样的：
 *   --在@Before中优先执行@Order(5)的内容，再执行@Order(10)的内容
 *   --在@After和@AfterReturning中优先执行@Order(10)的内容，再执行@Order(5)的内容
 *   所以我们可以这样子总结：
 *   --在切入点前的操作，按order的值由小到大执行
 *   --在切入点后的操作，按order的值由大到小执行
 */
@Aspect //使用@Aspect注解将一个java类定义为切面类
@Order(5)
@Component //声明这是一个组件
public class ControllerAspect {

    final static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    //使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
    @Pointcut("execution(public * com.spring.security.controller..*.*(..))")
    public void webLog() {
    }

    //使用@Before在切入点开始处切入内容
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //测试日志配置
//        logger.trace("trace");
//        logger.debug("debug str");
//        logger.info("info str");
//        logger.warn("warn");
//        logger.error("error");

        // 记录下请求内容
        logger.info("请求URL   : {}", request.getRequestURL().toString());
//        logger.info("请求方式  : {}", request.getMethod());
//        logger.info("请求IP地址: {}", request.getRemoteAddr());
//        logger.info("请求的类  : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("请求值    : {}", Arrays.toString(joinPoint.getArgs()));

    }

    //使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("返回值 : {}", ret);
//        logger.info("用  时 : {}", (System.currentTimeMillis() - startTime.get()));
    }

}
