//Pedro Scapati Sundblad y Máximo Prados Meléndez - Grupo C
package org.mps.ronqi2;

import java.util.ArrayList;
import java.util.List;

public class RonQI2Silver extends RonQI2{
    
    private int numLecturas;
    private List<Float> lecturasP;
    private List<Float> lecturasS;
    private float thresholdP;
    private float thresholdS;
    public RonQI2Silver() {
        lecturasP = new ArrayList<Float>();
        lecturasS = new ArrayList<Float>();
        thresholdP = 20.0f;
        thresholdS = 30.0f;
        numLecturas=5;
    }

    /* 
     * Obtiene las lecturas de presion y sonido del dispositivo y las almacena en sus respectivos
     * contenedores.
    */
    
  
    //Error 1 en el segundo add -> expresion antigua -> lecturasS.add(disp.leerSensorPresion());
    //Expresion correcta -> lecturasS.add(disp.leerSensorSonido());
    
    public void obtenerNuevaLectura(){
        lecturasP.add(disp.leerSensorPresion());
        if(lecturasP.size()>numLecturas){
            lecturasP.remove(0); 
        }
        lecturasS.add(disp.leerSensorSonido());
        if(lecturasS.size()>numLecturas){
            lecturasS.remove(0); 
        }
    }

    /* 
     * Evalua la apnea del sueno. 
     * - Devuelve true si el promedio de las lecturas de presion y sonido es mayor a los limites 
     *      establecidos
     * - False en otro caso
    */
    
    // Error 2 en el if ->  expresion antigua -> avgP>=thresholdP && avgS > thresholdS;
    // Expresion Correcta -> avgP > thresholdP && avgS > thresholdS

    // Error 3 en la accion que se realiza en el if -> resultado = false;
    // Accion correcta -> resultado = true;
    @Override
    public boolean evaluarApneaSuenyo() {
        boolean resultado;
        Double avgP = lecturasP.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
        Double avgS = lecturasS.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
        
        
        if (avgP > thresholdP && avgS > thresholdS){
            resultado = true;
        }   
        else{
            resultado = false;
        }
        return resultado;
    }

   
    
}
