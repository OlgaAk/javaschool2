package javaee;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class UserEntity {
    @Id
    private String id;
    private String first_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
