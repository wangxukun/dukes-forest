package org.xkidea.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
// TODO NamedQueries
public class Customer extends Person{
    private static final long serialVersionUID = -8032363575985772475L;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<CustomerOrder> customerOrderList;
    public Customer() {
        this.customerOrderList = new ArrayList<>();
        this.groupsList = new ArrayList<>();
    }

    public Customer(Integer id) {
        this.id = id;
        this.customerOrderList = new ArrayList<>();
        this.groupsList = new ArrayList<>();
    }

    public Customer(Integer id, @Size(min = 3, max = 50, message = "{person.firstname}") String firstname, @Size(min = 3, max = 100, message = "{person.lastname}") String lastname, @Pattern(regexp = ".+@.+\\\\.[a-z]+", message = "{person.email}") @Size(min = 3, max = 45, message = "{person.email}") String email, @Size(min = 3, max = 45, message = "{person.address}") String address, @Size(min = 3, max = 45, message = "{person.city}") String city) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.city = city;
        this.customerOrderList = new ArrayList<>();
        this.groupsList = new ArrayList<>();
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
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerOrderList, customer.customerOrderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerOrderList);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                '}';
    }
}
