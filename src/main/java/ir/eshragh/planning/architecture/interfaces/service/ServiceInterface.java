package ir.eshragh.planning.architecture.interfaces.service;

import java.util.List;

public interface ServiceInterface<T> {
    T addNewItem(T t);
    T updateItem(T t);
    List<T> deleteItem(T t);
    List<T> findAllItem();
    T findOne(T t);
    T findById(Long id);
}
