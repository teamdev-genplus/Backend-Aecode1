package ai.aecode.aecode1.servicesimplement;

import ai.aecode.aecode1.entities.PaymentMethod;
import ai.aecode.aecode1.repositories.IPaymentMethodRepository;
import ai.aecode.aecode1.services.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImplement implements IPaymentMethodService {
    @Autowired
    private IPaymentMethodRepository pmR;
    @Override
    public void insert(PaymentMethod paymentMethod) {
        pmR.save(paymentMethod);
    }

    @Override
    public List<PaymentMethod> list() {
        return pmR.findAll();
    }

    @Override
    public void delete(int id_payment) {
        pmR.deleteById(id_payment);
    }

    @Override
    public PaymentMethod listId(int id_payment) {
        return pmR.findById(id_payment).orElse(new PaymentMethod());
    }

}
