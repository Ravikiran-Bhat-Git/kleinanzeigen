package service;

import dto.Purchases;
import exercise.CodingChallengeSQL;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodingChallengeSQL.class)
@EnableAutoConfiguration(
        exclude = {DataSourceAutoConfiguration.class}
)
class PurchasesServiceTest {
    @Autowired
    private PurchasesService purchasesService;
    @Test
    void shouldGetPurchasesFromFile() {
        List<Purchases> purchases = purchasesService.getPurchasesFromFile();
        assertFalse(purchases.isEmpty());
    }
}
