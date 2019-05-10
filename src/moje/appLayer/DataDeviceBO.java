package moje.appLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import moje.JpaController.DatadeviceJpaController;
import moje.JpaController.exceptions.NonexistentEntityException;
import moje.entity.Datadevice;
import moje.entity.Dataoutput;

/**
 *
 * @author Honza
 */
public class DataDeviceBO {
  
  private static EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("a33PU");

  public static Datadevice createDadtaDeviceAndOutputs(String name, String building, String note, int outputCout) {
    DatadeviceJpaController cont = new DatadeviceJpaController(emf);
    
    Datadevice ret = new Datadevice();
    ret.setName(name);
    ret.setBuilding(building);
    ret.setNote(note);
    ret.setOutputcount(outputCout);
    List<Dataoutput>dataoutputList = new ArrayList<Dataoutput>();
    ret.setDataoutputList(dataoutputList);
    cont.create(ret);
    for (int i = 1; i <= outputCout; i++) {
      Dataoutput out = new Dataoutput();
      out.setDatadevout(i);
      out.setPhonenumber(0);
      out.setNote("");
      out.setMac("::::");
      out.setDeviceId(ret);
      DataOutputBO.createDataOutput(out);
    }
    return ret;
  }

  public static List<Datadevice> getAllDataDevices() {
    DatadeviceJpaController cont = new DatadeviceJpaController(emf);
    List<Datadevice> ret = cont.findDatadeviceEntities();
    return ret;
  }

  public static Datadevice getDataDeviceByID(Integer id) {
    DatadeviceJpaController cont = new DatadeviceJpaController(emf);
    Datadevice ret = cont.findDatadevice(id);
    return ret;
  }
 
  public static void deleteDataDeviceAndOutpust(Datadevice dataDevice){
    deleteDataDeviceAndOutpust(dataDevice.getId());
  }

  public static void deleteDataDeviceAndOutpust(int id) {
    DatadeviceJpaController cont = new DatadeviceJpaController(emf);
    Datadevice dataDevice = cont.findDatadevice(id);
    List<Dataoutput> outputList = dataDevice.getDataoutputList();
    for (Dataoutput dataoutput : outputList) {
      DataOutputBO.deleteDataOutput(dataoutput);
    }
    try {
      cont.destroy(dataDevice.getId());
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(DataDeviceBO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static Datadevice editdataDevice(int id, String name, String building, String note) {
    DatadeviceJpaController cont = new DatadeviceJpaController(emf);
    Datadevice newDataDevice = new Datadevice();
    Datadevice oldDataDevice = cont.findDatadevice(id);
    newDataDevice.setId(oldDataDevice.getId());
    newDataDevice.setOutputcount(oldDataDevice.getOutputcount());
    newDataDevice.setDataoutputList(oldDataDevice.getDataoutputList());
    //TEST PRÁZDNÝCH HODNOT Z FORMULÁŘE
//    name=null;        // součást testu
//    building=null;    // součást testu
//    note=null;        // součást testu
    if(name==null){
      newDataDevice.setName(oldDataDevice.getName());
    } else { newDataDevice.setName(name);}
    if(building==null){
      newDataDevice.setBuilding(oldDataDevice.getBuilding());
    } else { newDataDevice.setBuilding(building);}
    if(note==null){
      newDataDevice.setNote(oldDataDevice.getNote());
    } else { newDataDevice.setNote(note);}
    try {
      cont.edit(newDataDevice);
    } catch (Exception ex) {
      Logger.getLogger(DataDeviceBO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return newDataDevice;
  }
  
}
