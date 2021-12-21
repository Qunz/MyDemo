package my.qunz.myDemo.aop;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class DynamicAspect {

    @Resource
    private DynamicRoutingDataSource dynamicRoutingDataSource;

    @Pointcut("@annotation(Dynamic)")
    public void pointcut() {}

    @Around(value = "pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            Object arg = joinPoint.getArgs()[0];
            String projectCode = (String) arg;
            System.out.println("@Around=请求参数" + projectCode);
            //组装datasource
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl(projectCode);
            dataSource.setUsername("root");
            dataSource.setPassword("Cccc@bim123");

            //添加datasource到动态数据源并设为primary
            dynamicRoutingDataSource.addDataSource("dynamic", dataSource);
            dynamicRoutingDataSource.setPrimary("dynamic");
            Object result = joinPoint.proceed();
            //方法结束时重置数据源
            dynamicRoutingDataSource.setPrimary("master");
            dynamicRoutingDataSource.removeDataSource("dynamic");

            return result;
        } catch (Throwable throwable) {
            //方法出错时重置数据源
            throwable.printStackTrace();
            dynamicRoutingDataSource.setPrimary("master");
            dynamicRoutingDataSource.removeDataSource("dynamic");
            return null;
        }
    }

}
