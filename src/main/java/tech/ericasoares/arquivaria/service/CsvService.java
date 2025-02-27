package tech.ericasoares.arquivaria.service;

import tech.ericasoares.arquivaria.model.Registro;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface CsvService {
    List<Registro> lerCsv(String caminhoArquivo) throws IOException;
}
