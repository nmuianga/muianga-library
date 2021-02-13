package mz.co.muianga.library.leitor.resources.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import mz.co.muianga.library.leitor.model.Provincia;

public class LeitorDTO {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("apelido")
    private String apelido;

    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    @JsonProperty("genero")
    private String genero;

    @JsonProperty("endereco")
    private String endereco;

    @JsonProperty("local_nascimento")
    private Provincia localNascimento;

    @JsonProperty("telefone")
    private String telefone;

    @JsonProperty("documento_identificacao")
    private DocumentoDTO documentoDTO;

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

    public DocumentoDTO getDocumentoDTO() {
        return documentoDTO;
    }

    public void setDocumentoDTO(DocumentoDTO documentoDTO) {
        this.documentoDTO = documentoDTO;
    }
}
