package funcionariosCiss.funcionariosrestapi.Interface;

import funcionariosCiss.funcionariosrestapi.DTO.funcionarioDTO;
import org.springframework.data.repository.CrudRepository;

public interface funcionarioInterface extends CrudRepository<funcionarioDTO, Integer> {
}
