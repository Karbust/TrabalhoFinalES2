package interfaces;

import types.Ebook;

import java.util.ArrayList;

public interface EbookInterface {
    ArrayList<Ebook> lerEbooks();

    Ebook lerEbook(Integer id);

    boolean registarEbook(Ebook ebook);

    boolean atualizarEbook(Integer id, Ebook ebook);

    boolean apagarEbook(Integer id);
}
