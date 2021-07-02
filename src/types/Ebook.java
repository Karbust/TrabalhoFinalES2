package types;

import Exceptions.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.validator.routines.UrlValidator;

import java.net.URL;
import java.util.Objects;

public class Ebook {
    private String hash;
    private Integer tamanho;
    private String link;
    private String titulo;
    private String autor;
    private Editora editora;
    private Integer edicao;
    private Integer ano;
    private Integer paginas;

    public Ebook() { }

    public Ebook(String hash, Integer tamanho, String link, String titulo, String autor, Editora editora, Integer edicao, Integer ano, Integer paginas) throws Exception {
        if (hash == null || tamanho == null || link == null || titulo == null || autor == null || edicao == null || ano == null || paginas == null) {
            throw new NullFieldsException();
        }

        if (hash.length() == 0 || tamanho == 0 || link.length() == 0 || titulo.length() == 0 || autor.length() == 0 || edicao == 0 || ano == 0 || paginas == 0) {
            throw new NullFieldsException();
        }

        if (tamanho < 0 || ano < 0 || paginas < 0 || String.valueOf(ano).length() != 4) {
            throw new InvalidFieldNumberException();
        }

        if (!isValidURL(link)) {
            throw new InvalidLinkException();
        }

        if (titulo.length() < 2 || titulo.length() > 100) {
            throw new InvalidEbookTitleException();
        }

        if (autor.length() < 2 || autor.length() > 70) {
            throw new InvalidAutorException();
        }

        URL url = new URL(link);

        if(!FilenameUtils.getExtension(url.getPath()).equals("pdf") && !FilenameUtils.getExtension(url.getPath()).equals("epub")) {
            throw new InvalidFileFormatException();
        }

        this.hash = hash;
        this.tamanho = tamanho;
        this.link = link;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.edicao = edicao;
        this.ano = ano;
        this.paginas = paginas;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    private static boolean isValidURL(String link) {
        UrlValidator validator = UrlValidator.getInstance();

        return validator.isValid(link.replaceAll(" ", "%20"));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if(!(o instanceof Ebook that))
            return false;

        return new EqualsBuilder()
                .append(this.hash, that.hash)
                .append(this.tamanho, that.tamanho)
                .append(this.link, that.link)
                .append(this.titulo, that.titulo)
                .append(this.autor, that.autor)
                .append(this.editora, editora)
                .append(this.edicao, edicao)
                .append(this.ano, ano)
                .append(this.paginas, paginas)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash);
    }
}
