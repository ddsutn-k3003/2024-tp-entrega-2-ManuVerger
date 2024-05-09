package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.repositories.ViandaRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ListaViandaController implements Handler {
    private ViandaRepository repo;

    public ListaViandaController(ViandaRepository repo) {
        super();
        this.repo = repo;
    }
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.json(repo.getViandas());
    }
}
