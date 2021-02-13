package mz.co.muianga.library.leitor.resources;

import mz.co.muianga.library.leitor.model.Leitor;
import mz.co.muianga.library.leitor.service.LeitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leitores")
public class LeitorResources {

    @Autowired
    private LeitorService leitorService;

    @GetMapping
    public String hello() {
        return "Hello Nino";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Leitor save(@RequestBody Leitor leitor) {
        return leitorService.save(leitor);
    }
}
