package br.com.desafio.pontoeletronico.service;

import br.com.desafio.pontoeletronico.dominio.dto.BancoHoraDTO;
import br.com.desafio.pontoeletronico.dominio.dto.HorarioDTO;
import br.com.desafio.pontoeletronico.dominio.entidade.Horario;
import br.com.desafio.pontoeletronico.negocio.CalculoHora;
import br.com.desafio.pontoeletronico.negocio.ValidacaoHorario;
import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import br.com.desafio.pontoeletronico.negocio.utils.DataUtil;
import br.com.desafio.pontoeletronico.repository.HorarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public HorarioDTO salvar(HorarioDTO horarioDTO) {
        Horario ultimoHorario = this.horarioRepository.findTopByMatriculaAndDataOrderByIdDesc(horarioDTO.getMatricula(), horarioDTO.getData());
        var validador = new ValidacaoHorario(ultimoHorario, horarioDTO.getHora(), horarioDTO.getData(), horarioDTO.getTipoHorarioEnum());

        validador.validar();

        Horario horario = new Horario(horarioDTO.getMatricula(), horarioDTO.getHora(), horarioDTO.getData(), horarioDTO.getTipoHorarioEnum());

        return salvar(horario);
    }

    private HorarioDTO salvar(Horario horario) {
        horario = this.horarioRepository.save(horario);
        return new HorarioDTO(horario.getId(), horario.getMatricula(), horario.getHora(), horario.getData(), horario.getTipoHorarioEnum());
    }

    public HorarioDTO editar(HorarioDTO horarioDTO) {
        List<Horario> horarios = this.horarioRepository.findByMatriculaAndDataOrderByIdAsc(horarioDTO.getMatricula(), horarioDTO.getData());
        Integer indice = IntStream.range(0, horarios.size())
                                  .filter(it -> horarios.get(it).getId().equals(horarioDTO.getId()))
                                  .findFirst().orElse(0);
        Horario horarioAnterior = null;
        if (indice > 0) {
            horarioAnterior = horarios.get(indice - 1);
        }
        var validador = new ValidacaoHorario(horarioAnterior, horarioDTO.getHora(), horarioDTO.getData(), horarioDTO.getTipoHorarioEnum());

        validador.validar();
        Horario horario = new Horario(horarioDTO.getId(), horarioDTO.getMatricula(), horarioDTO.getHora(), horarioDTO.getData(), horarioDTO.getTipoHorarioEnum());
        return this.salvar(horario);
    }

    public Long calcularTotalTrabalhado(Long matricula, LocalDate data) {
        List<Horario> horarios = this.horarioRepository.findByMatriculaAndDataOrderByIdAsc(matricula, data);
        var calculoHora = new CalculoHora();
        return calculoHora.calcular(horarios);
    }

    public BancoHoraDTO calcularBancoHoras(Long matricula, Integer mes, Integer ano) {
        LocalDate dataInicio = DataUtil.getPrimeiroDiaMes(mes, ano);
        LocalDate dataFim = DataUtil.getUltimoDiaMes(mes, ano);

        List<Horario> horarios = this.horarioRepository.findByMatriculaBetweenData(matricula, dataInicio, dataFim);
        var calculoHora = new CalculoHora();

        return calculoHora.calcularBancoHoras(horarios, mes, ano);

    }
}
