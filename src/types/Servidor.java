package types;

import Exceptions.InvalidCountryException;
import Exceptions.NullFieldsException;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.*;

public class Servidor {
    private String pais;
    private final HashSet<Ebook> ebooksSemEditora;
    private final HashMap<Editora, HashSet<Ebook>> editoras_livros;

    public Servidor(String pais) throws Exception {
        if (!Arrays.toString(Locale.getISOCountries()).contains(pais)) {
            throw new InvalidCountryException();
        }
        this.pais = pais;
        this.ebooksSemEditora = new HashSet<>();
        this.editoras_livros = new HashMap<>();
    }

    public Servidor(String pais, HashSet<Ebook> ebooksSemEditora) throws Exception {
        if (!Arrays.toString(Locale.getISOCountries()).contains(pais)) {
            throw new InvalidCountryException();
        }
        if (ebooksSemEditora == null) {
            throw new NullFieldsException();
        }
        this.pais = pais;
        this.ebooksSemEditora = ebooksSemEditora;
        this.editoras_livros = new HashMap<>();
    }

    public Servidor(String pais, HashMap<Editora, HashSet<Ebook>> editoras_livros) throws Exception {
        if (!Arrays.toString(Locale.getISOCountries()).contains(pais)) {
            throw new InvalidCountryException();
        }
        if (editoras_livros == null) {
            throw new NullFieldsException();
        }
        this.pais = pais;
        this.ebooksSemEditora = new HashSet<>();
        this.editoras_livros = editoras_livros;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) throws Exception {
        if (!Arrays.toString(Locale.getISOCountries()).contains(pais)) {
            throw new InvalidCountryException();
        }
        this.pais = pais;
    }

    public HashSet<Ebook> getEbooksSemEditora() {
        return ebooksSemEditora;
    }

    public void setEbooksSemEditora(HashSet<Ebook> ebooksSemEditora) throws Exception {
        if (ebooksSemEditora == null) {
            throw new NullFieldsException();
        }
        this.ebooksSemEditora.addAll(ebooksSemEditora);
    }

    public HashMap<Editora, HashSet<Ebook>> getEditoras_livros() {
        return editoras_livros;
    }

    public void setEditoras_livros(HashMap<Editora, HashSet<Ebook>> editoras_livros) throws Exception {
        if (editoras_livros == null) {
            throw new NullFieldsException();
        }
        for (Map.Entry<Editora, HashSet<Ebook>> entry_new : editoras_livros.entrySet()) {
            if (this.editoras_livros.containsKey(entry_new.getKey())) {
                //System.out.println(entry_new.getValue());
                for (Ebook ebook_new : entry_new.getValue()) {
                    this.editoras_livros.get(entry_new.getKey()).add(ebook_new);
                }
            } else {
                this.editoras_livros.put(entry_new.getKey(), entry_new.getValue());
            }
        }
    }

    public HashSet<Ebook> getLivros_editora(Editora editora) {
        if (editora != null) {
            for (Map.Entry<Editora, HashSet<Ebook>> entry : this.editoras_livros.entrySet()) {
                if (entry.getKey() == editora) {
                    return entry.getValue();
                }
            }
        }
        return new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if(!(o instanceof Servidor that))
            return false;

        return new EqualsBuilder()
                .append(this.pais, that.pais)
                .isEquals();
    }
}
