package ai.aecode.aecode1.controllers;
import ai.aecode.aecode1.dtos.PaymentMethodDTO;
import ai.aecode.aecode1.entities.PaymentMethod;
import ai.aecode.aecode1.services.IPaymentMethodService;
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
@RequestMapping("/paymentmethod")
public class PaymentMethodController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private IPaymentMethodService pmS;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> insert(@RequestPart(value="file", required = false) MultipartFile imagen,
                                         @RequestPart(value = "data", required = false) String dtoJson) {
        String originalFilename = null;
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            PaymentMethodDTO dto= objectMapper.readValue(dtoJson, PaymentMethodDTO.class);

            String userUploadDir = uploadDir + File.separator + "payment";
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
            PaymentMethod payment = modelMapper.map(dto, PaymentMethod.class);
            // Establecer la ruta del archivo en la entidad
            payment.setPayment_qr("payment/"+originalFilename);
            pmS.insert(payment);

            return ResponseEntity.ok("Metodo de pago guardado correctamente");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el archivo de imagen: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al insertar el objeto en la base de datos: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodDTO>> list(){
        List<PaymentMethodDTO> datos = pmS.list().stream()
                .map(payment -> {
                    PaymentMethodDTO dto = new PaymentMethodDTO();
                    dto.setId_paymentmethod(payment.getId_paymentmethod());
                    dto.setPayment_name(payment.getPayment_name());
                    dto.setPayment_phone(payment.getPayment_phone());
                    dto.setPayment_titular(payment.getPayment_titular());
                    dto.setPayment_RUC(payment.getPayment_RUC());
                    dto.setPayment_accountSoles(payment.getPayment_accountSoles());
                    dto.setPayment_cciSoles(payment.getPayment_cciSoles());
                    dto.setPayment_accountDollar(payment.getPayment_accountDollar());
                    dto.setPayment_cciDollar(payment.getPayment_cciDollar());
                    dto.setPayment_qr("/uploads/" + payment.getPayment_qr());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(datos);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Integer id){pmS.delete(id);}

    @GetMapping("/{id}")
    public ResponseEntity<List<PaymentMethodDTO>> listId(@PathVariable("id")Integer id){
        List<PaymentMethodDTO> datos = pmS.list().stream()
                .map(payment -> {
                    PaymentMethodDTO dto = new PaymentMethodDTO();
                    dto.setId_paymentmethod(payment.getId_paymentmethod());
                    dto.setPayment_name(payment.getPayment_name());
                    dto.setPayment_phone(payment.getPayment_phone());
                    dto.setPayment_titular(payment.getPayment_titular());
                    dto.setPayment_RUC(payment.getPayment_RUC());
                    dto.setPayment_accountSoles(payment.getPayment_accountSoles());
                    dto.setPayment_cciSoles(payment.getPayment_cciSoles());
                    dto.setPayment_accountDollar(payment.getPayment_accountDollar());
                    dto.setPayment_cciDollar(payment.getPayment_cciDollar());
                    dto.setPayment_qr("/uploads/" + payment.getPayment_qr());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(datos);
    }
    @PutMapping
    public void update(@RequestBody PaymentMethodDTO dto) {
        ModelMapper m = new ModelMapper();
        PaymentMethod p = m.map(dto, PaymentMethod.class);
        pmS.insert(p);
    }
}
