package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.model.Vianda;
import ar.edu.utn.dds.k3003.repositories.ViandaRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ListaViandaQRController implements Handler {
    private ViandaRepository repo;

    public ListaViandaQRController(ViandaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String qr = ctx.pathParam("qr");  // Obtener el código QR del contexto
        Vianda vianda = repo.findByQR(qr);  // Buscar la vianda por QR en el repositorio

        if (vianda != null) {
            ctx.json(vianda);  // Devolver la vianda como JSON si se encuentra
        } else {
            ctx.status(404).result("Vianda no encontrada");  // Devolver un error si no se encuentra la vianda
        }
    }

}
