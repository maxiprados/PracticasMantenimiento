package org.mps.ronqi2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.mps.dispositivo.Dispositivo;

//ERROR 1 (3/4/2025): la clase se llamaba "ronQI2Silvertest.java" y no "ronQI2SilverTest.java"
public class ronQI2SilverTest {

    
    /*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser. 
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos, 
     * el método inicializar de ronQI2 o sus subclases, 
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */
    @Test
    @DisplayName("Test de inicializar correctamente")
    public void inicializar_ReturnsTrue(){
        //Arrange
        Dispositivo disp = mock(Dispositivo.class);
        RonQI2Silver ronqidossilver = new RonQI2Silver();
        when(disp.conectarSensorPresion()).thenReturn(true);
        when(disp.conectarSensorSonido()).thenReturn(true);
        when(disp.configurarSensorPresion()).thenReturn(true);
        when(disp.configurarSensorSonido()).thenReturn(true);
        ronqidossilver.anyadirDispositivo(disp);

        //Act: Probamos la inicializacion
        boolean res = ronqidossilver.inicializar();

        //Assert: Comprobamos que devuelva true
        verify(disp,times(1)).configurarSensorPresion();
        verify(disp,times(1)).configurarSensorSonido();
        assertTrue(res);

    }

    @Test
    @DisplayName("Test de inicializar fallo por conectar sensor presion")
    public void inicializar_FalloConectarSensorPresion_ReturnsFalse(){
        //Arrange
        Dispositivo disp = mock(Dispositivo.class);
        RonQI2Silver ronqidossilver = new RonQI2Silver();
        when(disp.conectarSensorPresion()).thenReturn(false);
        when(disp.conectarSensorSonido()).thenReturn(true);
        when(disp.configurarSensorPresion()).thenReturn(true);
        when(disp.configurarSensorSonido()).thenReturn(true);
        ronqidossilver.anyadirDispositivo(disp);

        //Act: Probamos la inicializacion
        boolean res = ronqidossilver.inicializar();

        //Assert: Comprobamos que devuelva true
        assertFalse(res);

    }

    @Test
    @DisplayName("Test de inicializar fallo por conectar sensor sonido")
    public void inicializar_FalloConectarSensorSonido_ReturnsFalse(){
        //Arrange
        Dispositivo disp = mock(Dispositivo.class);
        RonQI2Silver ronqidossilver = new RonQI2Silver();
        when(disp.conectarSensorPresion()).thenReturn(true);
        when(disp.conectarSensorSonido()).thenReturn(false);
        when(disp.configurarSensorPresion()).thenReturn(true);
        when(disp.configurarSensorSonido()).thenReturn(true);
        ronqidossilver.anyadirDispositivo(disp);

        //Act: Probamos la inicializacion
        boolean res = ronqidossilver.inicializar();

        //Assert: Comprobamos que devuelva true
        assertFalse(res);

    }

    @Test
    @DisplayName("Test de inicializar fallo por configurar sensor presion")
    public void inicializar_FalloConfigurarSensorPresion_ReturnsFalse(){
        //Arrange
        Dispositivo disp = mock(Dispositivo.class);
        RonQI2Silver ronqidossilver = new RonQI2Silver();
        when(disp.conectarSensorPresion()).thenReturn(true);
        when(disp.conectarSensorSonido()).thenReturn(true);
        when(disp.configurarSensorPresion()).thenReturn(false);
        when(disp.configurarSensorSonido()).thenReturn(true);
        ronqidossilver.anyadirDispositivo(disp);

        //Act: Probamos la inicializacion
        boolean res = ronqidossilver.inicializar();

        //Assert: Comprobamos que devuelva true
        assertFalse(res);

    }

    @Test
    @DisplayName("Test de inicializar fallo por configurar sensor sonido")
    public void inicializar_FalloConfigurarSensorSonido_ReturnsFalse(){
        //Arrange
        Dispositivo disp = mock(Dispositivo.class);
        RonQI2Silver ronqidossilver = new RonQI2Silver();
        when(disp.conectarSensorPresion()).thenReturn(true);
        when(disp.conectarSensorSonido()).thenReturn(true);
        when(disp.configurarSensorPresion()).thenReturn(true);
        when(disp.configurarSensorSonido()).thenReturn(false);
        ronqidossilver.anyadirDispositivo(disp);

        //Act: Probamos la inicializacion
        boolean res = ronqidossilver.inicializar();

        //Assert: Comprobamos que devuelva true
        assertFalse(res);

    }


    /*
     * Un inicializar debe configurar ambos sensores, comprueba que cuando se inicializa de forma correcta (el conectar es true), 
     * se llama una sola vez al configurar de cada sensor.
     */

    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */
    
    @Test
    @DisplayName("Caso Correcto, Un sensor desconectado se vuelve a conectar")
    public void reconectar_dispositivoReconectaCorrectamente_ReturnsTrue(){
        //Arrange
        Dispositivo disp = mock(Dispositivo.class);
        RonQI2Silver ronquidossilver = new RonQI2Silver();
        ronquidossilver.anyadirDispositivo(disp);
        when(disp.conectarSensorPresion()).thenReturn(true);
        when(disp.conectarSensorSonido()).thenReturn(true);

        //Act
        boolean res = ronquidossilver.reconectar();

        //Assert
        assertTrue(res);
        verify(disp,times(1)).conectarSensorPresion();
        verify(disp,times(1)).conectarSensorSonido();
    }

    @Test
    @DisplayName("Caso Incorrecto, Un sensor desconectado falla al reconectarse al sensor presion")
    public void reconectar_dispositivoReconectaMalSensorPresion_ReturnsFalse(){
        //Arrange
        Dispositivo disp = mock(Dispositivo.class);
        RonQI2Silver ronquidossilver = new RonQI2Silver();
        ronquidossilver.anyadirDispositivo(disp);
        when(disp.conectarSensorPresion()).thenReturn(false);
        

        //Act
        boolean res = ronquidossilver.reconectar();

        //Assert
        assertFalse(res);
        verify(disp,times(1)).conectarSensorPresion();
    }

    @Test
    @DisplayName("Caso Incorrectp, Un sensor desconectado falla al reconectarse al sensor de sonido")
    public void reconectar_dispositivoReconectaMalSensorSonido_ReturnsFalse(){
        //Arrange
        Dispositivo disp = mock(Dispositivo.class);
        RonQI2Silver ronquidossilver = new RonQI2Silver();
        ronquidossilver.anyadirDispositivo(disp);
        when(disp.conectarSensorPresion()).thenReturn(true);
        when(disp.conectarSensorSonido()).thenReturn(false);

        //Act
        boolean res = ronquidossilver.reconectar();

        //Assert
        assertFalse(res);
        verify(disp,times(1)).conectarSensorPresion();
        verify(disp,times(1)).conectarSensorSonido();
    }

    @Test
    @DisplayName("Caso Incorrecto, Un sensor ya esta conectada")
    public void reconectar_dispositivoYaConectadoNoReconecta_ReturnsFalse(){
        //Arrange
        Dispositivo disp = mock(Dispositivo.class);
        RonQI2Silver ronquidossilver = new RonQI2Silver();
        ronquidossilver.anyadirDispositivo(disp);
        when(disp.estaConectado()).thenReturn(true);

        //Act
        boolean res = ronquidossilver.reconectar();

        //Assert
        assertFalse(res);

    }

    @Test
    @DisplayName("Comprobar que un RonQI2Silver está conectado")
    public void estaConectado_RonQI2SilverConDispositivoConectado_ReturnsTrue(){
        //Arrange
        RonQI2Silver ronquidossilver = new RonQI2Silver();
        Dispositivo disp = mock(Dispositivo.class);
        when(disp.estaConectado()).thenReturn(true);
        ronquidossilver.anyadirDispositivo(disp);

        //Act
        boolean res = ronquidossilver.estaConectado();

        //Assert
        assertTrue(res);
        verify(disp).estaConectado();
    }

    @Test
    @DisplayName("Comprobar que un RonQI2Silver está desconectado")
    public void estaConectado_RonQI2SilverConDispositivoDesconectado_ReturnsFalse(){
        //Arrange
        RonQI2Silver ronquidossilver = new RonQI2Silver();
        Dispositivo disp = mock(Dispositivo.class);
        when(disp.estaConectado()).thenReturn(false);
        ronquidossilver.anyadirDispositivo(disp);

        //Act
        boolean res = ronquidossilver.estaConectado();

        //Assert
        assertFalse(res);
        verify(disp).estaConectado();
    }

    
    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     * /
    
    

    
     
     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */
}
