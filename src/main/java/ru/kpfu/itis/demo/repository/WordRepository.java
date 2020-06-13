package ru.kpfu.itis.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.demo.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long > {
}
