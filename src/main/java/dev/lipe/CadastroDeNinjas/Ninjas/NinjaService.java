package dev.lipe.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Listar todos os ninjas
    public List<NinjaModel> listarNinjas() {
        return ninjaRepository.findAll();
    };

    // Listar  ninjas por ID
   public NinjaModel listarNinjasPorId(Long id){
       Optional<NinjaModel> ninjaPorID = ninjaRepository.findById(id);
       return ninjaPorID.orElse(null);
   }

    // Criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
       NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    // Deletar ninja - Tem que ser um metodo void
    public void deletarNinjaPorID(Long id){
       ninjaRepository.deleteById(id);
    }

    // Atualizar ninja
    public NinjaModel atualizarNinja(Long id, NinjaModel ninjaAtualizado) {
       if(ninjaRepository.existsById(id)){
           ninjaAtualizado.setId(id);
           return ninjaRepository.save(ninjaAtualizado);
       }
       return null;
    }
}
