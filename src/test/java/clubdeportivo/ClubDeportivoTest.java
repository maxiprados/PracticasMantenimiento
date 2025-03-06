package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    

    @Test
    @DisplayName("Constructor de club deportivo crea objeto correctamente")
    void clubDeportivo_Correcto() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club", 1);
        assertNotNull(club);
    }


    @Test
    @DisplayName("Constructor de club deportivo sin numero de grupos")
    void clubDeportivo_SinGrupos() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club");
        assertNotNull(club);
    }



    @Test
    @DisplayName("Constructor de club deportivo con grupos 0")
    void clubDeportivo_0Grupos() {
        assertThrows(ClubException.class,() -> new ClubDeportivo("Club", 0));
    }

    @Test
    @DisplayName("Constructor de club deportivo con grupos negativos")
    void clubDeportivo_NegativoGrupos() {
        assertThrows(ClubException.class,() -> new ClubDeportivo("Club", -1));
    }

    @Test
    @DisplayName("Añadir grupo nulo a un club deportivo")
    void anyadirGrupoNulo() throws ClubException{
        //Creamos un club al que vamos a añadirle un grupo
        ClubDeportivo club = new ClubDeportivo("Club");
        //Creamos un grupo
        Grupo grupo = null;
        //Añadimos el grupo al club
        assertThrows(ClubException.class,() -> club.anyadirActividad(grupo));
        

        
    }







}
