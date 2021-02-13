package mz.co.muianga.library.leitor.service;

import java.time.LocalDateTime;
import java.util.List;

import mz.co.muianga.library.leitor.model.Leitor;
import mz.co.muianga.library.leitor.repository.LeitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeitorServiceImpl implements LeitorService {

    private LeitorRepository leitorRepository;

    @Override
    public Leitor save(Leitor leitor) {
        if (leitor == null) {
            throw new IllegalArgumentException("The reader can't be null");
        }

        if (leitor.getNome() == null || leitor.getNome().isBlank()) {
            throw  new IllegalArgumentException("The name mustn't be null");
        }

        if (leitor.getApelido() == null || leitor.getApelido().isBlank()) {
            throw  new IllegalArgumentException("The surename mustn't be null");
        }

        if (leitor.getGenero() != null && !leitor.getGenero().isBlank()) {

        }

        leitor.setDataCadastro(LocalDateTime.now());
        leitor.getDocumentoIdentificacao().setDataCadastro(LocalDateTime.now());
        leitor.getDocumentoIdentificacao().setLeitor(leitor);

        return leitorRepository.save(leitor);
    }

    @Autowired
    public void setLeitorRepository(LeitorRepository leitorRepository) {
        this.leitorRepository = leitorRepository;
    }

	@Override
	public List<Leitor> findAll() {
		return this.leitorRepository.findAll();
	}
}
