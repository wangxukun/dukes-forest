package org.xkidea.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSON")
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
        @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
        @NamedQuery(name = "Person.findByFirstname", query = "SELECT p FROM Person p WHERE p.firstname = :firstname"),
        @NamedQuery(name = "Person.findByLastname", query = "SELECT p FROM Person p WHERE p.lastname = :lastname"),
        @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email"),
        @NamedQuery(name = "Person.findByAddress", query = "SELECT p FROM Person p WHERE p.address = :address"),
        @NamedQuery(name = "Person.findByCity", query = "SELECT p FROM Person p WHERE p.city = :city")
})
public class Person implements Serializable {

    private static final long serialVersionUID = -8273379732498451012L;
    /**
     * @JoinTable : 指定关联的映射。 它应用于关联的拥有方。
     * 联接表通常用于多对多和单向一对多关联的映射中。 它也可以用来映射双向多对一。
     */
    @JoinTable(name = "PERSON_GROUPZ", joinColumns = {
            @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL")}, inverseJoinColumns = {
            @JoinColumn(name = "GROUPZ_ID", referencedColumnName = "ID")})
    @ManyToMany
    protected List<Groups> groupsList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    protected Integer id;
    @Basic(optional = false)
    @Size(min = 3, max = 50, message = "{person.firstname}")
    @Column(name = "FIRSTNAME")
    protected String firstname;
    @Basic(optional = false)
    @Size(min = 3, max = 100, message = "{person.lastname}")
    @Column(name = "LASTNAME")
    protected String lastname;
    @Pattern(regexp = ".+@.+\\.[a-z]+", message = "{person.email}")
    @Size(min = 3, max = 45, message = "{person.email}")
    @Basic(optional = false)
    @Column(name = "EMAIL")
    protected String email;
    @Basic(optional = false)
    @Size(min = 3, max = 45, message = "{person.address}")
    @Column(name = "ADDRESS")
    protected String address;
    @Basic(optional = false)
    @Size(min = 3, max = 45, message = "{person.city}")
    @Column(name = "CITY")
    protected String city;
    @Basic(optional = false)
    @Size(min = 7, max = 100, message = "{person.password}")
    @Column(name = "PASSWORD")
    protected String password;


    public Person() {
        this.groupsList = new ArrayList<>();
    }

    public Person(Integer id) {
        this.id = id;
        this.groupsList = new ArrayList<>();
    }

    public Person(Integer id,
                  @Size(min = 3, max = 50, message = "{person.firstname}") String firstname,
                  @Size(min = 3, max = 100, message = "{person.lastname}") String lastname,
                  @Pattern(regexp = ".+@.+\\\\.[a-z]+", message = "{person.email}")
                  @Size(min = 3, max = 45, message = "{person.email}") String email,
                  @Size(min = 3, max = 45, message = "{person.address}") String address,
                  @Size(min = 3, max = 45, message = "{person.city}") String city) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.city = city;
        this.groupsList = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Add XmlTransient annotation to this field for security reasons.
     * @return the password
     */
    @XmlTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person)){
            return false;
        }
        Person other = (Person) o;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals((other.id)))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                '}';
    }
}
