package org.example.backendamine.Repository;

import org.example.backendamine.Entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
