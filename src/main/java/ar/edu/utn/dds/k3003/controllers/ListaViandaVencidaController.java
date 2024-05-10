package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.facades.dtos.EstadoViandaEnum;
import ar.edu.utn.dds.k3003.model.Vianda;
import ar.edu.utn.dds.k3003.repositories.ViandaRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.json.JSONObject;
public class ListaViandaVencidaController implements Handler {
    private ViandaRepository repo;

    public ListaViandaVencidaController(ViandaRepository repo) {
        this.repo = repo;
    }

//    @Override
//    public void handle(Context ctx) throws Exception {
//        String qr = ctx.pathParam("qr");
//        Vianda vianda = repo.findByQR(qr);
//
//        if (vianda == null) {
//            ctx.status(404).result("Vianda no encontrada");
//        } else if (vianda.getEstado().equals(EstadoViandaEnum.VENCIDA)) {
//            ctx.result("true, la vianda esta en estado VENCIDA");
//        } else {
//            ctx.result("false, la vianda no esta vencida");
//        }
//    }
//} Devuelve en texto

    @Override
    public void handle(Context ctx) throws Exception {
        String qr = ctx.pathParam("qr");
        Vianda vianda = repo.findByQR(qr);

        JSONObject resultado = new JSONObject();  //objeto JSON para el resultado

        if (vianda == null) {
            resultado.put("resultado", false);
            ctx.status(404);
        } else {
            boolean viandaVencida = vianda.getEstado().equals(EstadoViandaEnum.VENCIDA);
            resultado.put("resultado", viandaVencida);  //agregar resultado al objeto JSON
        }

        ctx.result(resultado.toString()).contentType("application/json"); //la unica forma que devolvio el json bien ctx.json(resultado); devolvia siempre mal
    }

}
