package td.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import td.ecommerce.model.Order;
import td.ecommerce.repository.Order_Repository;
import td.ecommerce.service.Order_Service;

import java.util.List;

@Service
public class OrderServiceImpl implements Order_Service {
    private Order_Repository orderRepository;

    @Autowired
    public OrderServiceImpl(Order_Repository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order persistOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
