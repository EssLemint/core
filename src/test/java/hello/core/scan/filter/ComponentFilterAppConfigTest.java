package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {

  @Test
  void filterScan() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
    BeanA beanA = ac.getBean("beanA", BeanA.class);
    Assertions.assertThat(beanA).isNotNull();

    assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
  }

  @Configuration
  @ComponentScan(
//      FilterType.ANNOTATION - 기본값
//      FilterType.ASSIGNABLE_TYPE - 지정 타입, 자식 타입
//      ===아래로 사용하는 빈도수가 매우적은 편
//      FilterType.ASPECTJ - AspectJ 패턴 사용
//      FilterType.REGEX - 정규 표현식
//      FilterType.CUSTOM - TypeFilter 인터페이스 구현 -> 처리
      includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
      excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
      //BeanA 빼고싶을때
      //, @Filter(type = Filter.ASSIGNABLE_TYPE, class = BeanA.class)
  )
  static class ComponentFilterAppConfig {

  }
}
