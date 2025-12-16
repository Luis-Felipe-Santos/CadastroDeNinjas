package dev.lipe.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

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
    @GetMapping("/todos")
    public String mostrarNinjas(){
        return "Mostrar todos os Ninjas";
    }

    //Mostrar Ninjas por ID
    @GetMapping("/todosID")
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
