package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    

    @Test
    @DisplayName("Constructor de club deportivo crea objeto correctamente")
    void clubDeportivo_Correcto() throws ClubException {
        //Arr: Preparamos los datos
        String name="Club";
        int n=1;

        //Act: Creamos el objeto
        ClubDeportivo club = new ClubDeportivo(name, n);

        //Assert: Verificamos que no sea nulo
        assertNotNull(club);
    }


    @Test
    @DisplayName("Constructor de club deportivo sin numero de grupos")
    void clubDeportivo_SinGrupos() throws ClubException {
        //Arrange: no es necesario preparar datos
        String name = "Club";
        

        //Act: Creamos el objeto
        ClubDeportivo club = new ClubDeportivo(name);

        //Assert: Realizamos las verificaciones
        assertNotNull(club);
    }



    @Test
    @DisplayName("Constructor de club deportivo con grupos 0")
    void clubDeportivo_0Grupos() {
        //Arrange: Preparamos los datos
        String name="Club";
        int n=0; //Dato no válido
        

        //Act & Assert: Creamos el objeto y comprobamos si lanza la excepción
        assertThrows(ClubException.class,() -> new ClubDeportivo(name, n));
    }

    @Test
    @DisplayName("Constructor de club deportivo con grupos negativos")
    void clubDeportivo_NegativoGrupos() {
        //Arrange: Preparamos los parámetros a introducir en el constructor
        String name="Club";
        int n = -1; //Dato negativo, NO VÁLIDO
        
        
        //Act & Assert : Creamos el objeto y comprobamos si lanza la excepción
        assertThrows(ClubException.class,() -> new ClubDeportivo(name, n));
    }

    @Test
    @DisplayName("Añadir grupo nulo a un club deportivo")
    void anyadirGrupoNulo() throws ClubException{
        //Arrange: Preparamos los datos
        String name="Club";
        Grupo grupo = null;
        
        
        //Act & Assert: Creamos el objeto y comprobamos si lanza la excepción
        ClubDeportivo club = new ClubDeportivo(name);
        assertThrows(ClubException.class,() -> club.anyadirActividad(grupo));
        

        
    }


    @Test
    @DisplayName("Insercion correcta de un grupo no existente en un club deportivo")
    void anyadirGrupoNoExistente() throws ClubException{
        //Arrange: Preparamos los datos
        String name="Club";
        ClubDeportivo club = new ClubDeportivo(name);
        String codigo = "Futbol";
        String actividad = "Deporte";
        int nplazas = 10;
        int matriculados = 0;
        double tarifa = 10.0;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        String cadenaEsperada = "Club --> [ (Futbol - Deporte - 10.0 euros - P:10 - M:0) ]";

        //Act: Añadimos el grupo
        club.anyadirActividad(grupo);

        //Assert: Comprobamos que el grupo se ha añadido correctamente,
        // para ello empleamos comparacion de cadenas de caracteres del toString, que recorre todos los grupos en su lista
        assertEquals(club.toString(),cadenaEsperada);
    }
    






}
