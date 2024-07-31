package ai.aecode.aecode1.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class ModuleDTO {
    private int id_module;
    private String module_tittle;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate module_startdate;
    private String module_image;

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
