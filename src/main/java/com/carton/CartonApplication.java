package com.carton;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Liurunyong
 * @date 2019/12/23
 */
@MapperScan("com.carton.dao")
@SpringBootApplication
public class CartonApplication {



    public static void main(String[] args) {
        SpringApplication.run(CartonApplication.class, args);
    }

}
