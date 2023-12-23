package service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import dto.Purchases;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchasesService {
    public List<Purchases> getPurchasesFromFile() {
        URL fileUrl = PurchasesService.class.getClassLoader().getResource("purchases.csv");

        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = null;
        List<Purchases> purchasesList = new ArrayList<>();
        try {
            csvReader = new CSVReaderBuilder(new FileReader(fileUrl.getFile()))
                    .withSkipLines(1)
                    .withCSVParser(csvParser)
                    .build();
            String[] values;

            while ((values = csvReader.readNext()) != null) {
                Long adId = Long.valueOf(values[0]);
                String title = values[1];
                Long userId = Long.valueOf(values[2]);
                Purchases purchases = new Purchases(adId, title, userId);
                purchasesList.add(purchases);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return purchasesList;
    }
}
