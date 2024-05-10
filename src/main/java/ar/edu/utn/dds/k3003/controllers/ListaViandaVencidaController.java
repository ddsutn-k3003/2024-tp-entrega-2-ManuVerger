package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.facades.dtos.EstadoViandaEnum;
import ar.edu.utn.dds.k3003.model.Vianda;
import ar.edu.utn.dds.k3003.repositories.ViandaRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ListaViandaVencidaController implements Handler {
    private ViandaRepository repo;

    public ListaViandaVencidaController(ViandaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String qr = ctx.pathParam("qr");  // Obtener el código QR del contexto
        Vianda vianda = repo.findByQR(qr);  // Buscar la vianda por QR en el repositorio

        if (vianda != null && vianda.getEstado().equals(EstadoViandaEnum.VENCIDA)) {
            ctx.result("true, la vianda esta en estado VENCIDA");  // Devolver true si la vianda está vencida
        } else {
            ctx.result("false, la vianda no existe o no esta vencida");  // Devolver false si no se encuentra la vianda o no está vencida
        }
    }
}
