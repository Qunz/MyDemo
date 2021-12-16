package my.qunz.myDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("my.qunz.myDemo.mapper")
@ComponentScan(basePackages = {"my.qunz.myDemo", "com.cccc.bim.common.core", "com.cccc.bim.common.mybatis"})
public class MyDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyDemoApplication.class, args);
    }

}
