package br.pucgoias.ads1253.turmas.leitura;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PainelTurmaRepository extends Repository<PainelTurma, UUID> {

    PainelTurma save(PainelTurma painel);

    Optional<PainelTurma> findById(UUID turmaId);

    List<PainelTurma> findAllByOrderByNomeAsc();
}
