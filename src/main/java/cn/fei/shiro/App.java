package cn.fei.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liufei
 * @date 2018/6/6 11:34
 */
@SpringBootApplication
@MapperScan({"cn.fei.shiro.mapper"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
