package co.edu.udea.nexum.opportunity.common.domain.exception;

public class NexumException extends RuntimeException {
    public NexumException(String message) {
        super(message);
    }

    public NexumException(String message, Throwable cause) {
        super(message, cause);
    }

}
