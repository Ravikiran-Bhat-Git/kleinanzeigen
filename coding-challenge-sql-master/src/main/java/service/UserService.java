package service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import dto.Purchases;
import dto.Users;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public List<Users> getUsersFromFile() {
        URL fileUrl = PurchasesService.class.getClassLoader().getResource("users.csv");

        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = null;
        List<Users> usersList = new ArrayList<>();
        try {
            csvReader = new CSVReaderBuilder(new FileReader(fileUrl.getFile()))
                    .withSkipLines(1)
                    .withCSVParser(csvParser)
                    .build();
            String[] values;

            while ((values = csvReader.readNext()) != null) {
                Long userId = Long.valueOf(values[0]);
                String name = values[1];
                String email = values[2];
                Users user = new Users(userId, name, email);
                usersList.add(user);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return usersList;
    }
}
