package br.com.desafio.pontoeletronico.negocio;

import br.com.desafio.pontoeletronico.dominio.entidade.Horario;
import br.com.desafio.pontoeletronico.dominio.enums.TipoHorarioEnum;
import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ValidacaoHorarioTest {

    private LocalDate data = LocalDate.of(2020, 9, 16);

    @Test
    @DisplayName("Deve retornar ValidacaoNegocioException quando hora informada menor que hora informada anterior")
    void deveRetornarValidacaoNegocioExceptionQuandoHoraInformadaMenorQueInformadaAnterior() {
        var validacao = new ValidacaoHorario(new Horario(1L,"09:00",data, TipoHorarioEnum.ENTRADA),
                                        "08:00", data,
                                             TipoHorarioEnum.SAIDA_ALMOCO);
        assertThrows(ValidacaoNegocioException.class, () -> validacao.validar());
    }

    @Test
    @DisplayName("Deve retornar ValidacaoNegocioException quando nao existe horario anterior e tipo informado diferente ENTRADA")
    void deveRetornarValidacaoNegocioExceptionQuandoNaoExisteHorarioAnteriorETipoInformadoDiferenteEntrada() {
        var validacao = new ValidacaoHorario(null,
                                        "08:00",
                                         data,
                                        TipoHorarioEnum.SAIDA_ALMOCO);
        assertThrows(ValidacaoNegocioException.class, () -> validacao.validar());
    }

    @Test
    @DisplayName("Deve retornar ValidacaoNegocioException quando horario anterior tipo SAIDA_ALMOCO e tipo informado diferente RETORNO_ALMOCO")
    void deveRetornarValidacaoNegocioExceptionQuandoHorarioAnteriorSaidaAlmocoETipoInformadoDiferenteRetornoAlmoco() {
        var validacao = new ValidacaoHorario(new Horario(1L,"09:00",data, TipoHorarioEnum.SAIDA_ALMOCO),
                                        "13:00",
                                             data,
                                             TipoHorarioEnum.SAIDA);
        assertThrows(ValidacaoNegocioException.class, () -> validacao.validar());
    }

    @Test
    @DisplayName("Deve retornar ValidacaoNegocioException quando data informada pertence ao fim de semana")
    void deveRetornarValidacaoNegocioExceptionQuandoDataInformadaPertenceaAoFimSemana() {
        var validacao = new ValidacaoHorario(new Horario(1L,"09:00", data, TipoHorarioEnum.ENTRADA),
                                        "08:00", LocalDate.of(2020, 9, 17),
                                             TipoHorarioEnum.SAIDA_ALMOCO);
        assertThrows(ValidacaoNegocioException.class, () -> validacao.validar());
    }

    @Test
    @DisplayName("Deve retornar ValidacaoNegocioException quando hora almoco menor uma hora")
    void deveRetornarValidacaoNegocioExceptionQuandoHoraAlmocoMenorUmaHora() {
        var validacao = new ValidacaoHorario(new Horario(1L,"12:00", data, TipoHorarioEnum.SAIDA_ALMOCO),
                                       "12:20",
                                            data,
                                            TipoHorarioEnum.RETORNO_ALMOCO);
        assertThrows(ValidacaoNegocioException.class, () -> validacao.validar());
    }

}