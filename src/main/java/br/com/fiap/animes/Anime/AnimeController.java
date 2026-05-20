package br.com.fiap.animes.Anime;

import br.com.fiap.animes.Anime.dto.AnimeRequest;
import br.com.fiap.animes.Anime.dto.AnimeResponse;
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

import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("animes")
@RequiredArgsConstructor
@Tag(name = "Animes", description = "Controller responsável pelo gerenciamento de animes")
public class AnimeController {
    private final AnimeService service;

    @GetMapping
    @Operation(
            summary = "Lista todos os animes",
            description = "Retorna uma lista paginada de todos os animes cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de animes retornada com sucesso")
    })
    public PagedModel<EntityModel<AnimeResponse>> findAll(Pageable pageable, PagedResourcesAssembler<AnimeResponse> assembler) {
        return assembler.toModel(service.findAll(pageable).map(AnimeResponse::fromEntity),
                anime -> EntityModel.of(anime, linkTo(methodOn(AnimeController.class).findById(anime.id())).withSelfRel().withTitle("Anime details"))
        );
    }

    @GetMapping("by-title")
    @Operation(
            summary = "Busca animes por título",
            description = "Retorna os animes cujo título contém o valor informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animes encontrados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Valor inválido")
    })
    public PagedModel<EntityModel<AnimeSummary>> findAllByTitle(@RequestParam String title, Pageable pageable, PagedResourcesAssembler<AnimeSummary> assembler) {
        return assembler.toModel(service.findAllByTituloContaining(title, pageable),
        anime -> EntityModel.of(anime, linkTo(methodOn(AnimeController.class).findById(anime.getId())).withSelfRel().withTitle("Anime summary")
            )
        );
    }

    @GetMapping("by-category/{categories}")
    @Operation(
            summary = "Busca animes por categoria",
            description = "Retorna uma lista paginada de animes filtrados pelas categorias informadas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animes encontrados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Valor inválido")
    })
    public PagedModel<EntityModel<AnimeSummary>> findAllByCategory(@PathVariable List<Categoria> categories, Pageable pageable, PagedResourcesAssembler<AnimeSummary> assembler) {
        return assembler.toModel(service.findAllByCategoria(categories, pageable),
                anime -> EntityModel.of(anime, linkTo(methodOn(AnimeController.class).findById(anime.getId())).withSelfRel().withTitle("Anime summary")
                )
        );
    }

    @GetMapping("by-date/{date}")
    @Operation(
            summary = "Busca animes por data de lançamento",
            description = "Retorna uma lista paginada de animes lançados na data informada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animes encontrados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Valor inválido")
    })
    public PagedModel<EntityModel<AnimeSummary>> findAllByLaunch(@PathVariable LocalDate date, Pageable pageable, PagedResourcesAssembler<AnimeSummary> assembler) {
        return assembler.toModel(service.findAllByLancamento(date, pageable),
                anime -> EntityModel.of(anime, linkTo(methodOn(AnimeController.class).findById(anime.getId())).withSelfRel().withTitle("Anime summary")
                )
        );
    }

    @GetMapping("by-year-range/{from}/{to}")
    @Operation(
            summary = "Busca animes por período de lançamento",
            description = "Retorna uma lista paginada de animes lançados entre as datas informadas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animes encontrados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Valor inválido")
    })
    public PagedModel<EntityModel<AnimeSummary>> findAllByLaunchPeriod(@PathVariable LocalDate from, @PathVariable LocalDate to, Pageable pageable, PagedResourcesAssembler<AnimeSummary> assembler) {
        return assembler.toModel(service.findAllByPeriodoLancamento(from, to, pageable),
                anime -> EntityModel.of(anime, linkTo(methodOn(AnimeController.class).findById(anime.getId())).withSelfRel().withTitle("Anime summary")
                )
        );
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Busca anime por ID",
            description = "Retorna os dados de um anime específico pelo ID informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anime encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Anime não encontrado")
    })
    public EntityModel<AnimeResponse> findById(@PathVariable Long id) {
        return EntityModel.of(AnimeResponse.fromEntity(service.findById(id)),
                linkTo(methodOn(AnimeController.class).findById(id)).withSelfRel()
        );
    }

    @PostMapping
    @Operation(
            summary = "Cadastra anime",
            description = "Realiza o cadastro de um novo anime"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Anime cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<EntityModel<AnimeResponse>> create(@RequestBody @Valid AnimeRequest animeRequest) {
        AnimeResponse anime = AnimeResponse.fromEntity(service.create(animeRequest));
        var selfLink = linkTo(methodOn(AnimeController.class).findById(anime.id()));
        return ResponseEntity.created(selfLink.toUri()).body(EntityModel.of(anime, selfLink.withSelfRel().withTitle("Anime details")));
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Atualiza anime",
            description = "Atualiza os dados de um anime existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anime atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Anime não encontrado")
    })
    public EntityModel<AnimeResponse> update(@PathVariable Long id, @RequestBody @Valid AnimeRequest animeRequest) {
        AnimeResponse anime = AnimeResponse.fromEntity(service.update(id, animeRequest));
        return EntityModel.of(anime, linkTo(methodOn(AnimeController.class).findById(anime.id())).withSelfRel());
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Exclui anime",
            description = "Remove um anime do sistema pelo ID informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Anime removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Anime não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}