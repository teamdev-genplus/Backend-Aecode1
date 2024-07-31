package ai.aecode.aecode1.repositories;

import ai.aecode.aecode1.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IModuleRepository extends JpaRepository<Module, Integer> {
}
