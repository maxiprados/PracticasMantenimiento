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
        String expected = "Club --> [  ]";

        //Act: Creamos el objeto
        ClubDeportivo club = new ClubDeportivo(name, n);

        //Assert: Verificamos que no sea nulo
        assertNotNull(club);
        assertEquals(club.toString(), expected);
    }


    @Test
    @DisplayName("Constructor de club deportivo sin numero de grupos")
    void clubDeportivo_SinGrupos() throws ClubException {
        //Arrange: no es necesario preparar datos
        String name = "Club";
        String expected = "Club --> [  ]";
        

        //Act: Creamos el objeto
        ClubDeportivo club = new ClubDeportivo(name);

        //Assert: Realizamos las verificaciones
        assertNotNull(club);
        assertEquals(club.toString(), expected);
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
        Grupo grupo = new Grupo("Futbol", "Deporte", 10, 0, 10.0);
        String cadenaEsperada = "Club --> [ (Futbol - Deporte - 10.0 euros - P:10 - M:0) ]";

        //Act: Añadimos el grupo
        club.anyadirActividad(grupo);

        //Assert: Comprobamos que el grupo se ha añadido correctamente,
        // para ello empleamos comparacion de cadenas de caracteres del toString, que recorre todos los grupos en su lista
        assertEquals(club.toString(),cadenaEsperada);
    }
    
    @Test
    @DisplayName("Insercion correcta de un grupo existente en un club deportivo")
    void anyadirGrupoExistente() throws ClubException{
        //Arrange: Preparamos los datos a usar
        ClubDeportivo club = new ClubDeportivo("Club");
        Grupo futbol = new Grupo("Futbol", "Deporte", 10, 0, 10.0);
        club.anyadirActividad(futbol);
        Grupo futbolNuevo = new Grupo("Futbol", "Deporte", 20, 0, 10.0);

        //Act: Añadimos el grupo nuevo probando el anyadirActividad con un grupo ya existente
        club.anyadirActividad(futbolNuevo);

        //Assert: Comprobamos que el grupo se ha añadido correctamente, sustituyendo el antiguo num de plazas por el nuevo
        assertEquals(club.toString(),"Club --> [ (Futbol - Deporte - 10.0 euros - P:20 - M:0) ]");

    }


    @Test
    @DisplayName("Añadir grupo con datos insuficientes en el array")
    public void anyadirGrupo_ArrayDatosInsuficientes() throws ClubException{
        //Arrange: Preparamos los datos
        String name="Club";
        ClubDeportivo club = new ClubDeportivo(name);
        String[] datos = {"Futbol", "Deporte", "10", "0"}; //Faltan datos
        
        //Act & Assert: Añadimos el grupo al club con los datos del array (datos insuficientes)
        assertThrows(ClubException.class,() -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("Añadir mas grupos que los definidos en el constructor")
    public void anyadirGrupo_MasGruposQueNGruposDefinidos() throws ClubException{
        //Arrange: Preparamos los datos
        String name="Club";
        ClubDeportivo club = new ClubDeportivo(name, 1);
        Grupo grupo1 = new Grupo("Futbol", "Deporte", 10, 0, 1);
        Grupo grupo2 = new Grupo("Basketbol", "Deporte", 10, 0, 1);

        club.anyadirActividad(grupo1);

        String expected = "ERROR: tamaño maximo de grupos ha sido completado";

        //Act & Assert: Añadimos los grupos (mas de los que deberia haber)
        Exception exception = assertThrows(ClubException.class,() -> club.anyadirActividad(grupo2));
        assertEquals(expected, exception.getMessage());
    }




    @Test
    @DisplayName("Inserciones correctas de grupos pasando como argumento el array de datos del grupo")
    public void anyadirActividadesArgArray() throws ClubException{
        //Arrange: Preparamos los datos
        ClubDeportivo club = new ClubDeportivo("Club");
        String[] datosFutbol = {"Fut", "Futbol", "10", "0", "10.0"};
        String[] datosBaloncesto = {"Bal", "Baloncesto", "20", "0", "20.0"};
        String expected = "Club --> [ (Fut - Futbol - 10.0 euros - P:10 - M:0), (Bal - Baloncesto - 20.0 euros - P:20 - M:0) ]";


        //Act: añadimos el grupo al club con los datos del array
        club.anyadirActividad(datosFutbol);
        club.anyadirActividad(datosBaloncesto);

        //Assert: Comprobamos que el grupo se ha añadido correctamente
        assertEquals(club.toString(),expected);
    }


    @Test
    @DisplayName("Insercion de un grupo con formato de número incorrecto en el array que se pasa por parametro")
    public void anyadirActividadArgArrayFormatoIncorrecto() throws ClubException{
        //Arrange: Preparamos los datos
        ClubDeportivo club = new ClubDeportivo("Club");
        String[] datos = {"Futbol", "Deporte", "Fallo", "0", "10.0"};

        //Act & Assert: Añadimos el grupo al club con los datos del array (datos erróneos)
        assertThrows(ClubException.class,() -> club.anyadirActividad(datos));
    }


    @Test
    @DisplayName("Calculo correcto de plazas libres en una actividad")
    public void plazasLibresCorrecto() throws ClubException{
        //Arrange: Preparamos los datos a utilizar
        ClubDeportivo club = new ClubDeportivo("Club");
        String[] datosFutbol = {"FutAdulto", "Futbol", "10", "0", "10.0"};
        String[] datosFutbol2 = {"FutInfantil", "Futbol", "20", "5", "10.0"};
        String[] datosBaloncesto = {"Bsk", "Baloncesto", "30", "5", "20.0"};

        club.anyadirActividad(datosFutbol);
        club.anyadirActividad(datosFutbol2);
        club.anyadirActividad(datosBaloncesto);
        int plazasLibres;

        //Act: Calculamos las plazas libres de la actividad Futbol
        plazasLibres = club.plazasLibres("Futbol");

        //Assert: Comprobamos que el cálculo es correcto
        assertEquals(plazasLibres,25);

    }


    @Test
    @DisplayName("Matriculación de personas en una actividad en la que no quedan plazas")
    public void matricularSinPlazas() throws ClubException{
        //Arrange: Preparamos los datos
        ClubDeportivo club = new ClubDeportivo("Club");
        String[] datosFutbol = {"Fut", "Futbol", "10", "7", "10.0"};
        club.anyadirActividad(datosFutbol);

        //Act & Assert: Matriculamos a 5 personas en una actividad con 0 plazas libres
        assertThrows(ClubException.class,() -> club.matricular("Futbol", 5));
    }

    @Test
    @DisplayName("Matriculación de personas en una actividad con plazas libres")
    public void matricularConPlazas() throws ClubException{
        //Arrange: Preparamos los datos
        ClubDeportivo club = new ClubDeportivo("Club");
        String[] datosBaloncesto = {"Bsk", "Baloncesto", "10", "0", "10.0"};
        String[] datosFutbol = {"FutInfantil", "Futbol", "10", "7", "10.0"};
        String[] datosFutbol2 = {"FutAdulto", "Futbol", "20", "0", "20.0"};
        club.anyadirActividad(datosBaloncesto);
        club.anyadirActividad(datosFutbol);
        club.anyadirActividad(datosFutbol2);
        String expected = "Club --> [ (Bsk - Baloncesto - 10.0 euros - P:10 - M:0), (FutInfantil - Futbol - 10.0 euros - P:10 - M:10), (FutAdulto - Futbol - 20.0 euros - P:20 - M:1) ]";

        //Act: Matriculamos a 4 personas en una actividad con plazas libres
        club.matricular("Futbol", 4);

        //Assert: Comprobamos que se han matriculado correctamente
        assertEquals(club.toString(),expected);
    }

    @Test
    @DisplayName("Matriculación de personas en una actividad con plazas libres y fin de numero de pesonas")
    public void matricularConPlazasSinPersonasRestantes() throws ClubException{
        //Arrange: Preparamos los datos
        ClubDeportivo club = new ClubDeportivo("Club");
        String[] datosBaloncesto = {"Bsk", "Baloncesto", "10", "0", "10.0"};
        String[] datosFutbol = {"FutInfantil", "Futbol", "10", "7", "10.0"};
        String[] datosFutbol2 = {"FutAdulto", "Futbol", "20", "0", "20.0"};
        club.anyadirActividad(datosBaloncesto);
        club.anyadirActividad(datosFutbol);
        club.anyadirActividad(datosFutbol2);
        String expected = "Club --> [ (Bsk - Baloncesto - 10.0 euros - P:10 - M:10), (FutInfantil - Futbol - 10.0 euros - P:10 - M:7), (FutAdulto - Futbol - 20.0 euros - P:20 - M:0) ]";

        //Act: Matriculamos a 10 personas en una actividad con plazas libres
        club.matricular("Baloncesto", 10);

        //Assert: Comprobamos que se han matriculado correctamente
        assertEquals(club.toString(),expected);
    }


    @Test
    @DisplayName("Obtencion de los ingresos de un club deportivo")
    public void ingresosCorrectos() throws ClubException{
        //Arrange: Preparamos los datos a emplear

        ClubDeportivo club = new ClubDeportivo("Club");
        String[] datosBaloncesto = {"Bsk", "Baloncesto", "10", "0", "10.0"};
        String[] datosFutbol = {"FutInfantil", "Futbol", "10", "7", "10.0"};

        club.anyadirActividad(datosBaloncesto);
        club.anyadirActividad(datosFutbol);

        double ingresosExpected = 70;
        double cantidadObtenida;

        //Act: Calculamos los ingresos del club
        cantidadObtenida = club.ingresos();

        //Assert: Comprobamos que los ingresos son los esperados
        assertEquals(cantidadObtenida,ingresosExpected);
    }
    

}
