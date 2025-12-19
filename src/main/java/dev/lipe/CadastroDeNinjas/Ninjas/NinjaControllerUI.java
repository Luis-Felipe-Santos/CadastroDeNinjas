package dev.lipe.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;

    public NinjaControllerUI(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas"; // tem que retornar o nome da pagina HTML
    }

    @GetMapping("/listar/{id}")
    public String listarNinjasPorID(@PathVariable Long id, Model model){
        NinjaDTO ninja =  ninjaService.listarNinjasPorId(id);
        if(ninja != null) {
            model.addAttribute("ninja", ninja);
            return "detalhesNinja";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "listarNinjas";

        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorID(@PathVariable Long id){
       ninjaService.deletarNinjaPorID(id);
       return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model){
        model.addAttribute("ninja", new NinjaDTO());
        return "adicionarNinja";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes){
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso");
        return  "redirect:/ninjas/ui/listar";
    }


}
