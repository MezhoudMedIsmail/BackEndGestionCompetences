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
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String fileName;

    String fileType;
    @ManyToOne(cascade = CascadeType.ALL)
    User user;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    byte[] data;

}
