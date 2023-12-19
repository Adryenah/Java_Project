package Repository;

import Domain.Entity;

import java.util.HashMap;
import java.util.Map;

public class MemoryRepo<ID,T extends Entity<ID> > implements iRepository<ID,T>{

    Map<ID,T> repository;
    public MemoryRepo(){this.repository=new HashMap<ID,T>();}

    @Override
    public void addItem(ID id, T item) {
        if(repository.containsKey(id)) throw new RuntimeException("This ID already exists!");
        repository.put(id, item);
    }

    @Override
    public void removeItem(ID id) {
        if(!repository.containsKey(id)) throw new RuntimeException("ID does not exist");
        repository.remove(id);
    }

    @Override
    public void updateItem(ID id, T item) {
        if(!repository.containsKey(id)) throw new RuntimeException("ID does not exist");
        repository.replace(id,item);
    }

    @Override
    public T findItemByID(ID id) {
        if(!repository.containsKey(id)) throw new RuntimeException("ID does not exist");
        return repository.get(id);
    }

    @Override
    public Iterable<T> getAllItems() {
        return repository.values();
    }
}
