package nicolasps.com.github.contracts;

public interface Listable<T> {
    void append(T data);
    void update(int index, T data);
    void insert(int index, T data);
    T remove(int index);
    boolean isEmpty();
    boolean isFull();
    int size();
}
