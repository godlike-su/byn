package com.byn.client.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Tian
 * @version v1.0.0.0
 * @Date 2021/11/4 13:57
 */
@Configuration
@EnableSwaggerBootstrapUI
public class AppSwagger2Config {

    @Bean
    public Docket createAppManageApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("client")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.byn.client"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("用户类")
                .description("swagger Restful API文档")
                .version("1.0.0").build();
    }
}
