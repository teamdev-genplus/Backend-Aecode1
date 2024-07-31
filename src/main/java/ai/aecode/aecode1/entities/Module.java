package ai.aecode.aecode1.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_module;
    @Column(name = "module_tittle")
    private String module_tittle;
    @Column(name = "module_startdate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate module_startdate;
    @Column(name="module_image")
    private String module_image;

    public Module() {
    }

    public Module(int id_module, String module_tittle, LocalDate module_startdate, String module_image) {
        this.id_module = id_module;
        this.module_tittle = module_tittle;
        this.module_startdate = module_startdate;
        this.module_image = module_image;
    }

    public int getId_module() {
        return id_module;
    }

    public void setId_module(int id_module) {
        this.id_module = id_module;
    }

    public String getModule_tittle() {
        return module_tittle;
    }

    public void setModule_tittle(String module_tittle) {
        this.module_tittle = module_tittle;
    }

    public LocalDate getModule_startdate() {
        return module_startdate;
    }

    public void setModule_startdate(LocalDate module_startdate) {
        this.module_startdate = module_startdate;
    }

    public String getModule_image() {
        return module_image;
    }

    public void setModule_image(String module_image) {
        this.module_image = module_image;
    }
}
