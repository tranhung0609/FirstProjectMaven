package dao;

import model.User;

public interface DAOInterface<T> {
    public int insert(T t);
    public int update(T t);
    public int delete(T t);
    public T selectById(int id);

}
