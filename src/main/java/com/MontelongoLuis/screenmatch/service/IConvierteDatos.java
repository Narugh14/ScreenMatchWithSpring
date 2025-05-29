package com.MontelongoLuis.screenmatch.service;

public interface IConvierteDatos {

  <T> T obtenerDatos(String json, Class<T> clase);
}
