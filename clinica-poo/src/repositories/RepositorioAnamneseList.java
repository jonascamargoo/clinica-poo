package repositories;

import java.util.ArrayList;
import java.util.List;

import Model.Anamnesis;
import exceptions.AnamneseInvalidaException;
import exceptions.AnamneseNaoEncontradaException;

public class RepositorioAnamneseList implements IRepositorioAnamnese {
    private List<Anamnesis> anamneses;
    private long proxId;
    private static RepositorioAnamneseList repositorioAnamneseList;

    public RepositorioAnamneseList() {
        anamneses = new ArrayList<Anamnesis>();
        proxId = 1;
    }

    public static RepositorioAnamneseList getInstance() {
        if(repositorioAnamneseList == null) {
            repositorioAnamneseList = new RepositorioAnamneseList();
        }
        return repositorioAnamneseList;
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
    public List<Anamnesis> listar() {
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


    @Override
    public Anamnesis buscar(long idAnamnese) {
        for (int i = 0; i < anamneses.size(); i++) {
            if(anamneses.get(i).getId() == idAnamnese) {
                return anamneses.get(i);
            }
        }
        return null;
    }


    public void alterar(Anamnesis aNova, long id) throws AnamneseNaoEncontradaException {
        if(aNova == null) {
            throw new NullPointerException();
        }
        if(!existeAnamnese(id)) {
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

    public boolean existeAnamnese(long id) {
        for (Anamnesis anamnese : anamneses) {
            if (anamnese.getId() == id) {
                return true;
            }
        }
        return false;
    }

   

    public Anamnesis buscaAnamnese(String nome, String nomeMae) {       
        for (Anamnesis anamnese : anamneses) {
            if(!existeHomonimo(nome)) {
                return anamnese;
            } else {
                if(anamnese.getPaciente().getNomeMae().equals(nomeMae)) {
                    return anamnese;
                }
            }
        }
        return null;
    }


    @Override
    public boolean existeHomonimo(String nome) {
        String nomeMae;
        for (Anamnesis anamnese : anamneses) {
            if (anamnese.getPaciente().getName().equals(nome)) {
                nomeMae = anamnese.getPaciente().getNomeMae();
                for (Anamnesis anamnese2 : anamneses) {
                    if (anamnese2 != anamnese
                            && anamnese2.getPaciente().getName().equals(anamnese.getPaciente().getName())
                            && !anamnese2.getPaciente().getNomeMae().equals(nomeMae)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
