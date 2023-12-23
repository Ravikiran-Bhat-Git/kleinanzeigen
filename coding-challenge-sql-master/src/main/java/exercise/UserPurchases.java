package exercise;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@jakarta.persistence.Table(name = "T_USER_PURCHASES")
@NoArgsConstructor
@AllArgsConstructor
public class UserPurchases extends Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

}
