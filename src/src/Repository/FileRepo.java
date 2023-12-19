package Repository;

import Domain.Entity;

public abstract class FileRepo<ID,T extends Entity<ID>> extends MemoryRepo<ID,T> {

    protected String Filename;
    protected abstract void readFromFile();
    protected abstract void writeToFile();

    protected FileRepo(String filename){
        Filename=filename;
        //readFromFile();
    }

    @Override
    public void addItem(ID id, T elem){
        super.addItem(id, elem);
        writeToFile();
    }

    @Override
    public void removeItem(ID id){
        super.removeItem(id);
        writeToFile();
    }

    @Override
    public void updateItem(ID id, T elem){
        super.updateItem(id, elem);
        writeToFile();
    }


}
