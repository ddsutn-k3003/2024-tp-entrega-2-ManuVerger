package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.model.Vianda;
import ar.edu.utn.dds.k3003.repositories.ViandaRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

public class AltaViandaController implements Handler {

    private ViandaRepository repo;

    public AltaViandaController(ViandaRepository repo) {
        super();
        this.repo = repo;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Vianda vianda = ctx.bodyAsClass(Vianda.class);
        this.repo.save(vianda);
        ctx.status(HttpStatus.OK);  //ok devuelve codigo 200 created el 201
        ctx.result("Vianda agregada correctamente");
    }

}
