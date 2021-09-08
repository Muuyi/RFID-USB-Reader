/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import javax.swing.JOptionPane;
//import java.io.File;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.OutputStream;
import java.io.IOException;
//import javax.swing.filechooser.FileSystemView;
//import org.usb4java.Context;
//import org.usb4java.Device;
//import org.usb4java.DeviceDescriptor;
//import org.usb4java.DeviceList;
//import org.usb4java.LibUsb;
//import org.usb4java.LibUsbException;
public class Devices {

    public void findDevice() {
        SerialPort []portLists = SerialPort.getCommPorts();
        for(SerialPort port : portLists){
            System.out.println(port.getSystemPortName());
            SerialPort p = port;
            p.setBaudRate(9600);
            p.setNumDataBits(8);
            p.setNumStopBits(1);
            p.setParity(0);
            p.openPort();
            if(p.isOpen()){
                OutputStream outputStream;
                outputStream = p.getOutputStream();
                System.out.println(outputStream);
//               JOptionPane.showMessageDialog(p.getDescriptivePortName().toString()+" -- Success to open!");
            }
        }
        
//        Context context = new Context();
//        int result = LibUsb.init(context);
//        DeviceList list = new DeviceList();
//        result = LibUsb.getDeviceList(null,list);
////        System.out.print(result);
//        if(result < 0) throw new LibUsbException("Unable to get device list",result);
//        try{
//            for(Device device : list){
//                DeviceDescriptor descriptor = new DeviceDescriptor();
//                result = LibUsb.getDeviceDescriptor(device,descriptor);
//                System.out.println(result);
//            }
//        }finally{
////            LibUsb.freeDeviceList(list,true);
//        }
        }
    }
