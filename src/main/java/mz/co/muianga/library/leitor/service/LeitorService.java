package mz.co.muianga.library.leitor.service;

import java.util.List;

import mz.co.muianga.library.leitor.model.Leitor;

public interface LeitorService {

    Leitor save(Leitor leitor);
    
    List<Leitor> findAll();
}
