package br.com.fiap.animes.Temporada;

import br.com.fiap.animes.Temporada.dto.TemporadaRequest;
import br.com.fiap.animes.Temporada.dto.TemporadaResponse;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("temporadas")
@RequiredArgsConstructor
@Tag(name = "Temporadas", description = "Controller responsável pelo gerenciamento de temporadas")
public class TemporadaController {

    private final TemporadaService service;

    @GetMapping
    @Operation(
            summary = "Lista todas as temporadas",
            description = "Retorna uma lista paginada de todas as temporadas cadastradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de temporadas retornada com sucesso")
    })
    public PagedModel<EntityModel<TemporadaResponse>> findAll(Pageable pageable, PagedResourcesAssembler<TemporadaResponse> assembler) {
        return assembler.toModel(service.findAll(pageable).map(TemporadaResponse::fromEntity),
                temporada -> EntityModel.of(temporada, linkTo(methodOn(TemporadaController.class).findById(temporada.id())).withSelfRel().withTitle("Season details"))
        );
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Busca temporada por ID",
            description = "Retorna os dados de uma temporada específica pelo ID informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temporada encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Temporada não encontrada")
    })
    public EntityModel<TemporadaResponse> findById(@PathVariable Long id) {
        return EntityModel.of(TemporadaResponse.fromEntity(service.findById(id)),
                linkTo(methodOn(TemporadaController.class).findById(id)).withSelfRel()
        );
    }

    @GetMapping("by-anime/{animeId}")
    @Operation(
            summary = "Busca temporadas por anime",
            description = "Retorna uma lista paginada de temporadas vinculadas ao anime informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temporadas encontradas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Valor inválido"),
            @ApiResponse(responseCode = "404", description = "Anime não encontrado")
    })
    public PagedModel<EntityModel<TemporadaResponse>> findAllByAnimeId(@PathVariable Long animeId, Pageable pageable, PagedResourcesAssembler<TemporadaResponse> assembler) {
        return assembler.toModel(service.findAllByAnimeId(animeId, pageable)
                        .map(TemporadaResponse::fromEntity),
                temporada -> EntityModel.of(temporada, linkTo(methodOn(TemporadaController.class).findById(temporada.id())).withSelfRel().withTitle("Season details"))
        );
    }

    @PostMapping
    @Operation(
            summary = "Cadastra temporada",
            description = "Realiza o cadastro de uma nova temporada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Temporada cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<EntityModel<TemporadaResponse>> create(@RequestBody @Valid TemporadaRequest request) {
        TemporadaResponse temporada = TemporadaResponse.fromEntity(service.create(request));
        var selfLink = linkTo(methodOn(TemporadaController.class).findById(temporada.id()));
        return ResponseEntity.created(selfLink.toUri()).body(EntityModel.of(temporada, selfLink.withSelfRel().withTitle("Season details")));
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Atualiza temporada",
            description = "Atualiza os dados de uma temporada existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temporada atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Temporada não encontrada")
    })
    public EntityModel<TemporadaResponse> update(@PathVariable Long id, @RequestBody @Valid TemporadaRequest request) {
        TemporadaResponse temporada = TemporadaResponse.fromEntity(service.update(id, request));
        return EntityModel.of(temporada, linkTo(methodOn(TemporadaController.class).findById(temporada.id())).withSelfRel());
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Exclui temporada",
            description = "Remove uma temporada do sistema pelo ID informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Temporada removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Temporada não encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}