package repositories;

import java.util.ArrayList;
import java.util.List;

import Model.Anamnesis;
import exceptions.AnamnesisNotFoundException;

public class AnamnesisRepository implements IAnamnesis {
    private List<Anamnesis> anamneses;
    private long nextId;
    private static AnamnesisRepository anamnesisRepository;

    public static AnamnesisRepository getInstance() {
        if(anamnesisRepository == null) {
            anamnesisRepository = new AnamnesisRepository();
        }
        return anamnesisRepository;
    }

    public AnamnesisRepository() {
        anamneses = new ArrayList<Anamnesis>();
        nextId = 1;
    }
   
    public void add(Anamnesis anamnesis) {
        anamnesis.setId(nextId);
        nextId++;
        this.anamneses.add(anamnesis);
    }

    public List<Anamnesis> list() {
        return new ArrayList<Anamnesis>(this.anamneses);
    }

    public Anamnesis findById(long idAnamnesis) {
        return anamneses.stream()
                .filter(anamnesis -> anamnesis.getId() == idAnamnesis)
                .findFirst()
                .orElseThrow(() -> new AnamnesisNotFoundException());
    }

    public void update(Anamnesis updatedAnamnesis) {
        Anamnesis existingAnamnesis = findById(updatedAnamnesis.getId());
        anamneses.set(anamneses.indexOf(existingAnamnesis), existingAnamnesis);
    }

}
