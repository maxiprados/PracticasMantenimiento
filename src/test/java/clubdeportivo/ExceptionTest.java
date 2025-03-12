package clubdeportivo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ExceptionTest {
    //Codigo para que en el jacoco salga el 100% de prueba

    @Test
    @DisplayName("La excepción ClubException puede ser instanciada sin mensaje")
    public void testClubExceptionSinMensaje() {
        // Arrange & Act
        ClubException exception = new ClubException();

        // Assert
        assertNotNull(exception);
        assertNull(exception.getMessage()); // No debe tener mensaje
    }
}
