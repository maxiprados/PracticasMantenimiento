package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {
    
    @Test
    @DisplayName("Constructor de club deportivo con maximo personas por grupo negativo o 0")
    public void clubDeportivoAltoRendimiento_MaximoNegativo() throws ClubException{
        //Arrange: Preparamos los datos
        String name="Club";
        int max=-1;
        int max2=0;
        double incremento=2.0;
        //Act & Assert: Creamos el objeto y comprobamos si lanza la excepción
        assertThrows(ClubException.class,() -> new ClubDeportivoAltoRendimiento(name, max, incremento));
        assertThrows(ClubException.class,() -> new ClubDeportivoAltoRendimiento(name, max2, incremento));
    }



    @Test
    @DisplayName("Constructor de club deportivo con incremento negativo o 0")
    public void clubDeportivoAltoRendimiento_IncrementoNegativo() throws ClubException{
        //Arrange: Preparamos los datos a usar
        String name="Club";
        int max=10;
        double incremento=-2.0;
        double incremento2=0.0;
        
        //Act & Assert: Creamos el objeto y comprobamos si lanza la excepción
        assertThrows(ClubException.class,() -> new ClubDeportivoAltoRendimiento(name, max, incremento));
        assertThrows(ClubException.class,() -> new ClubDeportivoAltoRendimiento(name, max, incremento2));
    }

    @Test
    @DisplayName("Constructor de club deportivo ")
    public void clubDeportivoAltoRendimiento_Correcto() throws ClubException{
        //Arrange: Preparamos los datos a usar
        String name="Club";
        int max=10;
        double incremento=2.0;
        String expected = "Club --> [  ]";
        
        //Act: Creamos el objeto
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento(name, max, incremento);
        
        //Assert: Verificamos que no sea nulo y que el objeto se haya creado correctamente
        assertNotNull(club);
        assertEquals(club.toString(), expected);

    }


    @Test
    @DisplayName("Constructor de club deportivo con tamaño pasado por argumento y maximo negativo o 0")
    public void clubDeportivoAltoRendimiento_TamanoMaximoNegativo() throws ClubException{
        //Arrange: Preparamos los datos a usar
        String name="Club";
        int max=-1;
        int max2=0;
        double incremento=2.0;
        int tam=10;
        
        //Act & Assert: Creamos el objeto y comprobamos si lanza la excepción
        assertThrows(ClubException.class,() -> new ClubDeportivoAltoRendimiento(name, tam, max, incremento));
        assertThrows(ClubException.class,() -> new ClubDeportivoAltoRendimiento(name, tam, max2, incremento));
    }

    @Test
    @DisplayName("Constructor de club deportivo con tamaño pasado por argumento y incremento negativo o 0")
    public void clubDeportivoAltoRendimiento_TamanoIncrementoNegativo() throws ClubException{
        //Arrange: Preparamos los datos a usar
        String name="Club";
        int max=10;
        double incremento=-2.0;
        double incremento2=0.0;
        int tam=10;
        
        //Act & Assert: Creamos el objeto y comprobamos si lanza la excepción
        assertThrows(ClubException.class,() -> new ClubDeportivoAltoRendimiento(name, tam, max, incremento));
        assertThrows(ClubException.class,() -> new ClubDeportivoAltoRendimiento(name, tam, max, incremento2));
    }

    @Test
    @DisplayName("Constructor de club deportivo con tamaño pasado por argumento correcto")
    public void clubDeportivoAltoRendimiento_Tamano() throws ClubException{
        //Arrange: Preparamos los datos a usar
        String name="Club";
        int max=10;
        double incremento=2.0;
        int tam=10;
        String expected = "Club --> [  ]";
        
        //Act: Creamos el objeto
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento(name, tam, max, incremento);
        
        //Assert: Verificamos que no sea nulo y que el objeto se haya creado correctamente
        assertNotNull(club);
        assertEquals(club.toString(), expected);

    }


    @Test
    @DisplayName("Añadir actividad con datos insuficientes")
    public void anyadirActividad_DatosInsuficientes() throws ClubException{
        //Arrange: Preparamos los datos a usar
        String[] datos = {"Fut", "Futbol", "10", "0"};
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club", 10, 2.0);
        
        //Act & Assert: Añadimos la actividad con los datos insuficientes y comprobamos si lanza la excepción
        assertThrows(ClubException.class,() -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("Añadir actividad con formato de número incorrecto")
    public void anyadirActividad_FormatoNumeroIncorrecto() throws ClubException{
        //Arrange: Preparamos los datos a usar
        String[] datos = {"Fut", "Futbol", "FALLO", "0", "10.0"};
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club", 10, 2.0);
        
        //Act & Assert: Añadimos la actividad con el formato de número incorrecto y comprobamos si lanza la excepción
        assertThrows(ClubException.class,() -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("Añadir actividad con más numero de plazas que el permitido")
    public void anyadirActividad_PlazasSolicitadasSuperiorMax() throws ClubException{
        //Arrange: Preparamos los datos a usar
        String[] datos = {"Fut", "Futbol", "15", "0", "10.0"};
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club", 10, 2.0);
        String expected = "Club --> [ (Fut - Futbol - 10.0 euros - P:10 - M:0) ]";
        
        //Act: Anyadimos la actividad al club de alto rendimiento
        club.anyadirActividad(datos);

        //Assert: Nos aseguramos que el numero de plazas sea el maximo pertido del club (10) y no el solicitado (15)
        assertEquals(club.toString(),expected);
    }

    @Test
    @DisplayName("Añadir actividad con menor numero de plazas que el permitido")
    public void anyadirActividad_PlazasSolicitadasInferiorMax() throws ClubException{
        //Arrange: Preparamos los datos a usar
        String[] datos = {"Fut", "Futbol", "5", "0", "10.0"};
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club", 10, 2.0);
        String expected = "Club --> [ (Fut - Futbol - 10.0 euros - P:5 - M:0) ]";

        //Act: Anyadimos la actividad al club de alto rendimiento
        club.anyadirActividad(datos);

        //Assert: Nos aseguramos que el numero de plazas sea el solicitado (5) y no el maximo permitido del club (10)
        assertEquals(club.toString(),expected);
    }


    @Test
    @DisplayName("Calcular ingresos sin grupos")
    public void ingresos_SinGrupos() throws ClubException{
        //Arrange: Preparamos los datos a usar
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club", 10, 2.0);
        double expected = 0.0;
        
        //Act: Calculamos los ingresos del club de alto rendimiento
        double result = club.ingresos();

        //Assert: Nos aseguramos que los ingresos sean 0
        assertEquals(result,expected);
    }

    @Test
    @DisplayName("Calcular ingresos con grupos")
    public void ingresos_ConGrupos() throws ClubException{
        //Arrange: Preparamos los datos a usar
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club", 10, 2.0);
        String[] datos = {"Fut", "Futbol", "10", "5", "10.0"};
        double expected = 51.0;
        
        //Act: Anyadimos la actividad al club de alto rendimiento y calculamos los ingresos
        club.anyadirActividad(datos);
        double result = club.ingresos();

        //Assert: Nos aseguramos que los ingresos sean 100
        assertEquals(result,expected);
    }




}
