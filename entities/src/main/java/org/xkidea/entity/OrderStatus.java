package org.xkidea.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ORDER_STATUS")
public class OrderStatus implements Serializable {
    private static final long serialVersionUID = 8625164415446262189L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "STATUS")
    @Size(min = 3, max = 45, message = "{order.status}")
    private String status;
    @Basic(optional = false)
    @Size(min = 0, max = 200, message = "Description has maximum of 200 characters")
    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderStatus")
    private List<CustomerOrder> customerOrderList;

    public OrderStatus() {
    }

    public OrderStatus(Integer id) {
        this.id = id;
    }

    public OrderStatus(Integer id, @Size(min = 3, max = 45, message = "{order.status}") String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<CustomerOrder> getCustomerOrderList() {
        return customerOrderList;
    }

    public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
        this.customerOrderList = customerOrderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(description, that.description) && Objects.equals(customerOrderList, that.customerOrderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, description, customerOrderList);
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                '}';
    }
}
