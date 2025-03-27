package com.ejemplo.bst;

import java.util.Comparator;
import java.util.List;
import java.net.BindException;
import java.util.ArrayList;

public class BinarySearchTree<T> implements BinarySearchTreeStructure<T> {
    private Comparator<T> comparator;
    private T value;
    private BinarySearchTree<T> left;
    private BinarySearchTree<T> right;

    public String render(){
        String render = "";

        if (value != null) {
            render += value.toString();
        }

        if (left != null || right != null) {
            render += "(";
            if (left != null) {
                render += left.render();
            }
            render += ",";
            if (right != null) {
                render += right.render();
            }
            render += ")";
        }

        return render;
    }

    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.value = null;
        this.left = null;
        this.right = null;
    }

    @Override
    public void insert(T value) {
        if(contains(value)) {
            throw new BinarySearchTreeException("El valor ya existe");
        }

        if(this.value == null) {
            this.value = value;
        }
        else {
            int cmp= comparator.compare(value, this.value);
            if(cmp < 0) {
                if(left == null) {
                    left = new BinarySearchTree<>(comparator);
                    left.insert(value);
                }
                else {
                    left.insert(value);
                }
            }
            else if(cmp > 0) {
                if(right == null) {
                    right = new BinarySearchTree<>(comparator);
                    right.insert(value);
                }
                else {
                    right.insert(value);
                }
            }
        }
    }

    @Override
    public boolean isLeaf() {
        return value != null && left == null && right == null;
    }

    @Override
    public boolean contains(T value) {
        if(this.value == null) {
            return false;
        }
        if(value == null) return false;

        int comp = comparator.compare(value, this.value);
        //Si la comparación da cero es que hemos llegado al valor
        if (comp == 0) return true;
        //Si la comparación da negativo es que tenemos que buscar el valor en el lado izquierdo
        else if (comp < 0) return left != null && left.contains(value);
        //Si la comparación da positivo es que tenemos que buscar el valor en el lado derecho
        else return right != null && right.contains(value);
    }

    @Override
    public T minimum() {
        if(value == null) return null;
        if(left == null) return value;
        else return left.minimum();
    }

    @Override
    public T maximum() {
        if(value == null) return null;
        if(right == null) return value;
        else return right.maximum();
    }

    //Para resolver este metodo usaremos busqueda binaria, cuando encontremos el valor marcaremos ese arbol como nulo,
    //desvinculandolo del arbol

    @Override
    public void removeBranch(T value){
        if(value == null) return;

        int comp = comparator.compare(value, this.value);
        if (comp == 0) {
            this.value = null;
            this.left = null;
            this.right = null;
            return;
        }
        if (comp < 0 && left != null) {
             left.removeBranch(value);
        }
        if (comp > 0 && right != null) {
             right.removeBranch(value);
        }
    }

    @Override
    public int size() {
        if(value == null) return 0;
        int l = left == null ? 0 : left.size();
        int r = right == null ? 0 : right.size();
        return 1 + l + r;
    }

    @Override
    public int depth() {
        if(value == null) return 0;
        int l = left == null ? 0 : left.depth();
        int r = right == null ? 0 : right.depth();
        return 1 + Math.max(l, r);
    }

    // Complex operations

    public void removeValue(T value) {
        if(value==null){
            
        }
        
        if (this.value == null) {
            throw new BinarySearchTreeException("El elemento no se encuentra presente en el árbol");
        }
    
        int comp = comparator.compare(value, this.value);
    
        if (comp < 0) {
            // Buscar en el subárbol izquierdo
            if (left == null) {
                throw new BinarySearchTreeException("El elemento no se encuentra presente en el árbol");
            }
            left.removeValue(value);
            // Eliminar la rama izquierda si queda vacía
            if (left.value == null) {
                left = null;
            }
        } else if (comp > 0) {
            // Buscar en el subárbol derecho
            if (right == null) {
                throw new BinarySearchTreeException("El elemento no se encuentra presente en el árbol");
            }
            right.removeValue(value);
            // Eliminar la rama derecha si queda vacía
            if (right.value == null) {
                right = null;
            }
        } else {
            // Nodo encontrado
            if (isLeaf()) {
                // Caso 1: El nodo es una hoja
                this.value = null;
            } else if (left != null && right != null) {
                // Caso 3: El nodo tiene dos hijos
                T minValue = right.minimum();
                this.value = minValue;
                right.removeValue(minValue);
                // Eliminar la rama derecha si queda vacía
                if (right.value == null) {
                    right = null;
                }
            } else {
                // Caso 2: El nodo tiene un solo hijo
                BinarySearchTree<T> child = (left != null) ? left : right;
                this.value = child.value;
                this.left = child.left;
                this.right = child.right;
            }
        }
    }
    
    
    
    public List<T> inOrder(){
        List<T> listaOrdenada = new ArrayList<>();
        //Recorremos el arbol es decir, leemos el valor y ejecutamos in order de el arbol izq y del der,

        if(left != null) {
            listaOrdenada.addAll(left.inOrder());
        }
        if(value != null) {
            listaOrdenada.add(value);
        }
        if(right != null) {
            listaOrdenada.addAll(right.inOrder());
        }
        return listaOrdenada;
    }

    public void balance() {
        List<T> listaOrdenada = this.inOrder();
        this.removeBranch(this.value); // Limpiar el árbol actual
        construirBalanceado(listaOrdenada, 0, listaOrdenada.size() - 1);
    }

    private void construirBalanceado(List<T> lista, int inicio, int fin) {
        if (inicio > fin) return;

        int medio = (inicio + fin) / 2;
        this.insert(lista.get(medio)); // Insertar nodo medio

        construirBalanceado(lista, inicio, medio - 1); // Lado izquierdo
        construirBalanceado(lista, medio + 1, fin);    // Lado derecho
    }
}
