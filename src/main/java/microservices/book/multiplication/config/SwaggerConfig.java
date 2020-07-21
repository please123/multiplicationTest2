package microservices.book.multiplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
     
	private ApiInfo apiInfo() { 
        return new ApiInfoBuilder().title("Demo").description("API EXAMPLE").build(); 
    }
     
     
     @Bean
	public Docket commonApo() {
    	 return new Docket(DocumentationType.SWAGGER_2)
                      //.groupName("example")
                      .apiInfo(this.apiInfo())
                      .select()
                     .apis(RequestHandlerSelectors.basePackage("microservices.book.multiplication"))
//                   .paths(PathSelectors.ant("/primaryaccount"))
                      .build();
     }
     
// springfox 라이브러리 사용시 아래내용 추가
     @Override
     protected void addResourceHandlers(ResourceHandlerRegistry registry) {
          registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
          registry.addResourceHandler("/webjars/**") .addResourceLocations("classpath:/META-INF/resources/webjars/"); }
}
