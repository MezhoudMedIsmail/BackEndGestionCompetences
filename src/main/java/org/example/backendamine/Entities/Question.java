package org.example.backendamine.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String text;

    @OneToMany(cascade = CascadeType.ALL)
    List<Reponse> reponse;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

}
