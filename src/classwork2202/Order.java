package classwork2202;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private String id;
    private Customer customer;
    private List<Product> product;
    private LocalDate orderDate;

    public Order(String id, Customer customer, List<Product> product, LocalDate orderDate) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.orderDate = orderDate;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProduct() {
        return product;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", product=" + product +
                '}';
    }
}