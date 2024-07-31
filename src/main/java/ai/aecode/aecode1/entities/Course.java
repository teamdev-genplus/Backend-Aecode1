package ai.aecode.aecode1.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_course;
    @Column(name = "course_tittle")
    private String course_tittle;
    @Column(name = "course_image")
    private String course_image;
    @ManyToOne
    @JoinColumn(name = "id_module")
    private Module module;

    @ManyToMany
    @JoinTable(name = "detail_payment",
            joinColumns = @JoinColumn(name = "id_course"),
            inverseJoinColumns = @JoinColumn(name = "id_paymentmethod"))
    private Set<PaymentMethod> paymentMethods;

    public Course() {
    }

    public Course(int id_course, String course_tittle, String course_image, Module module, Set<PaymentMethod> paymentMethods) {
        this.id_course = id_course;
        this.course_tittle = course_tittle;
        this.course_image = course_image;
        this.module = module;
        this.paymentMethods = paymentMethods;
    }

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
