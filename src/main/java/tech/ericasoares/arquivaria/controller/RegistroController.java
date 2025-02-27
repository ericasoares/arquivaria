package tech.ericasoares.arquivaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import tech.ericasoares.arquivaria.model.ErrorResponse;
import tech.ericasoares.arquivaria.model.Registro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.ericasoares.arquivaria.service.CsvService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroController {

    @Autowired
    private CsvService csvService;

    @Operation(summary = "Ler arquivo CSV", description = "Retorna os registros de um arquivo CSV.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo lido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Caminho do arquivo inválido"),
            @ApiResponse(responseCode = "404", description = "Arquivo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao ler o arquivo")
    })
    @GetMapping
    public ResponseEntity<?> lerCsv(@RequestParam @NotBlank String caminhoArquivo) {
        try {
            List<Registro> registros = csvService.lerCsv(caminhoArquivo);
            return ResponseEntity.ok(registros);
        } catch (FileNotFoundException e) {
            ErrorResponse error = new ErrorResponse("Arquivo não encontrado: " + caminhoArquivo, 404, LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (IOException e) {
            ErrorResponse error = new ErrorResponse("Erro ao ler o arquivo: " + e.getMessage(), 500, LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}