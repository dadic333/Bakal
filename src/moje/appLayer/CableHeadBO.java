/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moje.appLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import moje.JpaController.CableheadJpaController;
import moje.entity.Cablehead;
import moje.JpaController.CabheadoutputJpaController;
import moje.JpaController.exceptions.NonexistentEntityException;
import moje.entity.Cabheadoutput;

/**
 *
 * @author Honza
 */
public class CableHeadBO {
  
    private static EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("a33PU");

  public static Cablehead createNewCaleHeadAndCHOutputs(String name, String building, String note, int outputCount) {
    CableheadJpaController cont = new CableheadJpaController(emf);
    CabheadoutputJpaController outscont = new CabheadoutputJpaController(emf);
    Cablehead ret = new Cablehead();
//    System.out.println("\nVýpis ID po  příkazu ____Cablehead ret = new Cablehead();___" + ret.getId()+"\n");  //ověření reference na objekt
    ret.setName(name);
    ret.setBuilding(building);
    ret.setNote(note);
    ret.setOutputcount(outputCount);
    cont.create(ret);
//    System.out.println("\nVýpis ID po  příkazu ____cont.create(ret);___" + ret.getId()+"\n"); //ověření reference na objekt
//  create and fill CableHeadOutputList
    List<Cabheadoutput> cabHeadOutputList = new ArrayList<Cabheadoutput>();
    for(int i=1; i<=outputCount; i++){
      Cabheadoutput newOutput = new Cabheadoutput();
      newOutput.setCabheadout(i);
      newOutput.setPhonenumber(0);
      newOutput.setNote("");
      newOutput.setCableheadId(ret);
      outscont.create(newOutput);
//      cabHeadOutputList.add(newOutput);
    }
    return ret;
  }
  
  public static void createNewCaleHeadAndOutputsNoReturn(String name, String building, String note, int outputCount) {
    CableheadJpaController cont = new CableheadJpaController(emf);
    CabheadoutputJpaController outscont = new CabheadoutputJpaController(emf);
    Cablehead ret = new Cablehead();
    ret.setName(name);
    ret.setBuilding(building);
    ret.setNote(note);
    ret.setOutputcount(outputCount);
    cont.create(ret);
//  create CableHeadOUtputList filled
    List<Cabheadoutput> cabHeadOutputList = new ArrayList<Cabheadoutput>();
    for(int i=1; i<=outputCount; i++){
      Cabheadoutput newOutput = new Cabheadoutput();
      newOutput.setCabheadout(i);
      newOutput.setPhonenumber(0);
      newOutput.setNote("");
      newOutput.setCableheadId(ret);
      outscont.create(newOutput);
      cabHeadOutputList.add(newOutput);
    }
//    ret.setCabheadoutputList(cabHeadOutputList);
  }

  public static Cablehead getCableheadByID(int id) {
    CableheadJpaController cont = new CableheadJpaController(emf);
    Cablehead ret  = cont.findCablehead(id);

    return ret;
  }

  public static List<Cablehead> getAllCableHeads() {
    CableheadJpaController cont = new CableheadJpaController(emf);
    List<Cablehead> ret = cont.findCableheadEntities();

    return ret;
  }
  
  public static void deleteCableHeadAndOutpustByCableHead(Cablehead cableHead) {
    deleteCableHeadAndOutpustByCableHeadID(cableHead.getId());
  }

  public static void deleteCableHeadAndOutpustByCableHeadID(int id) {
    CableheadJpaController cont = new CableheadJpaController(emf);
    Cablehead original = cont.findCablehead(id);
      try {
        cont.destroy(original.getId());
      } catch (NonexistentEntityException ex) {
        Logger.getLogger(CableHeadBO.class.getName()).log(Level.SEVERE, null, ex);
      }
    if (cont.findCablehead(original.getId())==null){ 
      CabHeadOutputBO.deleteOutputsWithoutCableHeadID();
    }

  }
/* OŠETŘENÍ PRÁZDNÝCH HODNOT z dřívější verze
  
//    public static void editPhoneNumber(int id, Integer number, String note, String socketname) {
//    PhonenumberJpaController cont = new PhonenumberJpaController(emf);
//    Phonenumber original = cont.findPhonenumber(id);
//    Phonenumber newp = new Phonenumber();
//    newp.setId(id);
//    if(number==null || original.getPhonenumber().equals(number)){
//      newp.setPhonenumber(original.getPhonenumber());
//    } else{newp.setPhonenumber(number);}
//    if(note==null || original.getNote().equals(note)){
//      newp.setNote(original.getNote());
//    } else{newp.setNote(note);}
//    if(socketname==null || original.getSocketname().equals(socketname)){
//      newp.setSocketname(original.getSocketname());
//    } else{newp.setSocketname(socketname);}
//    newp.setCabheadoutId(original.getCabheadoutId());
//    newp.setDataoutId(original.getDataoutId());
//    newp.setHwpositionList(original.getHwpositionList());
//    try {
//      cont.edit(newp);
//    } catch (Exception ex) {
//      Logger.getLogger(PhoneNumberBO.class.getName()).log(Level.SEVERE, null, ex);
//    }
*/

  public static Cablehead editCableHead(int id, String name, String building, String note) {
    CableheadJpaController cont = new CableheadJpaController(emf);
    Cablehead newCableHead = new Cablehead();
    Cablehead oldCableHead = cont.findCablehead(id);
    newCableHead.setId(oldCableHead.getId());
    //TEST PRÁZDNÝCH HODNOT Z FORMULÁŘE
//    name=null;      // součást testu
//    building=null;  // součást testu
//    note=null;      // součást testu
    if(name==null){
      newCableHead.setName(oldCableHead.getName());
    } else { newCableHead.setName(name);}
    if(building==null){
      newCableHead.setBuilding(oldCableHead.getBuilding());
    } else { newCableHead.setBuilding(building);}
    if(note==null){
      newCableHead.setNote(oldCableHead.getNote());
    } else { newCableHead.setNote(note);}
    newCableHead.setOutputcount(oldCableHead.getOutputcount());
    newCableHead.setCabheadoutputList(oldCableHead.getCabheadoutputList());
      try {
        cont.edit(newCableHead);
      } catch (Exception ex) {
        Logger.getLogger(CableHeadBO.class.getName()).log(Level.SEVERE, null, ex);
      }
    return newCableHead;
  }
  
  
  
}
