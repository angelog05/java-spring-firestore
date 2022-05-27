package com.example.demo.services;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ApppointmentService<T, I> {
   T create(T entity);

   T findById(String id) throws IOException, InterruptedException, ExecutionException;

   List<T> findAll() throws Exception;

   T update(I id, T entity);

   void delete(I id);
}
