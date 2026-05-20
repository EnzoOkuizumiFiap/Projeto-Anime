package br.com.fiap.animes.Personagem;

import br.com.fiap.animes.Personagem.dto.PersonagemRequest;
import br.com.fiap.animes.Personagem.dto.PersonagemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("personagens")
@Tag(name = "Personagens", description = "Controller responsável pelo gerenciamento de personagens")
public class PersonagemController {
    private final PersonagemService service;

    @GetMapping
    @Operation(
            summary = "Lista todos os personagens",
            description = "Retorna uma lista paginada de todos os personagens cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de personagens retornada com sucesso")
    })
    public PagedModel<EntityModel<PersonagemResponse>> findAll(Pageable pageable, PagedResourcesAssembler<PersonagemResponse> assembler) {
        return assembler.toModel(service.findAll(pageable).map(PersonagemResponse::fromEntity),
                personagem -> EntityModel.of(personagem, linkTo(methodOn(PersonagemController.class).findById(personagem.id())).withSelfRel().withTitle("Character details"))
        );
    }

    @GetMapping("anime")
    @Operation(
            summary = "Busca personagens por anime",
            description = "Retorna uma lista paginada de personagens vinculados ao anime informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personagens encontrados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Valor inválido"),
            @ApiResponse(responseCode = "404", description = "Anime não encontrado")
    })
    public PagedModel<EntityModel<PersonagemResponse>> findAllByAnimeId(@RequestParam Long animeId, Pageable pageable, PagedResourcesAssembler<PersonagemResponse> assembler) {
        return assembler.toModel(service.findAllByAnimeId(animeId, pageable).map(PersonagemResponse::fromEntity),
                personagem -> EntityModel.of(personagem, linkTo(methodOn(PersonagemController.class).findById(personagem.id())).withSelfRel().withTitle("Character details"))
        );
    }

    @GetMapping("by-name")
    @Operation(
            summary = "Busca personagens por nome",
            description = "Retorna os personagens cujo nome contém o valor informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personagens encontrados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Valor inválido")
    })
    public List<EntityModel<PersonagemSummary>> findAllByNome(@RequestParam String nome) {
        return service.findByNome(nome).stream().map(personagem -> EntityModel.of(personagem,
                linkTo(methodOn(PersonagemController.class).findById(personagem.getId())).withSelfRel().withTitle("Character summary"))).toList();
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Busca personagem por ID",
            description = "Retorna os dados de um personagem específico pelo ID informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personagem encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
    })
    public EntityModel<PersonagemResponse> findById(@PathVariable Long id) {
        return EntityModel.of(PersonagemResponse.fromEntity(service.findById(id)),
                linkTo(methodOn(PersonagemController.class).findById(id)).withSelfRel()
        );
    }

    @PostMapping
    @Operation(
            summary = "Cadastra personagem",
            description = "Realiza o cadastro de um novo personagem"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Personagem cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<EntityModel<PersonagemResponse>> create(@RequestBody @Valid PersonagemRequest request) {
        PersonagemResponse personagem = PersonagemResponse.fromEntity(service.create(request));
        var selfLink = linkTo(methodOn(PersonagemController.class).findById(personagem.id()));
        return ResponseEntity.created(selfLink.toUri()).body(EntityModel.of(personagem, selfLink.withSelfRel().withTitle("Character details")));
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Atualiza personagem",
            description = "Atualiza os dados de um personagem existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personagem atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
    })
    public EntityModel<PersonagemResponse> update(@PathVariable Long id, @RequestBody @Valid PersonagemRequest request) {
        PersonagemResponse personagem = PersonagemResponse.fromEntity(service.update(id, request));
        return EntityModel.of(personagem, linkTo(methodOn(PersonagemController.class).findById(personagem.id())).withSelfRel());
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Exclui personagem",
            description = "Remove um personagem do sistema pelo ID informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Personagem removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}