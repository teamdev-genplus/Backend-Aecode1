package ai.aecode.aecode1.controllers;

import ai.aecode.aecode1.dtos.ModuleDTO;
import ai.aecode.aecode1.entities.Module;
import ai.aecode.aecode1.services.IModuleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/module")
public class ModuleController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private IModuleService mS;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> insert(@RequestPart(value="file", required = false) MultipartFile imagen,
                                         @RequestPart(value = "data", required = false) String dtoJson) {
        String originalFilename = null;
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            ModuleDTO dto= objectMapper.readValue(dtoJson, ModuleDTO.class);

            String userUploadDir = uploadDir + File.separator + "module";
            Path userUploadPath = Paths.get(userUploadDir);
            if (!Files.exists(userUploadPath)) {
                Files.createDirectories(userUploadPath);
            }

            // Manejo del archivo de script
            if (imagen != null && !imagen.isEmpty()) {
                originalFilename = imagen.getOriginalFilename();;
                byte[] bytes = imagen.getBytes();
                Path path = userUploadPath.resolve(originalFilename);
                Files.write(path, bytes);
            }

            // Convertir DTO a entidad
            ModelMapper modelMapper = new ModelMapper();
            Module module = modelMapper.map(dto, Module.class);
            // Establecer la ruta del archivo en la entidad
            module.setModule_image("module/"+originalFilename);
            mS.insert(module);

            return ResponseEntity.ok("Modulo guardado correctamente");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el archivo de imagen: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al insertar el objeto en la base de datos: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ModuleDTO>> list(){
        List<ModuleDTO> datos = mS.list().stream()
                .map(module -> {
                    ModuleDTO dto = new ModuleDTO();
                    dto.setId_module(module.getId_module());
                    dto.setModule_startdate(module.getModule_startdate());
                    dto.setModule_tittle(module.getModule_tittle());
                    dto.setModule_image("/uploads/" + module.getModule_image());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(datos);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Integer id){mS.delete(id);}

    @GetMapping("/{id}")
    public ResponseEntity<List<ModuleDTO>>  listId(@PathVariable("id")Integer id){
        List<ModuleDTO> datos = mS.list().stream()
                .map(module -> {
                    ModuleDTO dto = new ModuleDTO();
                    dto.setId_module(module.getId_module());
                    dto.setModule_startdate(module.getModule_startdate());
                    dto.setModule_tittle(module.getModule_tittle());
                    dto.setModule_image("/uploads/" + module.getModule_image());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(datos);
    }
    @PutMapping
    public void update(@RequestBody ModuleDTO dto) {
        ModelMapper m = new ModelMapper();
        Module md = m.map(dto, Module.class);
        mS.insert(md);
    }
}
