package dev.lipe.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")

    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar Ninja
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome());
    }

    //Mostrar todos os Ninjas
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    //Mostrar Ninjas por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorID(@PathVariable Long id){
       NinjaDTO ninjaPorID =  ninjaService.listarNinjasPorId(id);
       if(ninjaPorID != null) {
           return ResponseEntity.ok(ninjaPorID);
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Ninja com o id: " + id + " não existe nos nossos registros");
       }
    }

    //Alterar dados dos Ninjas
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> alterarNinjaPorID(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
        NinjaDTO ninja =  ninjaService.atualizarNinja(id, ninjaAtualizado);
        if(ninja != null) {
            return ResponseEntity.ok(ninja);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o id: " + id + " não existe nos nossos registros");
        }
    }

    // Deletar Ninja
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorID(@PathVariable Long id){
        if(ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaPorID(id);
            return ResponseEntity.ok("Ninja deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o id " + id + " não foi encontrado.");
        }
    }


}
