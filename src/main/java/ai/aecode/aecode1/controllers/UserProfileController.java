package ai.aecode.aecode1.controllers;

import ai.aecode.aecode1.dtos.UserProfileDTO;
import ai.aecode.aecode1.entities.UserProfile;
import ai.aecode.aecode1.services.IUserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

    @Autowired
    private IUserProfileService upS;

    @PostMapping
    public void insert(@RequestBody UserProfileDTO dto){
        ModelMapper m=new ModelMapper();
        UserProfile u= m.map(dto,UserProfile.class);
        upS.insert(u);
    }

    @GetMapping
    public List<UserProfileDTO> list() {
        return upS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UserProfileDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Integer id){upS.delete(id);}

    @GetMapping("/{id}")
    public UserProfileDTO listId(@PathVariable("id")Integer id){
        ModelMapper m=new ModelMapper();
        UserProfileDTO dto=m.map(upS.listId(id),UserProfileDTO.class);
        return dto;
    }
    @PutMapping
    public void update(@RequestBody UserProfileDTO dto) {
        ModelMapper m = new ModelMapper();
        UserProfile u = m.map(dto, UserProfile.class);
        upS.insert(u);
    }
}
