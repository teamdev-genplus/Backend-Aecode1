package ai.aecode.aecode1.services;
import ai.aecode.aecode1.entities.PaymentMethod;

import java.util.List;

public interface IPaymentMethodService {
    public void insert(PaymentMethod paymentMethod);
    List<PaymentMethod> list();
    public void delete(int id_payment);
    public PaymentMethod listId(int id_payment);
}
