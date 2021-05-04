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
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username", nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "BIT", length = 1)
    boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_username"),
            inverseJoinColumns = @JoinColumn(name = "authority_username"))
    List<Role> authorityList;
}
