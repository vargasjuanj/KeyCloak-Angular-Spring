package frm.utn.entities;

import javax.persistence.Entity;

import java.io.Serializable;
import java.util.Objects;

@Entity  //En este caso se persistira en una base de datos embebida de sql llamada H2, es una dependencia.
public class Product extends Base implements Serializable {

    private String name;
    private  double price;

    public Product() {
    }

    public Product( String name, double price) {
        this.name = name;
        this.price = price;
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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice());
    }
}