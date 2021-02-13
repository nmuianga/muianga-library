package mz.co.muianga.library.leitor.resources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import mz.co.muianga.library.leitor.model.Provincia;

public class DocumentoDTO {

    private String numero;

    @JsonProperty("data_emissao")
    private LocalDate dataEmissao;

    @JsonProperty("data_validade")
    private LocalDate dataValidade;
    private boolean vitalicio;

    @JsonProperty("local_emissao")
    private Provincia localEmissao;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public boolean isVitalicio() {
        return vitalicio;
    }

    public void setVitalicio(boolean vitalicio) {
        this.vitalicio = vitalicio;
    }

    public Provincia getLocalEmissao() {
        return localEmissao;
    }

    public void setLocalEmissao(Provincia localEmissao) {
        this.localEmissao = localEmissao;
    }
}
