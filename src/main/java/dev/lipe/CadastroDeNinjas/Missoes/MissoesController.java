package dev.lipe.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    // GET -- Mandar uma Requisição para mostrar as missoes
    @GetMapping("/listar")
    public String listarMissao(){
        return "Missoes listadas com sucesso";
    }

    // POST -- Mandar uma Requisição para criar as missoes
    @PostMapping("/criar")
    public String criarMissao() {
        return "Missao criada com sucesso";
    }

    // PUT -- Mandar uma Requisição para alterar as missoes
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada com sucesso";
    }

    // DELETE-- Mandar uma Requisição para deletar uma missao
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missao deletada com sucesso";
    }
}
