package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanLifeCycleTest {

  @Test
  public void lifeCycleTest() {
    ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
    NetworkClient networkClient = ac.getBean(NetworkClient.class);

    ac.close();
  }

  static class LifeCycleConfig {

    //스프링 빈이 스프링 코드에 의존하지 않는다.
    //코드가 아닌 설정 정보를 사용하기 때문에 외부라이브러리에도 초기화, 종료 메서드를 적용 할 수 있다.
    //@Bean의 destroyMethod 는 기본값이 (inferred) (추론)으로 등록되어 있다.
    //주로 외부 라이브러리의 종료 기능이  close, shutdown으로 네이밍이 되어있기에 추론하여 종료 시켜준다.
    //@Bean(initMethod = "init", destroyMethod = "close")
    @Bean
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("https://hello-spring.dev");

      return networkClient;
    }
  }
}
