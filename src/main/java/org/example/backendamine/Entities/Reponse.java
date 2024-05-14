package org.example.backendamine.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String reponse;
    @ManyToOne
    User user;
}
