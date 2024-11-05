package generation.rencapp.services;

import generation.rencapp.models.Funcionario;
import generation.rencapp.models.Vecino;
import generation.rencapp.repositories.FuncionarioRepository;
import generation.rencapp.repositories.VecinoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Transactional
@AllArgsConstructor
public class VecinoServiceImpl implements VecinoService {
    @Autowired
    private VecinoRepository vecinoRepository;

    @Override
    public ArrayList<Vecino> findAllVecino() {
        return (ArrayList<Vecino>) vecinoRepository.findAll();
    }


    @Transactional
    public Vecino saveVecino(Vecino vecino){
        return vecinoRepository.save(vecino);
    }


}
