package org.mps.boundedqueue;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class ArrayBondedQueueTest {
<<<<<<< Updated upstream
    @Nested
    class Constructor {
        @DisplayName("")
        @Test
        void testConstructor() {
            //Arrange
            int capacity = 10;

            //Act
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(capacity);

            //Assert
            assertThat(queue)
                    .isNotNull();

        }

        @DisplayName("")
        @Test
        void testConstructor1() {
            //Arrange
            int capacity = -1;

            //Act & Assert
            assertThatThrownBy(() -> new ArrayBoundedQueue<Integer>(capacity))
                    .isInstanceOf(IllegalArgumentException.class);

        }
    }

    @Nested
    class Put {

        @Test
        @DisplayName("put() tira la excepcion FullBoundedQueueException cuando la cola esta llena")
        void testPutWhenFull() {
            // Arrange
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(1);
            queue.put(42); // llena la cola

            // Act & Assert
            assertThatThrownBy(() -> queue.put(99))
                    .isInstanceOf(FullBoundedQueueException.class)
                    .hasMessageContaining("put: full bounded queue");
        }

        @Test
        @DisplayName("put() tira IllegalArgumentException cuando el valor es nulo")
        void testPutWithNullValue() {
            // Arrange
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);

            // Act & Assert
            assertThatThrownBy(() -> queue.put(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("put: element cannot be null");
        }

        @Test
        @DisplayName("put() inserta el elemento correctamente")
        void testPutValidValue() {
            // Arrange
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(2);

            // Act
            queue.put(10);

            // Assert
            assertThat(queue.size()).isEqualTo(1);
            assertThat(queue.isEmpty()).isFalse();
            assertThat(queue).contains(10);
        }
    }

    @Nested
    class Get {
        @Test
        @DisplayName("get() tira una excepcion cunado la cola esta vacia")
        void testGetWhenEmpty() {
            //Arrange
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(1);

            //Act && Assert
            assertThatThrownBy(() -> queue.get())
                    .isInstanceOf(EmptyBoundedQueueException.class);
        }

        @Test
        @DisplayName("get() obtiene el primer elemento de la cola")
        void testGetCorrect() {
            //Arrange
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);
            queue.put(10);
            queue.put(20);
            queue.put(30);

            //Act
            int value = queue.get();

            //Assert
            assertThat(value).isEqualTo(10);
        }
    }

    @Nested
    class GetLast {
        @Test
        @DisplayName("getLast() retorna el ultimo elemento de la cola")
        void testGetLast() {
            //Arrange
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);
            queue.put(10);
            queue.put(20);
            queue.put(30);


            //Act
            int value = queue.getLast();

            //Assert
            assertThat(value).isEqualTo(0);

        }

    }

    @Nested
    class GetFirst {
        @Test
        @DisplayName("getFirst() retorna el primer elemento de la cola")
        void testGetFirst() {
            //Arrange
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);
            queue.put(10);
            queue.put(20);
            queue.put(30);

            //Act
            int value = queue.getFirst();

            //Assert
            assertThat(value).isEqualTo(0);

        }

    }
=======
    
    @Nested
    public class ArrayBoundedQueueIsFullTest{

        @Test
        @DisplayName("Test isFull() cuando la cola esta vacía")
        public void isFull_ArrayVacio_ReturnsFalse(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            assertThat(queue.isFull()).isFalse();
        }

        @Test
        @DisplayName("Test isFull() con alguna inserción pero sin llegar a la capacidad máxima")
        public void isFull_ArrayConElementos_ReturnsFalse(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            queue.put(1);
            queue.put(2);
            assertThat(queue.isFull()).isFalse();
        }

        @Test
        @DisplayName("Test isFull() cuando la cola está llena")
        public void isFull_ArrayLleno_ReturnsTrue(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            queue.put(1);
            queue.put(2);
            queue.put(3);
            queue.put(4);
            queue.put(5);
            assertThat(queue.isFull()).isTrue();
        }

        @Test
        @DisplayName("Test isFull() cuando la cola está y se elimina un elemento")
        public void isFull_ArrayConElementosYEliminacion_ReturnsFalse(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            queue.put(1);
            queue.put(2);
            queue.put(3);
            queue.put(4);
            queue.put(5);
            queue.get();
            assertThat(queue.isFull()).isFalse();
        }




    }

    @Nested
    public class ArrayBoundedQueueIsEmptyTest{

        @Test
        @DisplayName("Test isEmpty() cuando la cola está vacía")
        public void isEmpty_ArrayVacio_ReturnsTrue(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            assertThat(queue.isEmpty()).isTrue();
        }

        @Test
        @DisplayName("Test isEmpty() con alguna inserción pero sin llegar a la capacidad máxima")
        public void isEmpty_ArrayConElementos_ReturnsFalse(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            queue.put(1);
            queue.put(2);
            assertThat(queue.isEmpty()).isFalse();
        }

        @Test
        @DisplayName("Test isEmpty() cuando la cola está llena")
        public void isEmpty_ArrayLleno_ReturnsFalse(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            queue.put(1);
            queue.put(2);
            queue.put(3);
            queue.put(4);
            queue.put(5);
            assertThat(queue.isEmpty()).isFalse();
        }

        @Test
        @DisplayName("Test isEmpty() cuando la cola tiene un solo elemento y se elimina un elemento")
        public void isEmpty_ArrayConElementosYEliminacion_ReturnsTrue(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            queue.put(1);
            queue.get();
            assertThat(queue.isEmpty()).isTrue();
        }



    }



    @Nested
    public class ArrayBoundedQueueSizeTest{

        @Test
        @DisplayName("Test size() cuando la cola está vacía")
        public void size_ArrayVacio_Returns0(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            assertThat(queue.size()).isEqualTo(0);
        }

        @Test
        @DisplayName("Test size() con alguna inserción pero sin llegar a la capacidad máxima")
        public void size_ArrayConElementos_Returns2(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            queue.put(1);
            queue.put(2);
            assertThat(queue.size()).isEqualTo(2);
        }

        @Test
        @DisplayName("Test size() cuando la cola está llena")
        public void size_ArrayLleno_Returns5(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            queue.put(1);
            queue.put(2);
            queue.put(3);
            queue.put(4);
            queue.put(5);
            assertThat(queue.size()).isEqualTo(5);
        }

        @Test
        @DisplayName("Test size() despues de eliminar un elemento")
        public void size_ArrayConElementosYEliminacion_Returns4(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            queue.put(1);
            queue.put(2);
            queue.put(3);
            queue.put(4);
            queue.put(5);
            queue.get();
            assertThat(queue.size()).isEqualTo(4);
        }


    }

    @Nested
    public class ArrayBoundedQueueIteratorTest{

        @Test
        @DisplayName("Test iterator() para una cola vacia")
        public void iterator_ColaVacia_Returns0(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);

            Iterator<Integer> it = queue.iterator();

            assertThat(it.hasNext()).isFalse();
            assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(()-> {it.next();}).withMessage("next: bounded queue iterator exhausted");

        }

        @Test
        @DisplayName("Test iterator() para una cola con varios elementos")
        public void iterator_ColaConElementos_ReturnsElementHastaUltElemento(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
            queue.put(1);
            queue.put(2);
            queue.put(3);



            Iterator<Integer> it = queue.iterator();

            assertThat(it.hasNext()).isTrue();

            assertThat(it.next()).isEqualTo(1);

            assertThat(it.hasNext()).isTrue();

            assertThat(it.next()).isEqualTo(2);

            assertThat(it.hasNext()).isTrue();

            assertThat(it.next()).isEqualTo(3);

            assertThat(it.hasNext()).isFalse();

            assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(()-> {it.next();}).withMessage("next: bounded queue iterator exhausted");

        }

        @Test
        @DisplayName("Test iterator() para una cola llena")
        public void iterator_ColaLlenaConCapacidad3_ReturnsElementHastaFinal(){
            ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);
            queue.put(1);
            queue.put(2);
            queue.put(3);

            Iterator<Integer> it = queue.iterator();
            
            assertThat(it.hasNext()).isTrue();

            assertThat(it.next()).isEqualTo(1);

            assertThat(it.hasNext()).isTrue();

            assertThat(it.next()).isEqualTo(2);

            assertThat(it.hasNext()).isTrue();

            assertThat(it.next()).isEqualTo(3);

            assertThat(it.hasNext()).isFalse();

            assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(()-> {it.next();}).withMessage("next: bounded queue iterator exhausted");


        }
    }

    
>>>>>>> Stashed changes
}

