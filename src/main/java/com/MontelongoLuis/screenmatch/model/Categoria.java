package com.MontelongoLuis.screenmatch.model;

public enum Categoria {

    ACCION("Action","Accion"),
    ROMANCE("Romance","Romance"),
    COMEDIA("Comedy","Comedia"),
    DRAMA("Drama","Drama"),
    CRIMEN("Crime","Crimen");

    private String categoriaOmdb;
    private String categoriaSpanish;

    Categoria(String categoriaOmdb, String categoriaSpanish){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaSpanish = categoriaSpanish;
    }
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
    public static Categoria fromSpanish(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaSpanish.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
}
