package ar.edu.utn.dds.k3003.app;

import ar.edu.utn.dds.k3003.controllers.AltaViandaController;
import ar.edu.utn.dds.k3003.repositories.ViandaRepository;
import io.javalin.Javalin;

public class WebApp {
    public static void main(String[] args) {
        ViandaRepository repo = new ViandaRepository();
        Integer port = Integer.parseInt(
                System.getProperty("port", "8080"));
        Javalin app = Javalin.create().start(port);
        app.get("/", ctx -> ctx.result("Hola Mundo"));
        app.post("/viandas", new AltaViandaController(repo));
    }
}

