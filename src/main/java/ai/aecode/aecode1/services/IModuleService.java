package ai.aecode.aecode1.services;

import ai.aecode.aecode1.entities.Module;

import java.util.List;

public interface IModuleService {
    public void insert(Module module);
    List<Module> list();
    public void delete(int id_module);
    public Module listId(int id_module);
}
