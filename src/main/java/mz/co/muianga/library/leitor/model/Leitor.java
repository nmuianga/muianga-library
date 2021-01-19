package mz.co.muianga.library.leitor.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Leitor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String nome;

    @Column (nullable = false)
    private String apelido;

    @Column (name = "data_nascimento")
    private LocalDate dataNascimento;
    private String genero;
    private String endereco;

    @Column (name = "local_nascimento")
    private Provincia localNascimento;

    @Column (nullable = false)
    private String telefone;

    @Column (name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @OneToOne
    @JoinColumn (name = "documento_id")
    private Documento documentoIdentificacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Provincia getLocalNascimento() {
        return localNascimento;
    }

    public void setLocalNascimento(Provincia localNascimento) {
        this.localNascimento = localNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Documento getDocumentoIdentificacao() {
        return documentoIdentificacao;
    }

    public void setDocumentoIdentificacao(Documento documentoIdentificacao) {
        this.documentoIdentificacao = documentoIdentificacao;
    }
}
