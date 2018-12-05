package br.com.donini.jpos.q2;

import org.jpos.iso.*;
import org.jpos.q2.Q2;
import org.jpos.q2.iso.QMUX;
import org.jpos.q2.iso.QServer;
import org.jpos.util.NameRegistrar;

import java.io.IOException;
import java.util.EventObject;

public class Teste {

    public static void main(String[] args) throws NameRegistrar.NotFoundException, InterruptedException, ISOException, IOException {
        Q2 q2 = new Q2();
        q2.start();
        Thread.sleep(2000);
        ISOServer serverChannel = (ISOServer) NameRegistrar.get("server.xml-server-8000");
        ISOMsg isoMsg = new ISOMsg("0800");
        isoMsg.set(11, "000001");
        isoMsg.set(41, "00000001");
        isoMsg.set(70, "301");
        serverChannel.addISORequestListener(new ISORequestListener() {
            public boolean process(ISOSource isoSource, ISOMsg isoMsg) {
                System.out.println(isoMsg);
                return false;
            }
        });
        ISOChannel isoChannel = serverChannel.getLastConnectedISOChannel();
        isoChannel.send(isoMsg);
    }
}
