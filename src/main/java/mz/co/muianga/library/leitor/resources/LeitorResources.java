package mz.co.muianga.library.leitor.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mz.co.muianga.library.leitor.exception.ValidationException;
import mz.co.muianga.library.leitor.model.Leitor;
import mz.co.muianga.library.leitor.resources.dto.LeitorDTO;
import mz.co.muianga.library.leitor.resources.dto.ListLeitorDTO;
import mz.co.muianga.library.leitor.service.LeitorService;

@RestController
@RequestMapping("/leitores")
public class LeitorResources extends AbstractResources{

    @Autowired
    private LeitorService leitorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeitorDTO save(@RequestBody LeitorDTO leitorDTO) throws ValidationException {
        return map(leitorService.save(map(leitorDTO)));
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListLeitorDTO findAll() {
    	return map(leitorService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<LeitorDTO> findById(@PathVariable Long id) {
    	Leitor leitor = leitorService.findById(id);
    	
    	if (leitor == null) {
    		return new ResponseEntity<LeitorDTO>(HttpStatus.NOT_FOUND);
    	}
    	
    	return new ResponseEntity<LeitorDTO>(map(leitor), HttpStatus.OK);
    }
    
}
