package org.xkidea.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMER_ORDER")
// TODO NamedQueries
public class CustomerOrder implements Serializable {
    private static final long serialVersionUID = 2227581004817459989L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Basic(optional = false)
    @DecimalMin(value = "0.1", message = "{c.order.amount}")
    @Column(name = "AMOUNT")
    private double amount; // 金额

    @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private OrderStatus orderStatus;

    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerOrder")
    private List<OrderDetail> orderDetailList;

    public CustomerOrder() {
    }

    public CustomerOrder(Integer id) {
        this.id = id;
    }

    public CustomerOrder(Integer id, @NotNull Date dateCreated, @DecimalMin(value = "0.1", message = "{c.order.amount}") double amount) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder that = (CustomerOrder) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(id, that.id) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(orderStatus, that.orderStatus) && Objects.equals(customer, that.customer) && Objects.equals(orderDetailList, that.orderDetailList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreated, amount, orderStatus, customer, orderDetailList);
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                '}';
    }
}
