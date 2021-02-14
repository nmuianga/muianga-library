package mz.co.muianga.library.leitor.service;

import java.time.LocalDateTime;
import java.util.List;
import mz.co.muianga.library.leitor.exception.ValidationException;
import mz.co.muianga.library.leitor.model.Leitor;
import mz.co.muianga.library.leitor.repository.LeitorRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeitorServiceImpl implements LeitorService {

    private LeitorRepository leitorRepository;

    @Override
    public Leitor save(Leitor leitor) throws ValidationException {
        if (leitor == null) {
            throw new ValidationException("The reader can't be null");
        }

        if (leitor.getNome() == null || StringUtils.isBlank(leitor.getNome())) {
            throw  new ValidationException("The name mustn't be null");
        }

        if (leitor.getApelido() == null || StringUtils.isBlank(leitor.getApelido())) {
            throw  new ValidationException("The surename mustn't be null");
        }

        if (leitor.getGenero() == null || StringUtils.isBlank(leitor.getGenero())) {
            throw new ValidationException("Please, type a valid gender");
        }

        if (leitor.getGenero() != null && !StringUtils.isBlank(leitor.getGenero())
                && !StringUtils.equals("Masculino", leitor.getGenero())
                && !StringUtils.equals("Feminino", leitor.getGenero())) {

            throw new ValidationException("Please, type a valid gender");
        }

        if (leitor.getDocumentoIdentificacao() == null) {
            throw new ValidationException("The document mustn't be null");
        }

        if (leitor.getDocumentoIdentificacao().getNumero() == null || StringUtils.isBlank(leitor.getDocumentoIdentificacao().getNumero())) {
            throw new ValidationException("The document number mustn't be null");
        }

        if (leitor.getDocumentoIdentificacao().getDataEmissao() == null) {
            throw new ValidationException("The issue date of the document mustn't be null");
        }

        if (!leitor.getDocumentoIdentificacao().isVitalicio() && leitor.getDocumentoIdentificacao().getDataValidade() == null) {
            throw new ValidationException("Please, type the document validity date");
        }

        if (leitor.getDocumentoIdentificacao().isVitalicio()) {
            leitor.getDocumentoIdentificacao().setDataValidade(null);
        }

         if (leitor.getDocumentoIdentificacao().getLocalEmissao() == null) {
             throw new ValidationException("Please, type the document issue place");
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
