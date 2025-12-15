package dev.lipe.CadastroDeNinjas.Ninjas;


import dev.lipe.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity - transforma uma classe em uma entidade do banco de dados
// JPA - Java Persistence API
@Entity
@Table(name = "tb_cadastro")
@Data // cria os getter e setters
@NoArgsConstructor // cria construtor sem argumentos
@AllArgsConstructor // cria construtor com todos argumentos
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private int idade;

    //@ManyToOne Um ninja tem uma unica missao
    @ManyToOne
    @JoinColumn(name = "missoes_id") // Foreign Key ou chave estrangeira
    private MissoesModel missoes;
}
