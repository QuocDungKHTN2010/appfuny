package com.herosky.hackathon.hackathonedumobileclient.ws;

//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 4.1.7.1
//
// Created by Quasar Development at 01-08-2015
//
//---------------------------------------------------



import org.ksoap2.serialization.*;
import java.util.Vector;
import java.util.Hashtable;


public class PBGArrayOfTeacher_Student_Mapping extends Vector< PBGTeacher_Student_Mapping> implements KvmSerializable
{
    
    public PBGArrayOfTeacher_Student_Mapping(){}
    
    public PBGArrayOfTeacher_Student_Mapping(java.lang.Object inObj,PBGExtendedSoapSerializationEnvelope __envelope)
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
                PBGTeacher_Student_Mapping j1= (PBGTeacher_Student_Mapping)__envelope.get(j,PBGTeacher_Student_Mapping.class);
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
        info.name = "Teacher_Student_Mapping";
        info.type = PBGTeacher_Student_Mapping.class;
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