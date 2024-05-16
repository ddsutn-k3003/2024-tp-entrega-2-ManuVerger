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
        app.get("/viandas/{qr}/vencida", new ListaViandaVencidaController(fachada)); // Devuelve true si la vianda está vencida, false si no se encuentra o no está vencida
        app.patch("/viandas/{qr}", new ModificarHeladeraController(fachada)); // Modifica UNICAMENTE la heladeraId de una vianda por QR mediante un PATCH
        app.get("/viandas/search/findByColaboradorIdAndAnioAndMes", new ViandasColaboradorController(fachada)); // Devuelve las viandas de un colaborador para un mes y año especificados
    }
}


//para probar en postman
//    {
//            "id": 0,
//            "codigoQR": "codigoQR",
//            "fechaElaboracion": "2024-05-09T10:30:00Z",
//            "estado": "PREPARADA",
//            "colaboradorId": 10,
//            "heladeraId": 10
//            }
//POST http://localhost:8080/viandas/
//GET http://localhost:8080/viandas/hhh
//viandas vencidas
//PATCH http://localhost:8080/viandas/hhh body raw json { "heladeraId": 10 }
//GET http://localhost:8080/viandas/search/findByColaboradorIdAndAnioAndMes?colaboradorId=10&anio=2024&mes=5