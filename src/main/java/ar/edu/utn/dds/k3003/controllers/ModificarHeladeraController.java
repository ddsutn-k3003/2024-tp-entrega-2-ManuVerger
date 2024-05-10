package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.model.Vianda;
import ar.edu.utn.dds.k3003.repositories.ViandaRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.json.JSONException;
import org.json.JSONObject;

public class ModificarHeladeraController implements Handler {
    private ViandaRepository repo;

    public ModificarHeladeraController(ViandaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String qr = ctx.pathParam("qr");  // obtener el codigo qr del contexto
        Vianda vianda = repo.findByQR(qr);  // busca vianda por qr en el repositorio

        if (vianda != null) {
            try {
                String body = ctx.body();  // cuerpo de la solicitud
                JSONObject jsonBody = new JSONObject(body);  // Convertir la cadena JSON a un objeto JSON
                int heladeraId = jsonBody.getInt("heladeraId");
                vianda.setHeladeraId(heladeraId);
                repo.update(vianda);
                ctx.json(vianda);  // devuelve la vianda actualizada como JSON
            } catch (JSONException | NumberFormatException e) {
                ctx.status(400).result("formato incorrecto para heladeraId");
            }
        } else {
            ctx.status(404).result("Vianda no encontrada");  // Devolver error si no se encuentra la vianda
        }
    }

}
