package mz.co.muianga.library.leitor.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mz.co.muianga.library.leitor.exception.ValidationException;
import mz.co.muianga.library.leitor.model.Documento;
import mz.co.muianga.library.leitor.model.Leitor;
import mz.co.muianga.library.leitor.model.Provincia;
import mz.co.muianga.library.leitor.repository.LeitorRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeitorServiceImplTest {

    @InjectMocks
    LeitorServiceImpl service;

    @Mock
    LeitorRepository leitorRepository;

    Leitor leitor;

    @BeforeEach
    void setUp() {
        leitor = buildLeitor();
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
    @DisplayName("Success")
    @Order(99)
    void save() throws ValidationException {
        Leitor leitor = buildLeitor();

        when(leitorRepository.save(any(Leitor.class))).thenReturn(leitor);

        Leitor expectedLeitor = service.save(leitor);
        long expectedId = 1L;

        Assertions.assertNotNull(expectedLeitor);
        Assertions.assertEquals(expectedLeitor.getId(), leitor.getId());
    }

    private Leitor buildLeitor() {
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

        return leitor;
    }

}