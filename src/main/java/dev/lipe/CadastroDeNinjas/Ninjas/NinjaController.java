package dev.lipe.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    @Operation( summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar Ninja
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo Ninja", description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
    })

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
    @Operation(summary = "Lista o ninja por id", description = "Rota lista um ninja pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
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
    @Operation(
            summary = "Altera o ninja por id", description = "Rota altera um ninja pelo seu id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Usuário manda os dados do ninja a ser atualizado no corpo da requisição",
            required = true
     )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado, não foi possível alterar")
    })
    public ResponseEntity<?> alterarNinjaPorID(
            @Parameter(description = "Usuário manda o id no caminho da requisição")
            @PathVariable Long id,
            @RequestBody NinjaDTO ninjaAtualizado){
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
