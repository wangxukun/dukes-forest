package org.xkidea.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CATEGORY")
// TODO NamedQueries
public class Category implements Serializable {
    private static final long serialVersionUID = -6114726459895462589L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Size(min=3, max = 45,message = "{category.name}")
    @Column(name = "NAME", nullable = false, length = 45)
    private String name;
    @Basic(optional = false)
    @Size(min=3, max = 45,message = "{category.tags}")
    @Column(name = "TAGS", length = 45)
    private String tags;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Product> productList;

    public Category() {
    }

    public Category(Integer id) {
        this.id = id;
    }

    public Category(Integer id, @Size(min = 3, max = 45, message = "{category.name}") String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(tags, category.tags) && Objects.equals(productList, category.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tags, productList);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                '}';
    }
}
