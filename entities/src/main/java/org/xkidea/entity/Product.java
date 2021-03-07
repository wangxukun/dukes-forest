package org.xkidea.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT")
// TODO NamedQueries
public class Product implements Serializable {
    private static final long serialVersionUID = -97976331315222020L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Size(min = 3, max = 45, message = "{product.name}")
    private String name;
    @Basic(optional = false)
    @DecimalMax(value = "9999.99", message = "{product.price.max}")
    @Column(name = "PRICE", nullable = false)
    private double price;
    @Basic(optional = false)
    @Size(min = 3, max = 145, message = "{product.description}")
    @Column(name = "DESCRIPTION", nullable = false, length = 45)
    private String description;
    @Size(min = 3, max = 45, message = "{product.img}")
    @Column(name = "IMG", length = 45)
    private String img;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "IMG_SRC")
    @XmlTransient
    private byte[] imgSrc;
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Category category;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, @Size(min = 3, max = 45, message = "{product.name}") String name, @DecimalMax(value = "9999.99", message = "{product.price.max}") double price, @Size(min = 3, max = 145, message = "{product.description}") String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public byte[] getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(byte[] imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                '}';
    }
}
