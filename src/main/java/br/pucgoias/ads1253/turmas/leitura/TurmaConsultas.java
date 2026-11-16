package br.pucgoias.ads1253.turmas.leitura;

import br.pucgoias.ads1253.turmas.escrita.TurmaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** Lado da leitura: não altera dados. */
@Service
@Transactional(readOnly = true)
public class TurmaConsultas {

    private final TurmaRepository turmas;
    private final TurmaLeituraRepository leitura;

    public TurmaConsultas(TurmaRepository turmas, TurmaLeituraRepository leitura) {
        this.turmas = turmas;
        this.leitura = leitura;
    }

    public List<LinhaPainel> painelPeloAgregado() {
        return turmas.findAllByOrderByNomeAsc().stream()
                .map(t -> new LinhaPainel(t.getNome(), t.getCapacidade(), t.getInscritos().size(),
                        t.getCapacidade() - t.getInscritos().size()))
                .toList();
    }

    public List<ResumoTurma> resumos() {
        return leitura.resumos();
    }
}
