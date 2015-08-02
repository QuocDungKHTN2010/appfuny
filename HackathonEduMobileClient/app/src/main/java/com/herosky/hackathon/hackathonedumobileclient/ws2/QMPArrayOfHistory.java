package com.herosky.hackathon.hackathonedumobileclient.ws2;

//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 4.1.7.1
//
// Created by Quasar Development at 02-08-2015
//
//---------------------------------------------------



import org.ksoap2.serialization.*;
import java.util.Vector;
import java.util.Hashtable;


public class QMPArrayOfHistory extends Vector< QMPHistory> implements KvmSerializable
{
    
    public QMPArrayOfHistory(){}
    
    public QMPArrayOfHistory(java.lang.Object inObj,QMPExtendedSoapSerializationEnvelope __envelope)
    {
        if (inObj == null)
            return;
        SoapObject soapObject=(SoapObject)inObj;
        int size = soapObject.getPropertyCount();
        for (int i0=0;i0< size;i0++)
        {
            java.lang.Object obj = soapObject.getProperty(i0);
            if (obj!=null && obj instanceof AttributeContainer)
            {
                AttributeContainer j =(AttributeContainer) soapObject.getProperty(i0);
                QMPHistory j1= (QMPHistory)__envelope.get(j,QMPHistory.class);
                add(j1);
            }
        }
}
    
    @Override
    public java.lang.Object getProperty(int arg0) {
        return this.get(arg0)!=null?this.get(arg0):SoapPrimitive.NullNilElement;
    }
    
    @Override
    public int getPropertyCount() {
        return this.size();
    }
    
    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        info.name = "History";
        info.type = QMPHistory.class;
    	info.namespace= "http://schemas.datacontract.org/2004/07/WCFApp.DTO";
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1) {
    }

    @Override
    public String getInnerText() {
        return null;
    }

    @Override
    public void setInnerText(String s) {

    }

}