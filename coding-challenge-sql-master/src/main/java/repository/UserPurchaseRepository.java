package repository;

import exercise.UserPurchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPurchaseRepository extends JpaRepository<UserPurchases, Long> {
    @Query(
            value = """
            Select *
            FROM T_USER_PURCHASES 
            order by :orderBy
        """,
            nativeQuery = true
    )
    List<UserPurchases> findAllOrderedBy(String orderby);
}
