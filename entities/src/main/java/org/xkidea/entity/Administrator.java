package org.xkidea.entity;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Administrator extends Person{
    private static final long serialVersionUID = 1222267518799491672L;

    public Administrator() {
        this.groupsList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Administrator)) {
            return false;
        }
        Administrator other = (Administrator) o;
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
        return "com.forest.entity.Administrator[ id=" + id + " ]";
    }
}
