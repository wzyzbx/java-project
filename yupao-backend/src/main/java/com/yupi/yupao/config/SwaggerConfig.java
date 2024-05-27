package com.yupi.yupao.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
@Bean(value = "defaultApi2")
public Docket defaultApi2() {
return new Docket(DocumentationType.SWAGGER_2)
.apiInfo(apiInfo())
.select()
.apis(RequestHandlerSelectors.basePackage("com.yupi.yupao.controller"))
.paths(PathSelectors.any())
.build();
}
private ApiInfo apiInfo(){
return new ApiInfoBuilder()
.title("同城交友匹配系统")
.description("同城交友匹配系统接口文档")
.termsOfServiceUrl("https://github.com/liyupi")
.contact(new
Contact("zbx","https://github.com/liyupi","12345@qq.com"))
.version("1.0")
.build();
}
}