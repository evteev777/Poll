package ru.evteev.poll.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "authorities")
@NoArgsConstructor
@Getter
@Setter
public class Authorities {

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
