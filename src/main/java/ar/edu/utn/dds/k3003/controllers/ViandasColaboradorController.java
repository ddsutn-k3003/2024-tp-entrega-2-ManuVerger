package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.dtos.ViandaDTO;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;
import java.util.NoSuchElementException;

public class ViandasColaboradorController implements Handler {
    private Fachada fachada;

    public ViandasColaboradorController(Fachada fachada) {
        this.fachada = fachada;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        try {
            Long colaboradorId = Long.valueOf(ctx.queryParam("colaboradorId"));
            Integer mes = Integer.valueOf(ctx.queryParam("mes"));
            Integer anio = Integer.valueOf(ctx.queryParam("anio"));

            List<ViandaDTO> viandas = fachada.viandasDeColaborador(colaboradorId, mes, anio);

            ctx.json(viandas);
        } catch (NumberFormatException e) {
            ctx.status(400).result("Los parametros de la solicitud deben ser numeros enteros.");
        } catch (NoSuchElementException e) {
            ctx.status(404).result("No se encontraron viandas para el colaborador y fecha especificados.");
        }
    }
}
