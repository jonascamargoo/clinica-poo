package Date;

import java.util.ArrayList;
import java.util.List;

import Date.exceptions.AnamneseInvalidaException;
import Date.exceptions.AnamneseNaoEncontradaException;
import Model.Anamnese;

public class RepositorioAnamneseList implements IRepositorioAnamnese {
    private List<Anamnese> anamneses;
    private long proxId;
    private static RepositorioAnamneseList repositorioAnamneseList;

    public RepositorioAnamneseList() {
        anamneses = new ArrayList<Anamnese>();
        proxId = 1;
    }

    public static RepositorioAnamneseList getInstance() {
        if(repositorioAnamneseList == null) {
            repositorioAnamneseList = new RepositorioAnamneseList();
        }
        return repositorioAnamneseList;
    }
    

    @Override
    public void add(Anamnese a) throws AnamneseInvalidaException {
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
    public List<Anamnese> listar() {
        List<Anamnese> anamnesesCopia = new ArrayList<Anamnese>();
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
    public Anamnese buscar(long idAnamnese) {
        for (int i = 0; i < anamneses.size(); i++) {
            if(anamneses.get(i).getId() == idAnamnese) {
                return anamneses.get(i);
            }
        }
        return null;
    }


    public void alterar(Anamnese aNova, long id) throws AnamneseNaoEncontradaException {
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
        for (Anamnese anamnese : anamneses) {
            if (anamnese.getId() == id) {
                return true;
            }
        }
        return false;
    }

   

    public Anamnese buscaAnamnese(String nome, String nomeMae) {       
        for (Anamnese anamnese : anamneses) {
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
        for (Anamnese anamnese : anamneses) {
            if (anamnese.getPaciente().getNome().equals(nome)) {
                nomeMae = anamnese.getPaciente().getNomeMae();
                for (Anamnese anamnese2 : anamneses) {
                    if (anamnese2 != anamnese
                            && anamnese2.getPaciente().getNome().equals(anamnese.getPaciente().getNome())
                            && !anamnese2.getPaciente().getNomeMae().equals(nomeMae)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
