package ru.itis.springbootrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootrest.models.HelpMessage;

public interface HelpRepository extends JpaRepository<HelpMessage, Long> {
}
