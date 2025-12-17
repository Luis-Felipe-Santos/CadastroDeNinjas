package dev.lipe.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
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
    public NinjaModel criarNinja(NinjaModel ninja) {
       return ninjaRepository.save(ninja);
    }
}
