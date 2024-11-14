package osotnikov.spring.vault.dto;


import java.time.LocalDateTime;

public record GenericResponse(boolean success, String message, Object data, int code, LocalDateTime timestamp) {
}