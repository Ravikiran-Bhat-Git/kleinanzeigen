package service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.UserPurchaseRepository;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FetchUserPurchasesServiceTest {
    @InjectMocks
    private FetchUserPurchasesService underTest;

    @Mock
    private UserPurchaseRepository userPurchaseRepository;

    @Test
    void shouldFetchAllProductsOrderedByColumn() {
        underTest.getAllProductsOrderedByColumn("user_id");
        verify(userPurchaseRepository).findAllOrderedBy("user_id");
    }
}
