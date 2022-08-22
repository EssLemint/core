package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleTonTest {

  @Test
  @DisplayName("스프링 없는 DI")
  void pureContainer() {
    AppConfig appConfig = new AppConfig();

    //1. 조회 : 호출 시 객체 생성 무한
    MemberService memberService1 = appConfig.memberService();

    //2. 조회 : 호출 시 객체 생성성
   MemberService memberService2 = appConfig.memberService();

   //참조값이 다르다
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    Assertions.assertThat(memberService1).isNotSameAs(memberService2);
  }


  @Test
  @DisplayName("싱글톤 패턴을 사용한 객체 사용")
  void singleTonServiceTest() {
    SingleTonService instance = SingleTonService.getInstance();
    SingleTonService instance2 = SingleTonService.getInstance();

    Assertions.assertThat(instance).isSameAs(instance2);
    //same ==
    //equals .equals
  }

  @Test
  @DisplayName("스프링 컨테이너 싱글톤")
  void springContainer() {
    //AppConfig appConfig = new AppConfig();
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberService memberService1 = ac.getBean("memberService", MemberService.class);
    MemberService memberService2 = ac.getBean("memberService", MemberService.class);

    //참조 값이 같다
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    Assertions.assertThat(memberService1).isSameAs(memberService2);
  }
}
