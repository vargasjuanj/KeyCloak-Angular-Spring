package frm.utn.ecomapp.entities;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected long id;
    @Temporal(TemporalType.TIMESTAMP)
   protected Date date= new Date();
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
