package ai.aecode.aecode1.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "paymentmethod")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_paymentmethod;

    @Column(name = "payment_name")
    private String payment_name;

    @Column(name = "payment_phone")
    private String payment_phone;

    @Column(name = "payment_titular")
    private String payment_titular;

    @Column(name = "payment_RUC")
    private String payment_RUC;

    @Column(name = "payment_accountSoles")
    private String payment_accountSoles;

    @Column(name = "payment_cciSoles")
    private String payment_cciSoles;

    @Column(name = "payment_accountDollar")
    private String payment_accountDollar;

    @Column(name = "payment_cciDollar")
    private String payment_cciDollar;

    @Column(name = "payment_qr")
    private String payment_qr;

    public PaymentMethod() {
    }

    public PaymentMethod(int id_paymentmethod, String payment_name, String payment_phone, String payment_titular, String payment_RUC, String payment_accountSoles, String payment_cciSoles, String payment_accountDollar, String payment_cciDollar, String payment_qr) {
        this.id_paymentmethod = id_paymentmethod;
        this.payment_name = payment_name;
        this.payment_phone = payment_phone;
        this.payment_titular = payment_titular;
        this.payment_RUC = payment_RUC;
        this.payment_accountSoles = payment_accountSoles;
        this.payment_cciSoles = payment_cciSoles;
        this.payment_accountDollar = payment_accountDollar;
        this.payment_cciDollar = payment_cciDollar;
        this.payment_qr = payment_qr;
    }

    public int getId_paymentmethod() {
        return id_paymentmethod;
    }

    public void setId_paymentmethod(int id_paymentmethod) {
        this.id_paymentmethod = id_paymentmethod;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }

    public String getPayment_phone() {
        return payment_phone;
    }

    public void setPayment_phone(String payment_phone) {
        this.payment_phone = payment_phone;
    }

    public String getPayment_titular() {
        return payment_titular;
    }

    public void setPayment_titular(String payment_titular) {
        this.payment_titular = payment_titular;
    }

    public String getPayment_RUC() {
        return payment_RUC;
    }

    public void setPayment_RUC(String payment_RUC) {
        this.payment_RUC = payment_RUC;
    }

    public String getPayment_accountSoles() {
        return payment_accountSoles;
    }

    public void setPayment_accountSoles(String payment_accountSoles) {
        this.payment_accountSoles = payment_accountSoles;
    }

    public String getPayment_cciSoles() {
        return payment_cciSoles;
    }

    public void setPayment_cciSoles(String payment_cciSoles) {
        this.payment_cciSoles = payment_cciSoles;
    }

    public String getPayment_accountDollar() {
        return payment_accountDollar;
    }

    public void setPayment_accountDollar(String payment_accountDollar) {
        this.payment_accountDollar = payment_accountDollar;
    }

    public String getPayment_cciDollar() {
        return payment_cciDollar;
    }

    public void setPayment_cciDollar(String payment_cciDollar) {
        this.payment_cciDollar = payment_cciDollar;
    }

    public String getPayment_qr() {
        return payment_qr;
    }

    public void setPayment_qr(String payment_qr) {
        this.payment_qr = payment_qr;
    }
}
