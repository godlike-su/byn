package com.byn.web.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
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
 * @author Tian
 * @version v1.0.0.0
 * @Date 2021/11/4 13:57
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class WebSwagger2Config {

    @Bean
    public Docket createWebManageApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("常规web操作")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.byn.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("常规web操作")
                .description("swagger Restful API文档")
                .version("1.0.0").build();
    }
}
