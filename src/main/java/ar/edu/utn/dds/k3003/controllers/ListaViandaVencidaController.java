package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.dtos.EstadoViandaEnum;
import ar.edu.utn.dds.k3003.facades.dtos.ViandaDTO;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.json.JSONObject;

public class ListaViandaVencidaController implements Handler {
    private Fachada fachada;

    public ListaViandaVencidaController(Fachada fachada) {
        this.fachada = fachada;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String qr = ctx.pathParam("qr");

        JSONObject resultado = new JSONObject();  // Objeto JSON para el resultado

        try {
            ViandaDTO vianda = fachada.buscarXQR(qr);
            boolean viandaVencida = vianda.getEstado().equals(EstadoViandaEnum.VENCIDA);
            resultado.put("resultado", viandaVencida);  // Agregar resultado al objeto JSON
            ctx.result(resultado.toString()).contentType("application/json");
        } catch (IllegalArgumentException e) {
            resultado.put("resultado", false);  // Si no se encuentra la vianda, devuelve falso
            ctx.status(404);
        }
    }
}
