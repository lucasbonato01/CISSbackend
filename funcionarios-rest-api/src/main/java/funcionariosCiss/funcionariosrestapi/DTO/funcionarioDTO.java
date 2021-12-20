package funcionariosCiss.funcionariosrestapi.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import java.time.LocalDate;


@Data
@Entity
public class funcionarioDTO {


    @Id
    @GeneratedValue
    private Integer idFuncionario;

    @Max(value = 30, message = "O número de caracter do nome não pode exeder à 50 caracteres")
    @Column(nullable = false)
    private String nome;

    @Max(value = 50, message = "O número de caracter do sobrenome não pode exeder à 50 caracteres")
    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dtNascimento;

    @Column(unique = true)
    private String email;

    @Column
    private String numPis;

}