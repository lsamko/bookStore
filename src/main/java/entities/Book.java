package entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@NoArgsConstructor
public class Book {

    @Id
    private Long id;

    @ManyToMany()
    @JoinTable(
        name = "book_user",
        joinColumns = {@JoinColumn(name = "book_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;
    @Column(unique = true, nullable = false, name = "name")
    private String name;
}
