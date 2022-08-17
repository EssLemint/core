package hello.core.order;

public interface OrderService {
  Order createOrder(Long memberOd, String itemName, int itemPrice);

}
