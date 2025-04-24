package org.mps.boundedqueue;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class ArrayBondedQueueTest {
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
}
