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
    
//    createDataDeviceAndOutputs("DD3", "Uliční 25", "1.NP, dveře 101", 50);
//    getAllDataDevices();
//    readDataDeviceAllParam(1);
    deleteDataDeviceAndOutpusByID(1);

  }
  
  
  
  private static void createNewCaleHeadAndCHOutputs(String name, String building, String note, int outputCout) {
    Cablehead newCH = CableHeadBO.createNewCaleHeadAndCHOutputs(name, building, note, outputCout);
    System.out.println("NEW CableHead:");
    readCableHeadAllParam(newCH.getId());
  }
  
  private static void readCableHeadAllParam(int id) {
  Cablehead newCableHead = CableHeadBO.getCableheadByID(id);
    System.out.println(".....Read all Cablehead Parameters......");
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
    for (Cabheadoutput ch : cableHeadOutputList) {
      System.out.println("CableHeadOutput id= "+ch.getId()+"; output= "+ch.getCabheadout()
              +"; note= "+ch.getNote()+"; phoneNumber= "+ch.getPhonenumber()
              +"; owner cableHead ID= "+ch.getCableheadId());
      }
    }
  }
    
  private static void getAllCableHeads() {
    System.out.println("All Cable Heads:");
    List<Cablehead> ret = CableHeadBO.getAllCableHeads();
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
    System.out.println("........CableHeads without CableHead ID........");
    for (Cabheadoutput cabheadoutput : retL) {
      System.out.println("ID= "+cabheadoutput.getId()+"; out= "+cabheadoutput.getCabheadout()
          +"; note= "+cabheadoutput.getNote()+"; phone number= "+cabheadoutput.getPhonenumber()
          +"; CableHead ID= "+cabheadoutput.getCableheadId());
    }
  }

  private static void deleteOutputsWithoutCableHeadID() {
    CabHeadOutputBO.deleteOutputsWithoutCableHeadID();
  }

  private static void deleteCableHeadAndOutpustByID(int id) {
    CableHeadBO.deleteCableHeadAndOutpustByCableHeadID(id);
    readCableHeadAllParam(id);
    
  }

  private static void findAllCabHeadOutputsByParameter(String attribute, String value) {
    List<Cabheadoutput> retL = CabHeadOutputBO.findAllCabHeadOutputsByParameter(attribute,  value);
    System.out.println("........CableHeads by CableHead ID........");
    for (Cabheadoutput cabheadoutput : retL) {
      System.out.println("ID= "+cabheadoutput.getId()+"; out= "+cabheadoutput.getCabheadout()
          +"; note= "+cabheadoutput.getNote()+"; phone number= "+cabheadoutput.getPhonenumber()
          +"; CableHead ID= "+cabheadoutput.getCableheadId());
    }
  }

  private static void getAllCabHeadsOutputs() {
    System.out.println("All CableHeads Outputs:");
    List<Cabheadoutput> ret = CabHeadOutputBO.getAllCabHeadsOutputs();
    for (Cabheadoutput outs : ret) {
      System.out.println("ID= "+outs.getId()+"; name= "+outs.getCabheadout()
              +"; note= "+outs.getNote()+"; Phone NUmber= "+outs.getPhonenumber()
              +"; outputs cout= "+outs.getCableheadId());
    }
    System.out.println("END__________________________________________________________________________________END");
  }

  private static void createDataDeviceAndOutputs(String name, String building, String note, int outputCout) {
    Datadevice newdataDevice = DataDeviceBO.createDadtaDeviceAndOutputs(name, building, note, outputCout);
    System.out.println("NEW DataDevice:");
    readDataDeviceAllParam(newdataDevice.getId());
  }

  private static void getAllDataDevices() {
    System.out.println("All DataDevices:");
    List<Datadevice> ret = DataDeviceBO.getAllDataDevices();
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
    System.out.println(".....Read all DataDevice Parameters......");
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
    for (Dataoutput out : dataoutputList) {
      System.out.println("Output id= "+out.getId()+"; output number= "+out.getDatadevout()
              +"; note= "+out.getNote()+"; phoneNumber= "+out.getPhonenumber()
              +"; MAC= "+out.getMac()+"; owner DataDevice ID= "+out.getDeviceId());
      }
    }
  }

  private static void deleteDataDeviceAndOutpusByID(int id) {
    DataDeviceBO.deleteDadtaDeviceAndOutpustByID(id);
    readDataDeviceAllParam(id);
  }
  
  
}
