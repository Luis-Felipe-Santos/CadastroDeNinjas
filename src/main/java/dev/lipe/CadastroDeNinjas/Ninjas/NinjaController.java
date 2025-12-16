package dev.lipe.CadastroDeNinjas.Ninjas;

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
    public String criarNinja(){
        return "Ninja criado";
    }

    //Mostrar todos os Ninjas
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }

    //Mostrar Ninjas por ID
    @GetMapping("/listarID")
    public String mostrarNinjasPorID(){
        return "Mostrar Ninjas por ID";
    }

    //Alterar dados dos Ninjas
    @PutMapping("/alterarID")
    public String alterarNinjaPorID(){
        return "Alterar ninja por ID";
    }

    // Deletar Ninja
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorID(){
        return "Deletar ninja por ID";
    }

}
