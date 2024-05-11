package ar.edu.utn.dds.k3003.app;

import ar.edu.utn.dds.k3003.controllers.*;
import io.javalin.Javalin;

public class WebApp {
    public static void main(String[] args) {
        Fachada fachada = new Fachada();
        Integer port = Integer.parseInt(System.getProperty("port", "8080"));
        Javalin app = Javalin.create().start(port);
        app.get("/", ctx -> ctx.result("Servicio de Viandas!!!"));
        app.post("/viandas", new AltaViandaController(fachada)); // Postea una vianda
        app.get("/viandas/{qr}", new ListaViandaQRController(fachada)); // Devuelve vianda por QR y mensaje 404 si no encuentra
        // app.get("/viandas/{qr}/vencida", new ListaViandaVencidaController(fachada)); // Devuelve true si la vianda está vencida, false si no se encuentra o no está vencida
        app.patch("/viandas/{qr}", new ModificarHeladeraController(fachada)); // Modifica UNICAMENTE la heladeraId de una vianda por QR mediante un PATCH
    }
}
