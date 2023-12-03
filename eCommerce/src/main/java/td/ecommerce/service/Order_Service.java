package td.ecommerce.service;

import td.ecommerce.model.Order;

import java.util.List;

public interface Order_Service {
    public List<Order> getAllOrders();

    public Order persistOrder(Order order);

    public Order getOrderById(Long id);

    public Order updateOrder(Order order);

    public void deleteOrder(Long id);

    public List<Order> getOrdersByCustomerId(Long customerId);

}
