package mz.co.muianga.library.resources;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import mz.co.muianga.library.leitor.model.Documento;
import mz.co.muianga.library.leitor.model.Leitor;
import mz.co.muianga.library.leitor.model.Provincia;
import mz.co.muianga.library.leitor.resources.LeitorResources;
import mz.co.muianga.library.leitor.service.LeitorService;

@ExtendWith(MockitoExtension.class)
public class LeitorResourceTest {
	
	@Mock
	LeitorService leitorService;
	
	@InjectMocks
	LeitorResources leitorResources;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(leitorResources)
				.build();
	}
	
	@Test
	@DisplayName("Find all Readers")
	@Order(1)
	void findAllTest() throws Exception {
		List<Leitor> leitores = buildLeitores();
		
		when(leitorService.findAll()).thenReturn(leitores);
		
		mockMvc.perform(get("/leitores")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.items", Matchers.hasSize(2)));
	}
	
	 private List<Leitor> buildLeitores() {
	        Documento documento = new Documento();
	        documento.setId(1L);
	        documento.setVitalicio(false);
	        documento.setDataValidade(LocalDate.now());
	        documento.setNumero("12534");
	        documento.setLocalEmissao(Provincia.MAPUTO);
	        documento.setDataEmissao(LocalDate.now());
	        documento.setDataCadastro(LocalDateTime.now());

	        Leitor leitor = new Leitor();
	        leitor.setId(1L);
	        leitor.setNome("Nilvandro");
	        leitor.setApelido("Muianga");
	        leitor.setTelefone("823936154");
	        leitor.setEndereco("Habel Jafar");
	        leitor.setGenero("Masculino");
	        leitor.setLocalNascimento(Provincia.MAPUTO);
	        leitor.setDataNascimento(LocalDate.now());
	        leitor.setDataCadastro(LocalDateTime.now());
	        leitor.setDocumentoIdentificacao(documento);
	        documento.setLeitor(leitor);
	        
	        Documento documento1 = new Documento();
	        documento1.setId(1L);
	        documento1.setVitalicio(false);
	        documento1.setDataValidade(LocalDate.now());
	        documento1.setNumero("12534");
	        documento1.setLocalEmissao(Provincia.MAPUTO);
	        documento1.setDataEmissao(LocalDate.now());
	        documento1.setDataCadastro(LocalDateTime.now());
	        
	        Leitor leitor1 = new Leitor();
	        leitor1.setId(1L);
	        leitor1.setNome("Nilvandro");
	        leitor1.setApelido("Muianga");
	        leitor1.setTelefone("823936154");
	        leitor1.setEndereco("Habel Jafar");
	        leitor1.setGenero("Masculino");
	        leitor1.setLocalNascimento(Provincia.MAPUTO);
	        leitor1.setDataNascimento(LocalDate.now());
	        leitor1.setDataCadastro(LocalDateTime.now());
	        leitor1.setDocumentoIdentificacao(documento1);
	        documento1.setLeitor(leitor1);
	        
	        List<Leitor> leitores = new ArrayList<>();
	        leitores.add(leitor);
	        leitores.add(leitor1);

	        return leitores;
	    }

}
