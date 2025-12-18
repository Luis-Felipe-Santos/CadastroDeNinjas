package dev.lipe.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Listar todos os ninjas
    public List<NinjaDTO> listarNinjas() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    };

    // Listar  ninjas por ID
   public NinjaDTO listarNinjasPorId(Long id){
       Optional<NinjaModel> ninjaPorID = ninjaRepository.findById(id);
       return ninjaPorID.map(ninjaMapper::map).orElse(null);
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
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO) {
       Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);
       if(ninjaExistente.isPresent()) {
           NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
           ninjaAtualizado.setId(id);
           NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado);
           return ninjaMapper.map(ninjaSalvo);
       }
       return null;
    }
}
