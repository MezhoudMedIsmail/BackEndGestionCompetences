package org.example.backendamine.Repository;

import org.example.backendamine.Entities.Theme;
import org.example.backendamine.Entities.TypeDepartement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Theme findByQuestionsId(long id);
    Theme findByDepartement(TypeDepartement departement);
}
