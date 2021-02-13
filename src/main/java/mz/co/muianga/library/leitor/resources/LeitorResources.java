package mz.co.muianga.library.leitor.resources;

import mz.co.muianga.library.leitor.model.Documento;
import mz.co.muianga.library.leitor.model.Leitor;
import mz.co.muianga.library.leitor.resources.dto.DocumentoDTO;
import mz.co.muianga.library.leitor.resources.dto.LeitorDTO;
import mz.co.muianga.library.leitor.service.LeitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leitores")
public class LeitorResources {

    @Autowired
    private LeitorService leitorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeitorDTO save(@RequestBody LeitorDTO leitorDTO) {
        return map(leitorService.save(map(leitorDTO)));
    }

    private LeitorDTO map(Leitor leitor) {
        LeitorDTO leitorDTO = new LeitorDTO();

        leitorDTO.setApelido(leitor.getApelido());
        leitorDTO.setNome(leitor.getNome());
        leitorDTO.setEndereco(leitor.getEndereco());
        leitorDTO.setGenero(leitor.getGenero());
        leitorDTO.setDataNascimento(leitor.getDataNascimento());
        leitorDTO.setTelefone(leitor.getTelefone());
        leitorDTO.setLocalNascimento(leitor.getLocalNascimento());
        leitorDTO.setDocumentoDTO(map(leitor.getDocumentoIdentificacao()));

        return leitorDTO;
    }

    private DocumentoDTO map(Documento documento) {
        DocumentoDTO documentoDTO = new DocumentoDTO();

        documentoDTO.setNumero(documento.getNumero());
        documentoDTO.setDataEmissao(documento.getDataEmissao());
        documentoDTO.setDataValidade(documento.getDataValidade());
        documentoDTO.setLocalEmissao(documento.getLocalEmissao());
        documentoDTO.setVitalicio(documento.isVitalicio());

        return documentoDTO;
    }

    private Leitor map(LeitorDTO leitorDTO) {
        Leitor leitor = new Leitor();

        leitor.setApelido(leitorDTO.getApelido());
        leitor.setNome(leitorDTO.getNome());
        leitor.setDataNascimento(leitorDTO.getDataNascimento());
        leitor.setGenero(leitorDTO.getGenero());
        leitor.setEndereco(leitorDTO.getEndereco());
        leitor.setLocalNascimento(leitorDTO.getLocalNascimento());
        leitor.setTelefone(leitorDTO.getTelefone());
        leitor.setDocumentoIdentificacao(map(leitorDTO.getDocumentoDTO()));

        return leitor;
    }

    private Documento map(DocumentoDTO documentoDTO) {
        Documento documento = new Documento();

        documento.setNumero(documentoDTO.getNumero());
        documento.setDataEmissao(documentoDTO.getDataEmissao());
        documento.setDataValidade(documentoDTO.getDataValidade());
        documento.setLocalEmissao(documentoDTO.getLocalEmissao());
        documento.setVitalicio(documentoDTO.isVitalicio());

        return documento;
    }
}
