package mz.co.muianga.library.leitor.resources;

import mz.co.muianga.library.leitor.exception.ValidationException;
import mz.co.muianga.library.leitor.resources.dto.LeitorDTO;
import mz.co.muianga.library.leitor.service.LeitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
