package br.pucgoias.ads1253.turmas.leitura;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** Lado da leitura: não altera dados. */
@Service
@Transactional(readOnly = true)
public class TurmaConsultas {

    private final TurmaLeituraRepository leitura;

    public TurmaConsultas(TurmaLeituraRepository leitura) {
        this.leitura = leitura;
    }

    public List<ResumoTurma> resumos() {
        return leitura.resumos();
    }

    public List<OcupacaoTurma> painelCalculado() {
        return leitura.ocupacaoCalculada();
    }
}
