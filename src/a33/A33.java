/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a33;

import java.util.ArrayList;
import java.util.List;
import moje.appLayer.CabHeadOutputBO;
import moje.appLayer.CableHeadBO;
import moje.appLayer.DataDeviceBO;
import moje.appLayer.DataOutputBO;
import moje.entity.Cabheadoutput;
import moje.entity.Cablehead;
import moje.entity.Datadevice;
import moje.entity.Dataoutput;

/**
 *
 * @author Honza
 */
public class A33 {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
    
//    getAllCableHeads();
 

//    createNewCaleHeadAndCHOutputs("pokus2", "Českobratrská 25", "5.NP, dveře 519", 20);
 
//    readCableHeadAllParam(1);
//    deleteCableHeadAndOutpustByID(219);
//    getAllCableHeads();
//    findAllCabHeadOutputsWithoutCableHeadID();
//    deleteOutputsWithoutCableHeadID();
//    getAllCabHeadsOutputs();
//    readCableHeadAllParam(5);
    editCableHead(218,"mame 4","zmenena budova a umisteni 4","zmenena poznamka 4");
    
//    createDataDeviceAndOutputs("DD5", "Uliční 25", "1.NP, dveře 101", 30);
//    getAllDataDevices();
//    readDataDeviceAllParam(1);
//    deleteDataDeviceAndOutpusByID(3);
//    getAllDataOutputs();

    
  }
  
  
  
  private static void createNewCaleHeadAndCHOutputs(String name, String building, String note, int outputCout) {
    Cablehead newCH = CableHeadBO.createNewCaleHeadAndCHOutputs(name, building, note, outputCout);
    System.out.println("NEW CableHead:");
    readCableHeadAllParam(newCH.getId());
  }
  
  private static void readCableHeadAllParam(int id) {
  Cablehead newCableHead = CableHeadBO.getCableheadByID(id);
    System.out.println(".....Read Cablehead all Parameters......");
  System.out.println("Výpis kabelové hlavy s ID= "+id);   // cable reading
  System.out.println("id= "+newCableHead.getId()+"; name= "+newCableHead.getName()
          +"; building= "+newCableHead.getBuilding()+"; note= "
          + newCableHead.getNote()+"; outputCount= "+newCableHead.getOutputcount()
          +"; getCabheadoutputList().size()"+newCableHead.getCabheadoutputList().size());
  System.out.println("Má výstupy:");
  // cableHeadList reading
  readCabHeadOutputList(newCableHead.getCabheadoutputList());
  }
    
  private static void readCabHeadOutputList (List<Cabheadoutput> cableHeadOutputList){
  if (cableHeadOutputList.isEmpty()){
    System.out.println("NO CableHeadOutputs!!!");
  }else {
    System.out.println("___________________________-All CableHeads Outputs-______________________________");   
    for (Cabheadoutput ch : cableHeadOutputList) {
      System.out.println("CableHeadOutput id= "+ch.getId()+"; output= "+ch.getCabheadout()
              +"; note= "+ch.getNote()+"; phoneNumber= "+ch.getPhonenumber()
              +"; owner cableHead ID= "+ch.getCableheadId());
      }
    System.out.println("END__________________________________________________________________________________END");
    }
  }
    
  private static void getAllCableHeads() {
    List<Cablehead> ret = CableHeadBO.getAllCableHeads();
    System.out.println("________________________-All Cable Heads-___________________________");
    for (Cablehead cabHead : ret) {
      System.out.println("ID= "+cabHead.getId()+"; name= "+cabHead.getName()
              +"; building= "+cabHead.getBuilding()+"; note= "+cabHead.getNote()
              +"; outputs cout= "+cabHead.getOutputcount()
              +"; output List size= "+cabHead.getCabheadoutputList().size());
    }
    System.out.println("END__________________________________________________________________________________END");
  }

  private static void deleteCableHeadAndOutpustByCableHeadID(int id) {
     CableHeadBO.deleteCableHeadAndOutpustByCableHeadID(id);
  }

  private static void findAllCabHeadOutputsWithoutCableHeadID() {
    List<Cabheadoutput> retL = CabHeadOutputBO.findAllCabHeadOutputsWithoutCableHeadID();
    readCabHeadOutputList(retL);
  }

  private static void deleteOutputsWithoutCableHeadID() {
    CabHeadOutputBO.deleteOutputsWithoutCableHeadID();
  }

  private static void deleteCableHeadAndOutpustByID(int id) {
    CableHeadBO.deleteCableHeadAndOutpustByCableHeadID(id);    
  }

  private static void findAllCabHeadOutputsByParameter(String attribute, String value) {
    List<Cabheadoutput> retL = CabHeadOutputBO.findAllCabHeadOutputsByParameter(attribute, value);
    System.out.println("______________________-CableHeads by CableHead ID-__________________________");
    for (Cabheadoutput cabheadoutput : retL) {
      System.out.println("ID= "+cabheadoutput.getId()+"; out= "+cabheadoutput.getCabheadout()
          +"; note= "+cabheadoutput.getNote()+"; phone number= "+cabheadoutput.getPhonenumber()
          +"; CableHead ID= "+cabheadoutput.getCableheadId());
    }
    System.out.println("END__________________________________________________________________________________END");
  }

  private static void getAllCabHeadsOutputs() {
    List<Cabheadoutput> ret = CabHeadOutputBO.getAllCabHeadsOutputs();
    System.out.println("_____________________-All CableHeads Outputs-________________________");
    for (Cabheadoutput outs : ret) {
      System.out.println("ID= "+outs.getId()+"; name= "+outs.getCabheadout()
              +"; note= "+outs.getNote()+"; Phone NUmber= "+outs.getPhonenumber()
              +"; getCableheadId= "+outs.getCableheadId());
    }
    System.out.println("END__________________________________________________________________________________END");
  }

  private static void createDataDeviceAndOutputs(String name, String building, String note, int outputCout) {
    Datadevice newdataDevice = DataDeviceBO.createDadtaDeviceAndOutputs(name, building, note, outputCout);
    System.out.println("NEW DataDevice:");
    readDataDeviceAllParam(newdataDevice.getId());
  }

  private static void getAllDataDevices() {
    List<Datadevice> ret = DataDeviceBO.getAllDataDevices();
    System.out.println("______________________-All DataDevices-________________________");
    for (Datadevice dev : ret) {
      System.out.println("ID= "+dev.getId()+"; name= "+dev.getName()
              +"; building= "+dev.getBuilding()+"; note= "+dev.getNote()
              +"; outputs cout= "+dev.getOutputcount()
              +"; output List size= "+dev.getDataoutputList().size());
    }
    System.out.println("END__________________________________________________________________________________END");
  }

  private static void readDataDeviceAllParam(Integer id) {
    Datadevice newDataDevice = DataDeviceBO.getDataDeviceByID(id);
    System.out.println(".....Read DataDevice all Parameters......");
    System.out.println("Výpis datového prvku s ID= "+id);   // cable reading
    System.out.println("id= "+newDataDevice.getId()+"; name= "+newDataDevice.getName()
            +"; building= "+newDataDevice.getBuilding()+"; note= "+ newDataDevice.getNote()
            +"; outputCount= "+newDataDevice.getOutputcount()
            +"; getCabheadoutputList().size()"+newDataDevice.getDataoutputList().size());
    System.out.println("Má výstupy:");
    readDataDeviceOutputList(newDataDevice.getDataoutputList());
  }

  private static void readDataDeviceOutputList(List<Dataoutput> dataoutputList) {
  if (dataoutputList.isEmpty()){
    System.out.println("NO CableHeadOutputs!!!");
  }else { 
    System.out.println("________________________-All DataOutputs-___________________________");
    for (Dataoutput out : dataoutputList) {
      System.out.println("Output id= "+out.getId()+"; output number= "+out.getDatadevout()
              +"; note= "+out.getNote()+"; phoneNumber= "+out.getPhonenumber()
              +"; MAC= "+out.getMac()+"; OWNER DataDevice ID= "+out.getDeviceId());
      }
    System.out.println("END__________________________________________________________________________________END");
    }
  }

  private static void deleteDataDeviceAndOutpusByID(int id) {
    DataDeviceBO.deleteDataDeviceAndOutpustByID(id);
  }

  private static void getAllDataOutputs() {
    List<Dataoutput> ret = DataOutputBO.getAllDataOutputs();
    readDataDeviceOutputList(ret);
  }

  private static void editCableHead(int id, String name, String building, String note) {
    System.out.println("_______________editCableHead - VÝPIS PŘED ZMĚNOU___________________");
    readCableHeadAllParam(id);
    Cablehead newCableHead = CableHeadBO.editCableHead(id,name,building,note);
    System.out.println("_______________editCableHead - VÝPIS PO ZMĚNĚ___________________");
    readCableHeadAllParam(newCableHead.getId());
  }
  
  
}
