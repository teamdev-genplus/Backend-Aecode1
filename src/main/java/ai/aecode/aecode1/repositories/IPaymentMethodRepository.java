package ai.aecode.aecode1.repositories;

import ai.aecode.aecode1.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod,Integer> {
}
