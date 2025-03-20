package main.java.com.ejemplo.bst;

import java.util.Comparator;
import java.util.List;
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
    // (Estas operaciones se incluirán más adelante para ser realizadas en la segunda
    // sesión de laboratorio de esta práctica)
}
