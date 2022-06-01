package com.example.demo.services;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.demo.config.FirebaseConfig;
import com.example.demo.exeptions.NotFoundException;
import com.example.demo.models.Appointment;
import com.example.demo.models.dto.AppointmentDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class AppointmentServiceImp implements ApppointmentService<AppointmentDTO, AppointmentDTO> {

  @Autowired
  private FirebaseConfig firebase;

  @Value("${firestore.collection.dance}")
  private String danceCollection;

  @Override
  public AppointmentDTO findById(String id) throws IOException, InterruptedException, ExecutionException {
    AppointmentDTO appointment = new AppointmentDTO();
    ApiFuture<DocumentSnapshot> query = firebase
      .firestore()
      .collection(danceCollection)
      .document(id)
      .get();

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
    
    ApiFuture<QuerySnapshot> query = firebase
      .firestore()
      .collection(danceCollection)
      .get();
    
    List<QueryDocumentSnapshot> documents = query.get().getDocuments();

    for (QueryDocumentSnapshot doc : documents) {
      AppointmentDTO object = doc.toObject(AppointmentDTO.class);
      PropertyUtils.setProperty(object, "id", doc.getId());
      result.add(object);
    }

    return result;
  }

  @Override
  public AppointmentDTO create(AppointmentDTO entity) throws IOException {
    Appointment newAppointment = new Appointment();
    newAppointment.setEmail(entity.getEmail());
    newAppointment.setHour(entity.getHour());
    newAppointment.setDate(entity.getDate());

    ApiFuture<DocumentReference> future = firebase.firestore().collection(danceCollection).add(newAppointment);

    
    try {
      entity.setId(future.get().getId());
    } catch (Exception e) {
      throw new Error();
    }
    return entity;
  }

  @Override
  public AppointmentDTO update(String id, AppointmentDTO entity) {
    try {
      AppointmentDTO currAppointment = this.findById(id);

      if (currAppointment == null) {
        throw new NotFoundException();
      }

      currAppointment.setEmail(entity.getEmail());
      currAppointment.setHour(entity.getHour());
      currAppointment.setDate(entity.getDate());

      ApiFuture<WriteResult> future = firebase.firestore().collection(danceCollection).document(id).set(currAppointment);
      
      System.out.println("[Update time]: " + future.get().getUpdateTime());

      return currAppointment;
      
    } catch (Exception e) {
      throw new InternalError();
    }
  }

  @Override
  public void delete(AppointmentDTO id) {
    // TODO Auto-generated method stub
    
  }
  
}
