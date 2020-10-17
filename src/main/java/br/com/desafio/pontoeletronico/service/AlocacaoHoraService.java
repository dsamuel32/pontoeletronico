package br.com.desafio.pontoeletronico.service;

import br.com.desafio.pontoeletronico.dominio.dto.AlocacaoHoraDTO;
import br.com.desafio.pontoeletronico.dominio.dto.TotalAlocacaoDTO;
import br.com.desafio.pontoeletronico.dominio.entidade.AlocacaoHora;
import br.com.desafio.pontoeletronico.negocio.ValidacaoAlocacaoHora;
import br.com.desafio.pontoeletronico.negocio.utils.HoraUtil;
import br.com.desafio.pontoeletronico.repository.AlocacaoHoraRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AlocacaoHoraService {

    private final AlocacaoHoraRepository alocacaoHoraRepository;
    private final HorarioService horarioService;

    public AlocacaoHoraService(AlocacaoHoraRepository alocacaoHoraRepository, HorarioService horarioService) {
        this.alocacaoHoraRepository = alocacaoHoraRepository;
        this.horarioService = horarioService;
    }

    public AlocacaoHoraDTO alocar(AlocacaoHoraDTO alocacaoHoraDTO) {
        var totalTrabalhado = this.horarioService.calcularTotalTrabalhado(alocacaoHoraDTO.getMatricula(), alocacaoHoraDTO.getData());
        var totalAlocado = this.alocacaoHoraRepository.recuperarTotalAlocado(alocacaoHoraDTO.getMatricula(), alocacaoHoraDTO.getData()).orElse(0);
        var tempoAlocado = HoraUtil.converterSegundos(alocacaoHoraDTO.getHoras());
        totalAlocado += tempoAlocado.intValue();
        var validacao = new ValidacaoAlocacaoHora(totalTrabalhado, totalAlocado.longValue());
        validacao.validar();
        return this.salvar(alocacaoHoraDTO.getMatricula(), alocacaoHoraDTO.getCodigoProjeto(), tempoAlocado, alocacaoHoraDTO.getData());
    }

    private AlocacaoHoraDTO salvar(Long matricula, Long codigoProjeto, Long segundos, LocalDate data) {
        var alocacaoHora = this.alocacaoHoraRepository.save(new AlocacaoHora(matricula, segundos, data, codigoProjeto));
        var horaFormadata = HoraUtil.converterParaString(segundos);
        return new AlocacaoHoraDTO(alocacaoHora.getId(), matricula, codigoProjeto, horaFormadata, data);
    }
}
