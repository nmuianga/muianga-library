package mz.co.muianga.library.leitor.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mz.co.muianga.library.leitor.exception.ValidationException;
import mz.co.muianga.library.leitor.model.Documento;
import mz.co.muianga.library.leitor.model.Leitor;
import mz.co.muianga.library.leitor.model.Provincia;
import mz.co.muianga.library.leitor.repository.LeitorRepository;

@ExtendWith(MockitoExtension.class)
class LeitorServiceImplTest {

    @InjectMocks
    LeitorServiceImpl service;

    @Mock
    LeitorRepository leitorRepository;

    Leitor leitor;
    
    private static final Long LEITOR_ID = 1L;

    @BeforeEach
    void setUp() {
        leitor = buildLeitores().get(0);
    }

    @Test
    @DisplayName("Leitor null")
    @Order(1)
    void leitorNull() {
        leitor = null;

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Leitor without name")
    @Order(2)
    void withoutName() throws ValidationException {
        leitor.setNome(null);

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Leitor with empty/blank name")
    @Order(3)
    void withBlankName() throws ValidationException {
        leitor.setNome("");

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Leitor without apelido")
    @Order(4)
    void withoutApelido() throws ValidationException {
        leitor.setApelido(null);

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Leitor with empty/blank apelido")
    @Order(5)
    void withBlankApelido() throws ValidationException {
        leitor.setApelido("");

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Leitor without genero")
    @Order(6)
    void withoutGenero() throws ValidationException {
        leitor.setGenero(null);

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Leitor with empty/blank genero")
    @Order(7)
    void withBlankGenero() throws ValidationException {
        leitor.setGenero("");

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Leitor with genero different of Masculino/Feminino")
    @Order(8)
    void withGeneroNorMasculinoFeminino() throws ValidationException {
        leitor.setGenero("Feminin");

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Leitor without document")
    @Order(9)
    void withoutDocumento() throws ValidationException {
        leitor.setDocumentoIdentificacao(null);

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Documento without numero")
    @Order(10)
    void documentWithoutNumber() throws ValidationException {
        leitor.getDocumentoIdentificacao().setNumero(null);

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Documento with Blank/Empty number")
    @Order(11)
    void documentwithBlankNumber() throws ValidationException {
        leitor.getDocumentoIdentificacao().setNumero("");

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Documento without issue date")
    @Order(12)
    void documentwithoutIssueDate() throws ValidationException {
        leitor.getDocumentoIdentificacao().setDataEmissao(null);

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Documento not vitalicio without validity date")
    @Order(13)
    void documentVitalicioWithoutValidityDate() throws ValidationException {
        leitor.getDocumentoIdentificacao().setVitalicio(false);
        leitor.getDocumentoIdentificacao().setDataValidade(null);

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }

    @Test
    @DisplayName("Documento vitalicio")
    @Order(14)
    void documentVitalicio() throws ValidationException {
        leitor.getDocumentoIdentificacao().setVitalicio(true);
        leitor.getDocumentoIdentificacao().setDataValidade(null);

        Assertions.assertEquals(null, leitor.getDocumentoIdentificacao().getDataValidade());
    }

    @Test
    @DisplayName("Documento without issue place")
    @Order(15)
    void documentWithoutIssuepalce() throws ValidationException {
        leitor.getDocumentoIdentificacao().setLocalEmissao(null);

        Assertions.assertThrows(ValidationException.class, () -> service.save(leitor));
    }
    
    @Test
    @DisplayName("Find all readers")
    @Order(17)
    void findAll() {
    	List<Leitor> actualReaders = buildLeitores();
    	
    	when(leitorRepository.findAll()).thenReturn(actualReaders);
    	
    	List<Leitor> expectedReaders = service.findAll();
    	
    	assertNotNull(expectedReaders);
    	assertTrue(expectedReaders.size() > 0);
    	assertEquals(expectedReaders.size(), actualReaders.size());
    }
    
    @Test
    @DisplayName("Find Reader by Id")
    @Order(18)
    void findById() {
    	leitor.setId(LEITOR_ID);
    	
    	when(leitorRepository.findById(any(Long.class))).thenReturn(Optional.of(leitor));
    	
    	Leitor actualLeitor = service.findById(LEITOR_ID);
    	
    	assertNotNull(actualLeitor);
    	assertEquals(LEITOR_ID, actualLeitor.getId());
    	assertEquals(LEITOR_ID, leitor.getId());
    }
    
    @Test
    @DisplayName("Reader not found")
    @Order(19)
    void readerNotFound() {
    	when(leitorRepository.findById(any(Long.class))).thenReturn(Optional.empty());
    	
    	Leitor leitorExpected = service.findById(LEITOR_ID);
    	
    	assertNull(leitorExpected);
    }

    @Test
    @DisplayName("Success")
    @Order(99)
    void save() throws ValidationException {
        leitor = buildLeitores().get(0);

        when(leitorRepository.save(any(Leitor.class))).thenReturn(leitor);

        Leitor expectedLeitor = service.save(leitor);

        Assertions.assertNotNull(expectedLeitor);
        Assertions.assertEquals(expectedLeitor.getId(), leitor.getId());
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