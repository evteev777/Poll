package ru.evteev.poll.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Role {

    @Id
    @Column(name = "username", nullable = false)
    String username;

    @Column(name = "authority", nullable = false)
    String authority;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(name = "authority_username"),
            inverseJoinColumns = @JoinColumn(name = "user_username"))
    List<User> userList;
}
