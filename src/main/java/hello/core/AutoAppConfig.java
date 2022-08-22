package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    //componentscan의 시작 위치를 정할 수 있다.
    //basePackages = "hello.core.member" -> member 하위 폴더들  scan
    //지정하지 않을 시 - @ComponentScan (hello.core) 하위 폴더 전부
//    basePackages = "hello.core",
//    basePackageClasses = AutoAppConfig.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
    //Configuration.class 제외 현재 수동으로 등록하고 있는 Configuration.class
)
public class AutoAppConfig {
}
