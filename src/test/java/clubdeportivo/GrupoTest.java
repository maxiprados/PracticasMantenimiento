package clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrupoTest {
    Grupo grupo1;

    @BeforeEach
    void setUp() throws ClubException{
        grupo1 = new Grupo("G1","natacion", 20, 10, 15);
    }

    @Test
    @DisplayName("El constructor esta bien inicializado")
    public void testConstructorBienInicializado() throws ClubException{
        assertEquals("G1", grupo1.getCodigo());
        assertEquals("natacion", grupo1.getActividad());
        assertEquals(20, grupo1.getPlazas());
        assertEquals(10, grupo1.getMatriculados());
        assertEquals(15, grupo1.getTarifa());
    }

    @Test
    @DisplayName("Salta error al inicialiarlo con matriculados > nplazas")
    public void testConstructorConMatriculadosMayoresQuePlazas() {
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo("G3", "Tenis", 10, 15, 10.0);
        });
        assertEquals("ERROR: El número de plazas es menor que el de matriculados.", exception.getMessage());
    }

    @Test
    @DisplayName("Salta error al inicializarlo con nplazas negativo")
    public void testConstructorConNplazasNegativos() {
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo("G3", "Tenis", -1, 15, 10.0);
        });
        assertEquals("ERROR: los datos numéricos no pueden ser menores o iguales que 0.", exception.getMessage());
    }

    @Test
    @DisplayName("Salta error al inicializarlo con matriculados negativo")
    public void testConstructorConMatriculadosNegativos() {
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo("G3", "Tenis", 10, -1, 10.0);
        });
        assertEquals("ERROR: los datos numéricos no pueden ser menores o iguales que 0.", exception.getMessage());
    }

    @Test
    @DisplayName("Salta error al inicializarlo con matriculados negativo")
    public void testConstructorConTarifaNegativos() {
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo("G3", "Tenis", 20, 15, -1);
        });
        assertEquals("ERROR: los datos numéricos no pueden ser menores o iguales que 0.", exception.getMessage());
    }

    @Test
    @DisplayName("Plazas libres correctamente calculadas")
    public void testPlazasLibres() throws ClubException{
        assertEquals(10, grupo1.plazasLibres());
    }

    @Test
    @DisplayName("Salta error al actualizar mal las plazas por negativo")
    public void testActualizarPlazas1() throws ClubException{
        Exception exception = assertThrows(ClubException.class, () -> {
            grupo1.actualizarPlazas(-1);
        });
        assertEquals(("ERROR: número de plazas negativo."), exception.getMessage());
    }

    @Test
    @DisplayName("Salta error al actualizar mal las plazas por menos plazas que matriculados")
    public void testActualizarPlazas2() throws ClubException{
        Exception exception = assertThrows(ClubException.class, () -> {
            grupo1.actualizarPlazas(9);
        });
        assertEquals(("ERROR: número de plazas negativo."), exception.getMessage());
    }

    @Test
    @DisplayName("Las plazas se actualizan al actualizarla")
    public void testActualizarPlazas3() throws ClubException{
        grupo1.actualizarPlazas(20);
        assertEquals(20, grupo1.getPlazas());
    }

    @Test
    @DisplayName("La matriculación es correcta")
    public void testMatricular1() throws ClubException{
        grupo1.actualizarPlazas(20);
        assertEquals(20, grupo1.getPlazas());
    }

}
