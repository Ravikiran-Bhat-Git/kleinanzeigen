package service;

import dto.Purchases;
import dto.Users;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.UserPurchaseRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserPurchasesServiceTest {
    @InjectMocks
    private UserPurchasesService underTest;
    @Mock
    private UserPurchaseRepository userPurchaseRepository;
    @Mock
    private PurchasesService purchasesService;
    @Mock
    private UserService userService;


    @Test
    void shouldSaveUserPurchasesWhenUserPresentForEachPurchase() {
        Purchases purchase1 = new Purchases(1L, "car-1", 1L);
        Purchases purchase2 = new Purchases(4L, "guitar-1", 2L);

        Users user1 = new Users(2L, "manuel","manuel@foo.de");
        Users user2 = new Users(1L, "andre","andre@bar.de");

        when(purchasesService.getPurchasesFromFile()).thenReturn(List.of(purchase1, purchase2));
        when(userService.getUsersFromFile()).thenReturn(List.of(user1, user2));

        underTest.saveProducts();

        verify(userPurchaseRepository).saveAll(any());
    }

    @Test
    void shouldNotSaveUserPurchasesAndThrowExceptionWhenUserNotFound() {
        Purchases purchase1 = new Purchases(1L, "car-1", 1L);
        Purchases purchase2 = new Purchases(4L, "guitar-1", 2L);

        Users user1 = new Users(2L, "manuel","manuel@foo.de");

        when(purchasesService.getPurchasesFromFile()).thenReturn(List.of(purchase1, purchase2));
        when(userService.getUsersFromFile()).thenReturn(List.of(user1));

        assertThrows(EntityNotFoundException.class, () -> underTest.saveProducts());
    }


}
