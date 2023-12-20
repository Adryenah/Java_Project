package Repository;


public interface iRepository<ID,T> {
    public void addItem(ID id,T item);
    public void removeItem(ID id);
    public void updateItem(ID id,T item);
    public T findItemByID(ID id);
    public Iterable<T> getAllItems();
}
