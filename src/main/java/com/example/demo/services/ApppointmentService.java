package com.example.demo.services;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ApppointmentService<T, I> {
   T create(T entity) throws IOException, InterruptedException, ExecutionException;

   T findById(String id) throws IOException, InterruptedException, ExecutionException;

   List<T> findAll() throws Exception;

   T update(String id, T entity);

   void delete(I id);
}
