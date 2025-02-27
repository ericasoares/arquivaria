package tech.ericasoares.arquivaria.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import tech.ericasoares.arquivaria.model.Registro;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CsvServiceImpl implements CsvService {

    @Override
    public List<Registro> lerCsv(String caminhoArquivo) throws IOException {
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            HeaderColumnNameMappingStrategy<Registro> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Registro.class);

            CsvToBean<Registro> csvToBean = new CsvToBeanBuilder<Registro>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }
}
