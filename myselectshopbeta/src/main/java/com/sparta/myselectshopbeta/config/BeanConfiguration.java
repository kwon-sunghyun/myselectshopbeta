//package com.sparta.myselectshopbeta.config;
//
//import com.sparta.myselectshopbeta.repository.ProductRepository;
//import com.sparta.myselectshopbeta.repository.ProductRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration //Bean으로 등록시 꼭 추가해야함.
//public class BeanConfiguration {
//    @Bean
//    public ProductRepository productRepository() {
//        String dbUrl = "jdbc:h2:mem:db";
//        String username = "sa";
//        String password = "";
//        return new ProductRepository(dbUrl, username, password);
//    }
//}