package com.revature.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

/**
 * Order entity.
 *
 * An Order belongs to exactly one Customer, and a Customer can have many
 * Orders; a classic one-to-many relationship. Here we map the many
 * (owning) side of that relationship.
 *
 * Notice this class references a Customer object, not a raw UUID.
 * That is the whole point of an ORM: we model real object references and
 * Hibernate manages the underlying foreign-key column in the database.
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /*
     * @ManyToOne:  many Order rows point to one Customer row.
     * @JoinColumn: names the foreign-key column in the ORDERS table that holds
     *              the customer's id. We set it non-nullable because an order
     *              always has a customer.
     *
     * This is the OWNING side of the relationship, meaning it owns the foreign key,
     * which is why Customer.orders uses mappedBy to point back here.
     * (fetch defaults to EAGER for @ManyToOne, which is fine here.)
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "product")
    private String product;

    @Column(name = "cost")
    private double cost;

    /*
     * JPA REQUIRES a public/protected no-arg constructor.
     */
    public Order(){}

    /*
     * Convenience constructor for building an Order in code. The customer is
     * passed as an object reference rather than an id.
     */
    public Order(UUID id, Customer customer, String product, double cost) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.cost = cost;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(getCost(), order.getCost()) == 0 && Objects.equals(getId(), order.getId()) && Objects.equals(getCustomer(), order.getCustomer()) && Objects.equals(getProduct(), order.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getProduct(), getCost());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", product='" + product + '\'' +
                ", cost=" + cost +
                '}';
    }
}
