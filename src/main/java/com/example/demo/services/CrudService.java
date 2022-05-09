package com.example.demo.services;

import java.util.List;

public interface CrudService<T, I> {
   T create(T entity);

   T findById(I id);

   List<T> findAll();

   T update(I id, T entity);

   void delete(I id);
}
