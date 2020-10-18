package br.com.desafio.pontoeletronico.dominio.dto;

public class BancoHoraDTO {

    private String totalHoraMesal;
    private String totalTrabalhado;
    private String horasDevidas;
    private String horasExtras;

    public BancoHoraDTO() { }

    public BancoHoraDTO(String totalHoraMesal, String totalTrabalhado, String horasDevidas, String horasExtras) {
        this.totalHoraMesal = totalHoraMesal;
        this.totalTrabalhado = totalTrabalhado;
        this.horasDevidas = horasDevidas;
        this.horasExtras = horasExtras;
    }

    public String getTotalHoraMesal() {
        return totalHoraMesal;
    }

    public String getTotalTrabalhado() {
        return totalTrabalhado;
    }

    public String getHorasDevidas() {
        return horasDevidas;
    }

    public void setHorasDevidas(String horasDevidas) {
        this.horasDevidas = horasDevidas;
    }

    public String getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(String horasExtras) {
        this.horasExtras = horasExtras;
    }
}
