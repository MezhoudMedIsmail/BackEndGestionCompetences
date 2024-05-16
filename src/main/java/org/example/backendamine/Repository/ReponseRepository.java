package org.example.backendamine.Repository;

import org.example.backendamine.Entities.Question;
import org.example.backendamine.Entities.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReponseRepository extends JpaRepository<Reponse, Long> {
}
