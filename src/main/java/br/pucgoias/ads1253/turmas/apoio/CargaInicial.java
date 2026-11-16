package br.pucgoias.ads1253.turmas.apoio;

import br.pucgoias.ads1253.turmas.TurmaService;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Cria os dados do estudo: 100 turmas com capacidade para 30 estudantes
 * e quantidade variável de inscritos (de 0 a 20), totalizando 976 inscrições.
 */
@Component
public class CargaInicial {

    public static final int TURMAS = 100;

    private final TurmaService servico;

    public CargaInicial(TurmaService servico) {
        this.servico = servico;
    }

    /** @return identificador da primeira turma criada (Turma 001) */
    public UUID executar() {
        UUID primeira = null;
        for (int i = 1; i <= TURMAS; i++) {
            UUID turma = servico.abrir(String.format("Turma %03d", i), 30);
            for (int j = 1; j <= i % 21; j++) {
                servico.inscrever(turma, "estudante" + j + "@pucgoias.edu.br");
            }
            if (primeira == null) {
                primeira = turma;
            }
        }
        return primeira;
    }
}
