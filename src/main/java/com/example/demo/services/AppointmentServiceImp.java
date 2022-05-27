package com.example.demo.services;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.demo.config.FirebaseConfig;
import com.example.demo.models.dto.AppointmentDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppointmentServiceImp implements ApppointmentService<AppointmentDTO, AppointmentDTO> {

  @Autowired
  private FirebaseConfig firebase;

  @Override
  public AppointmentDTO findById(String id) throws IOException, InterruptedException, ExecutionException {
    AppointmentDTO appointment = new AppointmentDTO();
    ApiFuture<DocumentSnapshot> query = firebase.firestore().collection("dance").document(id).get();

    Map<String, Object> result = query.get().getData();
    
    if (result == null) {
      return null;
    }
    
    Object _id = query.get().getId().toString();
    Object email = result.get("email");
    Object hour = result.get("hour");
    Object date = result.get("date");
    
    appointment.setId(_id.toString());
    appointment.setEmail(email.toString());
    appointment.setHour(hour.toString());
    appointment.setDate(date.toString());

    return appointment;
  }

  @Override
  public List<AppointmentDTO> findAll() throws IOException, InterruptedException, ExecutionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    List<AppointmentDTO> result = new ArrayList<AppointmentDTO>();
    
    ApiFuture<QuerySnapshot> query = firebase.firestore().collection("dance").get();
    
    List<QueryDocumentSnapshot> documents = query.get().getDocuments();

    for (QueryDocumentSnapshot doc : documents) {
      AppointmentDTO object = doc.toObject(AppointmentDTO.class);
      PropertyUtils.setProperty(object, "id", doc.getId());
      result.add(object);
    }

    return result;
  }

  @Override
  public AppointmentDTO create(AppointmentDTO entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AppointmentDTO update(AppointmentDTO id, AppointmentDTO entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(AppointmentDTO id) {
    // TODO Auto-generated method stub
    
  }
  
}
