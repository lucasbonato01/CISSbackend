package funcionariosCiss.funcionariosrestapi.Controller;

import funcionariosCiss.funcionariosrestapi.DTO.funcionarioDTO;
import funcionariosCiss.funcionariosrestapi.Interface.funcionarioInterface;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //aplicacao Rest
@RequestMapping("api")
public class funcionariosController {


    @Autowired //Injetando
    private funcionarioInterface FuncionarioInterface;


    @GetMapping(path = "/status")
    public String check() {
        return "online";
    }

    @GetMapping(path = "/funcionario/{id_funcionario}")
    public ResponseEntity<funcionarioDTO> consultar(@PathVariable("id_funcionario") Integer idFuncionario) {
        return FuncionarioInterface.findById(idFuncionario)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/funcionario/adicionar")
    public Object adicionar(@RequestBody funcionarioDTO funcionario, validarController validar) {
        if (validar.validarEmail(funcionario.getEmail())) {
            return FuncionarioInterface.save(funcionario);
        } else {
            throw new ResourceAccessException("");
        }

    }


    @GetMapping("/funcionarios")
    public List<funcionarioDTO> buscarFuncionarios() {
        return (List<funcionarioDTO>) this.FuncionarioInterface.findAll();
    }

    @PutMapping("/funcionario/editar/{id_funcionario}")
    public ResponseEntity<funcionarioDTO> editarFuncionario(
            @PathVariable(value = "id_funcionario") Integer idFuncionario, @Validated @RequestBody funcionarioDTO funcionarios)
            throws ResourceNotFoundException {

        funcionarioDTO funcionario = FuncionarioInterface.findById(idFuncionario).orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado pelo id :: " + idFuncionario));
        funcionario.setNome(funcionarios.getNome());
        funcionario.setSobrenome(funcionarios.getSobrenome());
        funcionario.setDtNascimento(funcionario.getDtNascimento());
        funcionario.setEmail(funcionarios.getEmail());
        funcionario.setNumPis(funcionarios.getNumPis());

        return ResponseEntity.ok(
                this.FuncionarioInterface.save(funcionario));
    }


    @DeleteMapping("/funcionario/excluir/{id_funcionario}")
    public Map<String, Boolean> deleteFuncionario(@PathVariable(value = "id_funcionario") Integer idFuncionario) throws ResourceNotFoundException {
        funcionarioDTO funcionario = FuncionarioInterface.findById(idFuncionario)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario nao encontrado pelo id :: " + idFuncionario));

        this.FuncionarioInterface.delete(funcionario);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", true);

        return response;
    }

}

