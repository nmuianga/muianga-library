package mz.co.muianga.library.leitor.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mz.co.muianga.library.leitor.model.Leitor;
import mz.co.muianga.library.leitor.service.LeitorService;

@RestController
@RequestMapping("/leitores")
public class LeitorResources {

    @Autowired
    private LeitorService leitorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Leitor save(@RequestBody Leitor leitor) {
        return leitorService.save(leitor);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Leitor> findAll() {
    	return leitorService.findAll();
    }
    
}
