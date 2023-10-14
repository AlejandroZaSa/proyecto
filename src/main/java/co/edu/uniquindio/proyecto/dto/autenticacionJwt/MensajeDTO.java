package co.edu.uniquindio.proyecto.dto.autenticacionJwt;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}