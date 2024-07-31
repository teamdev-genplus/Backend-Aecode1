package ai.aecode.aecode1.dtos;

import ai.aecode.aecode1.entities.Module;
import ai.aecode.aecode1.entities.PaymentMethod;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Set;

public class CourseDTO {

    private int id_course;
    private String course_tittle;
    private String course_image;
    private Module module;
    private Set<PaymentMethod> paymentMethods;

    public int getId_course() {
        return id_course;
    }

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }

    public String getCourse_tittle() {
        return course_tittle;
    }

    public void setCourse_tittle(String course_tittle) {
        this.course_tittle = course_tittle;
    }

    public String getCourse_image() {
        return course_image;
    }

    public void setCourse_image(String course_image) {
        this.course_image = course_image;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Set<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(Set<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
