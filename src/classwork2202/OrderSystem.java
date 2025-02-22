package classwork2202;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderSystem {
    private List<Order> orders;

    public OrderSystem(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Customer> topSpendingCustomers() {
        Map<Customer, Double> customerSpending = new HashMap<>();
        for (Order order : this.getOrders()) {
            double totalSpent = order.getProduct().stream().mapToDouble(product -> product.getPrice()).sum();
            customerSpending.merge(order.getCustomer(), totalSpent, Double::sum);
        }
        List<Customer> topSpendingCustomer = customerSpending.entrySet().stream().sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue())).map(Map.Entry::getKey).limit(3).collect(Collectors.toList());
        return topSpendingCustomer;
    }

    public Product mostPurchasedProduct() {
        Map<Product, Integer> purchasedProducts = new HashMap<>();
        for (Order order : this.getOrders()) {
            for (Product product : order.getProduct()) {
                purchasedProducts.put(product, purchasedProducts.getOrDefault(product, 0) + 1);
            }
        }
        return purchasedProducts.entrySet().stream().sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue())).map(Map.Entry::getKey).findFirst().orElse(null);
    }

    public double ordersPerCustomer() {
        Map<Customer, List<Order>> customerOrders = this.getOrders().stream().collect(Collectors.groupingBy(Order::getCustomer));
        int totalOrders = customerOrders.values().stream().mapToInt(List::size).sum();
        int totalCustomers = customerOrders.size();
        return totalCustomers == 0 ? 0 : (double) totalCustomers / totalOrders;
    }

    public double totalRevenue() {
        return this.getOrders().stream().flatMap(order -> order.getProduct().stream()).
                mapToDouble(Product::getPrice).sum();
    }

    public Map<Customer, Double> customerOrderSummary() {
        return this.getOrders().stream().collect(Collectors.groupingBy(Order::getCustomer,
                Collectors.summingDouble(order -> order.getProduct().stream().
                        mapToDouble(Product::getPrice).sum())));
    }

    public List<Order> expensivePurchases() {
        return this.getOrders().stream().filter(order -> order.getProduct().stream().
                mapToDouble(Product::getPrice).sum() > 1000).toList();
    }

    public List<Order> orderInLast30Day() {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDayAgo = today.minusDays(30);
        return this.getOrders().stream().filter((order -> order.getOrderDate().isAfter(thirtyDayAgo))).toList();
    }

    public static void main(String[] args) {
        Product p1 = new Product("001", "laptop", 1000);
        Product p2 = new Product("002", "phone", 500);
        Product p3 = new Product("003", "mouse", 100);
        Customer c1 = new Customer("101", "Guts");
        Customer c2 = new Customer("102", "Griffith");
        Customer c3 = new Customer("103", "Casca");
        Customer c4 = new Customer("104", "Serpico");
        Order o1 = new Order("201", c1, new ArrayList<>(Arrays.asList(p1, p2, p3)), LocalDate.now().minusDays(10));
        Order o2 = new Order("202", c2, new ArrayList<>(Arrays.asList(p3)), LocalDate.now().minusDays(40));
        Order o3 = new Order("203", c3, new ArrayList<>(Arrays.asList(p2, p3)), LocalDate.now().minusDays(15));
        Order o4 = new Order("204", c4, new ArrayList<>(Arrays.asList(p1, p3)), LocalDate.now().minusDays(15));
        OrderSystem oSystem = new OrderSystem(new ArrayList<>(Arrays.asList(o1, o2, o3, o4)));
        System.out.println(oSystem.topSpendingCustomers());
        System.out.println(oSystem.mostPurchasedProduct());
        System.out.println(oSystem.ordersPerCustomer());
        System.out.println(oSystem.totalRevenue());
        System.out.println(oSystem.customerOrderSummary());
        System.out.println(oSystem.expensivePurchases());
        System.out.println(oSystem.orderInLast30Day());
    }
}

