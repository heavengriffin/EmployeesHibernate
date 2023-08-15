package db;

import java.util.List;

public interface Dao<E> {

    void insert(E entity);

    void update(E entity);

    void delete(E entity);

    List<E> getAll();

    E getEmployee (int id);

    List<E> searchByName(String name);

    List<E> searchBySurname(String surname);

    List<E> searchByAge(int age);

    List<E> searchByAddress(String address);

    List<E> searchBySalary(int salary);


}
