package service;

import dto.Purchases;
import dto.Users;
import exercise.UserPurchases;
import interfaces.SaveUserProducts;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.UserPurchaseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPurchasesService implements SaveUserProducts {
    private final UserPurchaseRepository userPurchaseRepository;
    private final PurchasesService purchasesService;
    private final UserService userService;

    @Override
    public List<UserPurchases> saveProducts() {
        List<Purchases> purchasesList = purchasesService.getPurchasesFromFile();
        List<Users> usersList = userService.getUsersFromFile();

        List<UserPurchases> userPurchases = new ArrayList<>();

        purchasesList.forEach(purchase -> {
            Users user = usersList.stream().filter(item -> item.userId().equals(purchase.userId())).findFirst().orElseThrow(
                    () -> new EntityNotFoundException(String.format("User with id %s not found", purchase.userId()))
            );
            UserPurchases userPurchase = new UserPurchases();
            userPurchase.setUserId(user.userId());
            userPurchase.setCreatedBy(user.email());
            userPurchase.setUserName(user.name());
            userPurchase.setProductId(purchase.adId());
            userPurchase.setProductName(purchase.title());

            userPurchases.add(userPurchase);
        });

        return userPurchaseRepository.saveAll(userPurchases);

    }
}
