package carshop.domain;



import javax.persistence.*;

/**
 * Created by nik on 4/24/17.
 */

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="author_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    long id;

    @Column(name="f_name")
    String firstName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
