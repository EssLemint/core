package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
  //InitializingBean - afterPropertiesSet  메서드 초기화 지원
  //DisposableBean - destroy 지원
  //Spring것이어서 스프링만 지원한다.
  //외부 라이브러리에 적용 할 수 없다.
  //잘 사용하지 않는다.

  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출, url = " + url);
    connect();
    call("초기화 연결 메세지");
  }

  public void setUrl(String url) {
    this.url = url;
  }

  //서비스 시작 호출
  public void connect() {
    System.out.println("connect : " + url);
  }

  public void call(String message) {
    System.out.println("call = " + url + "message : " + message);
  }

  //서비스 종료 호출
  public void disconnect() {
    System.out.println("close : " + url);
  }

  @PostConstruct
  public void init() {
    System.out.println("afterPropertiesSet");
    connect();
    call("초기화 연결 메세지");
  }

  @PreDestroy
  public void close()  {
    System.out.println("destroy");
    disconnect();
  }
//  PostConstruct, PreDestroy - 유일한 단점 : 외부 라이브러리에 적용을 하지 못한다.
//  정리
//  @PostConstruct, @PreDestroy 애노테이션을 사용하자
//  코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 하면 @Bean 의 initMethod , destroyMethod 를 사용하자.
}
