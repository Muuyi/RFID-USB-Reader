/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usbreader;

import main.Devices;
import org.usb4java.Context;
import org.usb4java.Device;
import org.usb4java.DeviceList;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

/**
 *
 * @author muuyi
 */
public class UsbReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Devices d = new Devices();
        d.findDevice();
        // TODO code application logic here
//        Context context = new Context();
//        int result = LibUsb.init(context);
//        if(result != LibUsb.SUCCESS) throw new LibUsbException("Unable to initialize libusb.",result);
//        LibUsb.exit(context);
//        public void findDevice(){
//            DeviceList list = new DeviceList();
////            int result LibUsb.getDeviceList(null,list);
//        }
    }
    
}
