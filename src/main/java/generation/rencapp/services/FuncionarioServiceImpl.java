package generation.rencapp.services;

import generation.rencapp.models.Funcionario;
import generation.rencapp.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Transactional
@AllArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public ArrayList<Funcionario> findAllFuncionario() {
        return (ArrayList<Funcionario>) funcionarioRepository.findAll();
    }


    @Transactional
    public Funcionario saveFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

}
