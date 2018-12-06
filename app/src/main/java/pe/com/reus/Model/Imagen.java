package pe.com.reus.Model;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import pe.com.reus.Globals;

public class Imagen {

    public void grabarFoto(Foto foto) {

        int fotosCantidad = 0;
        String mensaje = "";

        String SOAP_ACTION = "";
        String METHOD_NAME = "";
        String NAMESPACE = "";
        String URL = "";
        String TABLA = "";
        String TOKEN = "";

        fotosCantidad = foto.getFotosCantidad();

        try {

            SOAP_ACTION = Globals.SOAP_ACTION;
            METHOD_NAME = Globals.METHOD_NAME;
            NAMESPACE = Globals.NAMESPACE;
            URL = Globals.URL;
            TABLA = Globals.TABLA_PATIO;
            TOKEN = Globals.TOKEN;

            String fotoRuta = "";
            String result = "0";

            //
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            HttpTransportSE transport = new HttpTransportSE(URL, 3000);
            //

            for (int x = 1; x <= fotosCantidad; x++) {

                if (x == 1) {
                    fotoRuta = foto.getFotoRuta1();
                }

                if (x == 2) {
                    fotoRuta = foto.getFotoRuta2();
                }

                if (x == 3) {
                    fotoRuta = foto.getFotoRuta3();
                }

                if (x == 4) {
                    fotoRuta = foto.getFotoRuta4();
                }

                if (x == 5) {
                    fotoRuta = foto.getFotoRuta5();
                }

                ////////////
                try {
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                    request.addProperty("codigo_imagen", String.valueOf(x));
                    request.addProperty("cadena", fotoRuta);
                    request.addProperty("tabla", TABLA);
                    request.addProperty("token", TOKEN);
                    soapEnvelope.setOutputSoapObject(request);
                    soapEnvelope.dotNet = false;

                    try {
                        result = "0";
                        transport.call(SOAP_ACTION, soapEnvelope);

                        SoapPrimitive resultSOAP = (SoapPrimitive) soapEnvelope.getResponse();
                        result = resultSOAP.toString();

                        if (result.equals("0")) {
                            throw new Exception();
                        }
                    } catch (SocketTimeoutException e) {
                        result = "0";
                        mensaje = "Socket: " + e.getMessage().toString();
                        e.printStackTrace();
                        //Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                    } catch (XmlPullParserException e) {
                        result = "0";
                        mensaje = "Xml: " + e.getMessage().toString();
                        e.printStackTrace();
                        //Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        result = "0";
                        mensaje = "IO: " + e.getMessage().toString();
                        e.printStackTrace();
                        //Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                    } catch (InterruptedException e) {
                        result = "0";
                        mensaje = "Inter: " + e.getMessage().toString();
                        e.printStackTrace();
                        //Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        result = "0";
//                            mensaje = "Excep1: " + e.getMessage().toString();
                        e.printStackTrace();
                        //Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                    }

                    if (result.equals("0")) {
                        mensaje = "Break - " + mensaje;
                        break;
                    }

                } catch (Exception e) {
                    result = "0";
                    mensaje = "Excep2: " + e.getMessage().toString();
                    e.printStackTrace();
                    //Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                }
                ////////////

            }

        } catch (Exception ex) {

            mensaje = "Exception / daReport.reportMovilImagen : " + ex.getMessage();
            //Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();

        }

    }

}
