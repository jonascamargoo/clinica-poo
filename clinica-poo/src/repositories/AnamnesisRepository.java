package repositories;

import java.util.ArrayList;
import java.util.List;

import Model.Anamnesis;
import exceptions.AnamneseInvalidaException;
import exceptions.AnamneseNaoEncontradaException;

// deveria colocar listas de pacientes aqui?
public class AnamnesisRepository implements IAnamnesis {
    private List<Anamnesis> anamneses;
    private long proxId;
    private static AnamnesisRepository anamnesisRepository;

    public AnamnesisRepository() {
        anamneses = new ArrayList<Anamnesis>();
        proxId = 1;
    }

    public static AnamnesisRepository getInstance() {
        if(anamnesisRepository == null) {
            anamnesisRepository = new AnamnesisRepository();
        }
        return anamnesisRepository;
    }
    

    @Override
    public void add(Anamnesis a) throws AnamneseInvalidaException {
        if(a == null) {
            throw new AnamneseInvalidaException("Dados inválidos");
        } else {
            a.setId(proxId);
            proxId++;
            if(!anamneses.add(a)) {
                throw new AnamneseInvalidaException("Dados inválidos");
            }
        }
        
    }

    @Override
    public List<Anamnesis> list() {
        List<Anamnesis> anamnesesCopia = new ArrayList<Anamnesis>();
        for (int i = 0; i < anamneses.size(); i++) {
            try {
                anamnesesCopia.add(anamneses.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return anamnesesCopia;
    }

    // esse metodo retorna a lista real!
    public List<Anamnesis> getAnamnesisRealList() {
        return anamneses;
    }


    @Override
    public Anamnesis findById(long idAnamnese) {
        for (int i = 0; i < anamneses.size(); i++) {
            if(anamneses.get(i).getId() == idAnamnese) {
                return anamneses.get(i);
            }
        }
        return null;
    }


    public void update(Anamnesis aNova, long id) throws AnamneseNaoEncontradaException {
        if(aNova == null) {
            throw new NullPointerException();
        }
        if(!anamnesisExists(id)) {
            throw new AnamneseNaoEncontradaException();
        }
        for (int i = 0; i < anamneses.size(); i++) {
            if (anamneses.get(i) != null) {
                if (anamneses.get(i).getId() == id) {
                    anamneses.set(i, aNova);
                }
            } 
        }
    }

    public boolean anamnesisExists(long id) {
        for (Anamnesis anamnese : anamneses) {
            if (anamnese.getId() == id) {
                return true;
            }
        }
        return false;
    }

   

    public Anamnesis findAnamnesisByMotherName(String nome, String nomeMae) {       
        for (Anamnesis anamnese : anamneses) {
            if(!homonymExists(nome)) {
                return anamnese;
            } else {
                if(anamnese.getPatient().getMotherName().equals(nomeMae)) {
                    return anamnese;
                }
            }
        }
        return null;
    }


    @Override
    public boolean homonymExists(String nome) {
        String nomeMae;
        for (Anamnesis anamnese : anamneses) {
            if (anamnese.getPatient().getName().equals(nome)) {
                nomeMae = anamnese.getPatient().getMotherName();
                for (Anamnesis anamnese2 : anamneses) {
                    if (anamnese2 != anamnese
                            && anamnese2.getPatient().getName().equals(anamnese.getPatient().getName())
                            && !anamnese2.getPatient().getMotherName().equals(nomeMae)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
