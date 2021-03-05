package mz.co.muianga.library.leitor.service;

import java.util.List;
import mz.co.muianga.library.leitor.exception.ValidationException;
import mz.co.muianga.library.leitor.model.Leitor;

public interface LeitorService {

	Leitor save(Leitor leitor) throws ValidationException;
	
    List<Leitor> findAll();
    
    Leitor findById(Long id);
    
}
