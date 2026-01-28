package com.challenge.literalura.service;

import com.challenge.literalura.dto.GutendexResponse;

public class GutendexClient {

    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos convierteDatos = new ConvierteDatos();

    public GutendexResponse buscarLibro(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20");
        String json = consumoAPI.obtenerDatos(url);
        return convierteDatos.obtenerDatos(json, GutendexResponse.class);
    }
}
