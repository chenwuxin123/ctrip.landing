package com.meipiao.ctrip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Chenwx
 * @Date: 2020/5/8 14:59
 * @since JDK1.11
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    /**
     * 通过 createRestApi函数来构建一个DocketBean
     * 函数名,可以随意命名,喜欢什么命名就什么命名
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select() // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.meipiao.ctrip.controller"))
                //不显示错误的接口地址
//                .paths(Predicates.not(PathSelectors.regex("/error")))//错误路径不监控
//                .paths(Predicates.not(PathSelectors.regex(".*/load_style")))//继承templateController的方法
//                .paths(Predicates.not(PathSelectors.regex(".*/load")))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("携程数据落地")
                /*.termsOfServiceUrl("http://despairyoke.github.io/")
                .contact("hfj")
                .version("2.0")*/
                //描述
                .description("携程数据落地 API")
                .build();
    }
}
