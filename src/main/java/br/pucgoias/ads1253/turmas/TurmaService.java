package br.pucgoias.ads1253.turmas;

import br.pucgoias.ads1253.turmas.dominio.RegraDeNegocioException;
import br.pucgoias.ads1253.turmas.dominio.Turma;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Serviço único: reúne as operações que alteram dados e as que apenas consultam.
 * É o ponto de partida do estudo dirigido.
 */
@Service
public class TurmaService {

    private final TurmaRepository turmas;

    public TurmaService(TurmaRepository turmas) {
        this.turmas = turmas;
    }

    @Transactional
    public UUID abrir(String nome, int capacidade) {
        Turma turma = Turma.abrir(nome, capacidade);
        turmas.save(turma);
        return turma.getId();
    }

    @Transactional
    public void inscrever(UUID turmaId, String email) {
        Turma turma = buscar(turmaId);
        turma.inscrever(email);
        turmas.save(turma);
    }

    @Transactional(readOnly = true)
    public List<LinhaPainel> painel() {
        return turmas.findAllByOrderByNomeAsc().stream()
                .map(t -> new LinhaPainel(t.getNome(), t.getCapacidade(), t.getInscritos().size(),
                        t.getCapacidade() - t.getInscritos().size()))
                .toList();
    }

    @Transactional(readOnly = true)
    public int totalDeInscritos(UUID turmaId) {
        return buscar(turmaId).getInscritos().size();
    }

    private Turma buscar(UUID turmaId) {
        return turmas.findById(turmaId)
                .orElseThrow(() -> new RegraDeNegocioException("Turma não encontrada: " + turmaId));
    }
}
