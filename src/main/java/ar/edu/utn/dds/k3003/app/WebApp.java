package ar.edu.utn.dds.k3003.app;

import ar.edu.utn.dds.k3003.controllers.AltaViandaController;
import ar.edu.utn.dds.k3003.controllers.ListaViandaController;
import ar.edu.utn.dds.k3003.controllers.ListaViandaQRController;
import ar.edu.utn.dds.k3003.repositories.ViandaRepository;
import io.javalin.Javalin;

public class WebApp {
    public static void main(String[] args) {
        ViandaRepository repo = new ViandaRepository();
        Integer port = Integer.parseInt(
                System.getProperty("port", "8080"));
        Javalin app = Javalin.create().start(port);
        app.get("/", ctx -> ctx.result("Servicio de Viandas!!!"));
        app.post("/viandas", new AltaViandaController(repo)); //Postea una vianda
        app.get("/viandas", new ListaViandaController(repo)); //devuelve todas las viandas (no hace falta esta api pero dejo para hacer pruebas)
        app.get("/viandas/{qr}", new ListaViandaQRController(repo)); //devuelve vianda x qr y mensaje 404 si no encuentra
    }
}

