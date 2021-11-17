package entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    @Column(unique = true, nullable = false, name = "first_name")
    private String firstName;

    @Column(unique = true, nullable = false, name = "last_name")
    private String lastName;

    @ManyToMany(mappedBy = "users")
    private List<Book> books;
}
