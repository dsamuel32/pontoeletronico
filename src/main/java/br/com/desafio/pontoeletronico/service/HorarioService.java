package br.com.desafio.pontoeletronico.service;

import br.com.desafio.pontoeletronico.dominio.dto.HorarioDTO;
import br.com.desafio.pontoeletronico.dominio.entidade.Horario;
import br.com.desafio.pontoeletronico.negocio.ValidacaoHorario;
import br.com.desafio.pontoeletronico.repository.HorarioRepository;
import org.springframework.stereotype.Service;

@Service
public class HorarioService {

    private HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public HorarioDTO salvar(HorarioDTO horarioDTO) {
        Horario ultimoHorario = this.horarioRepository.findTopByMatriculaAndDataOrderByIdDesc(horarioDTO.getMatricula(), horarioDTO.getData());
        var validador = new ValidacaoHorario(ultimoHorario, horarioDTO.getHora(), horarioDTO.getData());

        validador.validar();

        Horario horario = new Horario(horarioDTO.getMatricula(), horarioDTO.getHora(), horarioDTO.getData(), horarioDTO.getTipoHorarioEnum());

        return salvar(horario);
    }

    private HorarioDTO salvar(Horario horario) {
        horario = this.horarioRepository.save(horario);
        return new HorarioDTO(horario.getId(), horario.getMatricula(), horario.getHora(), horario.getData(), horario.getTipoHorarioEnum());
    }
}