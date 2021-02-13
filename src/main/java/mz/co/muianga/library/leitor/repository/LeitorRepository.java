package mz.co.muianga.library.leitor.repository;

import mz.co.muianga.library.leitor.model.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeitorRepository extends JpaRepository<Leitor, Long> {
}
