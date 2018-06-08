package cn.fei.item;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liufei
 * @date 2018/6/6 11:34
 */
@SpringBootApplication
@MapperScan({"cn.fei.item.mapper"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
