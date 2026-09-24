package com.revature.hibernate.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Customer entity.
 *
 * This plain Java class (POJO) has been turned into a Hibernate entity
 * by adding JPA annotations. Hibernate maps its fields to columns in a database
 * table and handles loading/saving instances automatically, so no need for
 * handwritten SQL
 */
@Entity // Marks this class as a JPA entity (a row in a table).
@Table(name = "customers")  // Optional: names the backing table (default would be "Customer").
public class Customer {

    /*
     * Primary key. @Id marks the field that uniquely identifies each row.
     * Because the field type is UUID, we tell Hibernate to generate the value
     * automatically (JPA 3.1+ standard) rather than assigning it by hand.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /*
     * @Column lets us customize the column name. Without it, Hibernate would
     * derive a name from the field (e.g. "firstName"). Providing explicit names
     * matches the typical snake_case convention used in SQL tables.
     */
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "years_as_customer")
    private int yearsAsCustomer;

    /*
     * The INVERSE side of the one-to-many relationship with Order.
     *
     * @OneToMany: one Customer row maps to many Order rows.
     * mappedBy = "customer": tells Hibernate this side does NOT own the foreign
     * key: the Order.customer field does. Hibernate uses that to keep the two
     * sides in sync and to know which table holds the FK.
     *
     * cascade = CascadeType.ALL: operations on a Customer (persist, merge,
     * remove, ...) are propagated to its orders. So removing a Customer also
     * removes its Orders -- Hibernate deletes the orders first, then the
     * customer, in the correct FK-safe order.
     *
     * We initialize the list to an empty ArrayList so it's never null.
     * (fetch defaults to LAZY for @OneToMany, so the orders are only loaded from
     * the DB when you actually access this list.)
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    /*
     * JPA REQUIRES a public/protected no-arg constructor. Hibernate uses it
     * (via reflection) when it instantiates entities it loads from the database.
     * Note: the no-arg instance has no id -- Hibernate fills the fields in when
     * loading, or assigns the id when a new record is saved.
     */
    public Customer(){}

    /*
     * A convenience constructor for creating a fully-populated Customer in code.
     */
    public Customer(UUID id, String firstName, String lastName, String address, int yearsAsCustomer) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.yearsAsCustomer = yearsAsCustomer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYearsAsCustomer() {
        return yearsAsCustomer;
    }

    public void setYearsAsCustomer(int yearsAsCustomer) {
        this.yearsAsCustomer = yearsAsCustomer;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getYearsAsCustomer() == customer.getYearsAsCustomer() && Objects.equals(getId(), customer.getId()) && Objects.equals(getFirstName(), customer.getFirstName()) && Objects.equals(getLastName(), customer.getLastName()) && Objects.equals(getAddress(), customer.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getAddress(), getYearsAsCustomer());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", yearsAsCustomer=" + yearsAsCustomer +
                '}';
    }
}
