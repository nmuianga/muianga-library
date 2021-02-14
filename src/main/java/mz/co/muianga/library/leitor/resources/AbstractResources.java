package mz.co.muianga.library.leitor.resources;

import java.util.List;
import java.util.stream.Collectors;

import mz.co.muianga.library.leitor.model.Documento;
import mz.co.muianga.library.leitor.model.Leitor;
import mz.co.muianga.library.leitor.resources.dto.DocumentoDTO;
import mz.co.muianga.library.leitor.resources.dto.LeitorDTO;

public abstract class AbstractResources {

    protected LeitorDTO map(Leitor leitor) {
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

    protected DocumentoDTO map(Documento documento) {
        DocumentoDTO documentoDTO = new DocumentoDTO();

        documentoDTO.setNumero(documento.getNumero());
        documentoDTO.setDataEmissao(documento.getDataEmissao());
        documentoDTO.setDataValidade(documento.getDataValidade());
        documentoDTO.setLocalEmissao(documento.getLocalEmissao());
        documentoDTO.setVitalicio(documento.isVitalicio());

        return documentoDTO;
    }

    protected Leitor map(LeitorDTO leitorDTO) {
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

    protected Documento map(DocumentoDTO documentoDTO) {
        Documento documento = new Documento();

        documento.setNumero(documentoDTO.getNumero());
        documento.setDataEmissao(documentoDTO.getDataEmissao());
        documento.setDataValidade(documentoDTO.getDataValidade());
        documento.setLocalEmissao(documentoDTO.getLocalEmissao());
        documento.setVitalicio(documentoDTO.isVitalicio());

        return documento;
    }
    
    protected List<LeitorDTO> map(List<Leitor> leitores) {
    	
    	return leitores.stream()
	    	.map(this::map)
	    	.collect(Collectors.toList());
    	
    }
}
