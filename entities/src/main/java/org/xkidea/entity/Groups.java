package org.xkidea.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "GROUPZ") // GROUPS change GROUPZ， GROUPS是mysql保留字
// TODO @NamedQueries
public class Groups implements Serializable {

    private static final long serialVersionUID = -6448091993548590089L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50, message = "{groups.name}")
    @Column(name = "NAME")
    private String name;
    @Size(max = 300, message = "{groups.description}")
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy = "groupsList")
    @XmlTransient
    private List<Person> personList;

    public Groups() {
    }

    public Groups(Integer id) {
        this.id = id;
    }

    public Groups(Integer id, @NotNull @Size(min = 1, max = 50, message = "{groups.name}") String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) o;
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
        return "Groups{" +
                "id=" + id +
                '}';
    }
}
