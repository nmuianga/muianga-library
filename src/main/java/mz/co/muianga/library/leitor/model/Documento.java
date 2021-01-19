package mz.co.muianga.library.leitor.model;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Documento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;

    @Column (name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column (name = "data_validade")
    private LocalDate dataValidade;

    @Column (nullable = false)
    private boolean vitalicio;

    @Enumerated (EnumType.STRING)
    @Column (name = "local_emissao", nullable = false)
    private Provincia localEmissao;

    @Column (name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @OneToOne (mappedBy = "documentoIdentificacao")
    private Leitor leitor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }
}