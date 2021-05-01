package ru.evteev.poll.entity;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

    @Email
    @Column(name = "email")
    String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Poll> pollList;
}
