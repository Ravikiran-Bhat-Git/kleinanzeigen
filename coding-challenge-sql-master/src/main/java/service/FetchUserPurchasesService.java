package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.UserPurchaseRepository;

@Service
@RequiredArgsConstructor
public class FetchUserPurchasesService {
    private final UserPurchaseRepository userPurchaseRepository;
    public void getAllProductsOrderedByColumn(String orderBy) {
        userPurchaseRepository.findAllOrderedBy(orderBy);
    }
}
