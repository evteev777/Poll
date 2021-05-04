package ru.evteev.poll.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email", referencedColumnName="username",
            foreignKey = @ForeignKey(name = "persons_users_username_fk"))
    User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    List<Answer> answerList;
}
