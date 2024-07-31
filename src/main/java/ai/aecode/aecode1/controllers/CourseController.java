package ai.aecode.aecode1.controllers;

import ai.aecode.aecode1.dtos.CourseDTO;
import ai.aecode.aecode1.entities.Course;
import ai.aecode.aecode1.services.ICourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService cS;
    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> insert(@RequestPart(value="file", required = false) MultipartFile imagen,
                                         @RequestPart(value = "data", required = false) String dtoJson) {
        String originalFilename = null;
        try { ObjectMapper objectMapper = new ObjectMapper();
            CourseDTO dto= objectMapper.readValue(dtoJson, CourseDTO.class);

            String userUploadDir = uploadDir + File.separator + "course";
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
            Course course = modelMapper.map(dto, Course.class);
            // Establecer la ruta del archivo en la entidad
            course.setCourse_image("course/"+originalFilename);
            cS.insert(course);

            return ResponseEntity.ok("Curso guardado correctamente");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el archivo de imagen: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al insertar el objeto en la base de datos: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> list(){
        List<CourseDTO> datos = cS.list().stream()
                .map(course -> {
                    CourseDTO dto = new CourseDTO();
                    dto.setId_course(course.getId_course());
                    dto.setCourse_tittle(course.getCourse_tittle());
                    dto.setModule(course.getModule());
                    dto.setCourse_image("/uploads/" + course.getCourse_image());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(datos);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Integer id){cS.delete(id);}

    @GetMapping("/{id}")
    public ResponseEntity<List<CourseDTO>> listId(@PathVariable("id")Integer id){
        List<CourseDTO> datos = cS.list().stream()
                .map(course -> {
                    CourseDTO dto = new CourseDTO();
                    dto.setId_course(course.getId_course());
                    dto.setCourse_tittle(course.getCourse_tittle());
                    dto.setModule(course.getModule());
                    dto.setCourse_image("/uploads/" + course.getCourse_image());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(datos);
    }
    @PutMapping
    public void update(@RequestBody CourseDTO dto) {
        ModelMapper m = new ModelMapper();
        Course c = m.map(dto, Course.class);
        cS.insert(c);
    }
}
