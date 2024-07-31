package ai.aecode.aecode1.servicesimplement;

import ai.aecode.aecode1.entities.Module;
import ai.aecode.aecode1.repositories.IModuleRepository;
import ai.aecode.aecode1.services.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImplement implements IModuleService {
    @Autowired
    private IModuleRepository mR;

    @Override
    public void insert(Module module) {
        mR.save(module);
    }

    @Override
    public List<Module> list() {
        return mR.findAll();
    }

    @Override
    public void delete(int id_module) {
        mR.deleteById(id_module);
    }

    @Override
    public Module listId(int id_module) {
        return mR.findById(id_module).orElse(new Module());
    }
}
