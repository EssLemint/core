package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

  @Test
  void AutowiredOption() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
  }

  static class TestBean {

    //@Autowired(required = false) - 의존관계가 존재하지 않으면 메서드 호출 안됨
    @Autowired(required = false)
    public void setNoBean1(Member noBean1) {
      System.out.println("no Bean = " + noBean1);
    }

    //@Nullable 호출은 되지만 Null 이 들어감
    @Autowired
    public void setNoBean2(@Nullable Member noBean2) {
      System.out.println("no Bean = " + noBean2);
    }

    //Optional<Member> -> Optional.empty 들어감
    @Autowired
    public void setNoBean3(Optional<Member> noBean3) {
      System.out.println("no Bean = " + noBean3);
    }
  }

}
