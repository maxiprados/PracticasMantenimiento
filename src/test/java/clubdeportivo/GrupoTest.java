//Hecho por Maximo Prados Melendez y Pedro Scarpati Sundblad
//Grupo C
package clubdeportivo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrupoTest {
    Grupo grupo;

    @Test
    @DisplayName("El constructor esta bien inicializado")
    public void testConstructorBienInicializado() throws ClubException{
        //Arrange
        String codigoE = "G3";
        String actividadE = "Tenis";
        int plazasE = 10;
        int matriculadosE = 5;
        double tarifaE = 10.0;
        grupo= new Grupo(codigoE, actividadE, plazasE, matriculadosE, tarifaE);


        //Act & Assert
        assertEquals(codigoE, grupo.getCodigo());
        assertEquals(actividadE, grupo.getActividad());
        assertEquals(plazasE, grupo.getPlazas());
        assertEquals(matriculadosE, grupo.getMatriculados());
        assertEquals(tarifaE, grupo.getTarifa());
    }

    @Test
    @DisplayName("Salta error al inicialiarlo con matriculados > nplazas")
    public void testConstructorConMatriculadosMayoresQuePlazas() {
        //Arrange
        String codigo = "G3";
        String actividad = "Tenis";
        int plazas = 10;
        int matriculados = 15;
        double tarifa = 10.0;

        //Act
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, plazas, matriculados, tarifa);
        });

        //Assert

        assertEquals("ERROR: El número de plazas es menor que el de matriculados.", exception.getMessage());
    }

    @Test
    @DisplayName("Salta error al inicializarlo con nplazas negativo")
    public void testConstructorConNplazasNegativos() {
        //Arrange
        String codigo = "G3";
        String actividad = "Tenis";
        int plazas = -1;
        int matriculados = 15;
        double tarifa = 10.0;

        //Act
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, plazas, matriculados, tarifa);
        });

        //Assert
        assertEquals("ERROR: los datos numéricos no pueden ser menores o iguales que 0.", exception.getMessage());
    }

    @Test
    @DisplayName("Salta error al inicializarlo con matriculados negativo")
    public void testConstructorConMatriculadosNegativos() {
        //Arrange
        String codigo = "G3";
        String actividad = "Tenis";
        int plazas = 10;
        int matriculados = -1;
        double tarifa = 10.0;

        //Act
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, plazas, matriculados, tarifa);
        });

        //Assert
        assertEquals("ERROR: los datos numéricos no pueden ser menores o iguales que 0.", exception.getMessage());
    }

    @Test
    @DisplayName("Salta error al inicializarlo con tarifa negativa")
    public void testConstructorConTarifaNegativos() {
        //Arrange
        String codigo = "G3";
        String actividad = "Tenis";
        int plazas = 10;
        int matriculados = 10;
        double tarifa = -1;

        //Act
        Exception exception = assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, plazas, matriculados, tarifa);
        });

        //Assert
        assertEquals("ERROR: los datos numéricos no pueden ser menores o iguales que 0.", exception.getMessage());
    }

    @Test
    @DisplayName("Plazas libres correctamente calculadas")
    public void testPlazasLibres() throws ClubException{
        //Arrange
        grupo = new Grupo("G1","natacion", 20, 10, 15);
        int expected = 10;

        //Act
        int plazasLibres = grupo.plazasLibres();

        //Assert
        assertEquals(expected, plazasLibres);
    }

    @Test
    @DisplayName("Salta error al actualizar mal las plazas por negativo")
    public void testActualizarPlazasNegativas() throws ClubException{
        //Arrange
        grupo = new Grupo("G1","natacion", 20, 10, 15);

        //Act
        Exception exception = assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(-1);
        });

        //Assert
        assertEquals(("ERROR: número de plazas negativo."), exception.getMessage());
    }

    @Test
    @DisplayName("Salta error al actualizar mal las plazas por menos plazas que matriculados")
    public void testActualizarPlazasSobrepasadas() throws ClubException{
        //Arrange
        grupo = new Grupo("G1","natacion", 20, 10, 15);

        //Act
        Exception exception = assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(5);
        });

        //Assert
        assertEquals(("ERROR: número de plazas negativo."), exception.getMessage());
    }

    @Test
    @DisplayName("Las plazas se actualizan al actualizarla")
    public void testActualizarPlazasCorrectamente() throws ClubException{
        //Arrange
        grupo = new Grupo("G1","natacion", 20, 10, 15);
        int plazas;
        int expected = 20;

        //Act
        grupo.actualizarPlazas(20);
        plazas = grupo.getPlazas();

        //Assert
        assertEquals(expected, plazas);
    }

    @Test
    @DisplayName("La matriculación es incorrecta porque se intenta matricular mas personas que plazas")
    public void testMatricularMasPersonasQuePlazas() throws ClubException{
        //Arrange
        Grupo grupo = new Grupo("G1","natacion", 20, 10, 15);
        int n = 20;

        //Act
        Exception exception = assertThrows(ClubException.class, () -> {
            grupo.matricular(n);
        });

        String errorExpected = "ERROR: no hay plazas libres suficientes, plazas libre: "+ grupo.plazasLibres()+ " y matriculas: "+n;
        assertEquals(errorExpected, exception.getMessage());
    }

    @Test
    @DisplayName("La matriculación es incorrecta porque se intenta matricular un numero negativo de personas")
    public void testMatricularPersonasNegativas() throws ClubException{
        //Arrange
        Grupo grupo = new Grupo("G1","natacion", 20, 10, 15);
        int n = -1;

        //Act
        Exception exception = assertThrows(ClubException.class, () -> {
            grupo.matricular(n);
        });

        String errorExpected = "ERROR: no hay plazas libres suficientes, plazas libre: "+ grupo.plazasLibres()+ " y matriculas: "+n;
        assertEquals(errorExpected, exception.getMessage());
    }

    @Test
    @DisplayName("La funcion toString devuelve correctamente el toString")
    public void testtoStringCorrecto() throws ClubException{
        //Arrange
        Grupo grupo = new Grupo("G1","natacion", 20, 10, 15);
        int n = -1;

        //Act
        String expected = "("+ grupo.getCodigo() + " - "+grupo.getActividad()+" - " + grupo.getTarifa() + " euros "+ "- P:" + grupo.getPlazas() +" - M:" +grupo.getMatriculados()+")";
        String tostr = grupo.toString();

        String errorExpected = "ERROR: no hay plazas libres suficientes, plazas libre: "+ grupo.plazasLibres()+ " y matriculas: "+n;
        assertEquals(expected, tostr);
    }

    @Test
    @DisplayName("La funcion equals funciona correctamente igualando dos objetos iguales")
    public void tetsEqualsTrue() throws ClubException{
        //Arrange
        Grupo grupo1 = new Grupo("G1","natacion", 20, 10, 15);
        Grupo grupo2 = new Grupo("G1","natacion", 20, 10, 15);

        //Act
        boolean equals = grupo1.equals(grupo2);

        //Assert
        assertTrue(equals);
    }

    @Test
    @DisplayName("La funcion equals funciona correctamente igualando dos objetos diferentes por el codigo")
    public void testEqualsFalse() throws ClubException{
        //Arrange
        Grupo grupo1 = new Grupo("G1","natacion", 20, 10, 15);
        Grupo grupo2 = new Grupo("G2","natacion", 20, 10, 15);

        //Act
        boolean equals = grupo1.equals(grupo2);

        //Assert
        assertFalse(equals);
    }

    @Test
    @DisplayName("La funcion equals funciona correctamente igualando dos objetos diferentes por la actividad")
    public void testEqualsFalseActividadDiff() throws ClubException{
        //Arrange
        Grupo grupo1 = new Grupo("G1","natacion", 20, 10, 15);
        Grupo grupo2 = new Grupo("G1","voley", 20, 10, 15);

        //Act
        boolean equals = grupo1.equals(grupo2);

        //Assert
        assertFalse(equals);
    }


    @Test
    @DisplayName("La funcion equals funciona correctamente igualando dos objetos diferentes por el codigo y la actividad")
    public void testEqualsFalse2Diff() throws ClubException{
        //Arrange
        Grupo grupo1 = new Grupo("G1","natacion", 20, 10, 15);
        Grupo grupo2 = new Grupo("g2","voley", 20, 10, 15);

        //Act
        boolean equals = grupo1.equals(grupo2);

        //Assert
        assertFalse(equals);
    }

    @Test
    @DisplayName("La funcion equals funciona correctamente igualando dos objetos de diferente tipo")
    public void testEqualsDiffTipo() throws ClubException{
        //Arrange
        Grupo grupo1 = new Grupo("G1","natacion", 20, 10, 15);
        String grupo2 = "No soy un grupo";

        //Act
        boolean equals = grupo1.equals(grupo2);

        //Assert
        assertFalse(equals);
    }

    @Test
    @DisplayName("La funcion hash hace un hash correcto")
    public void testHash() throws ClubException{
        //Arrange
        Grupo grupo = new Grupo("G1","natacion", 20, 10, 15);

        //Act
        int hash = grupo.hashCode();
        int expected = grupo.getCodigo().toUpperCase().hashCode()+grupo.getActividad().toUpperCase().hashCode();

        //Assert
        assertEquals(expected, hash);
    }

}
