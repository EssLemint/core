package hello.core.singleton;

public class StatefulService {

//  private int price; //상태유지

  public int order(String name, int price) {
    System.out.println("name = " + name + " price = " + price);
//    this.price = price;
    return price;
//    무상태
  }

//  public int getPrice() {
//    return price;
//  }
}
