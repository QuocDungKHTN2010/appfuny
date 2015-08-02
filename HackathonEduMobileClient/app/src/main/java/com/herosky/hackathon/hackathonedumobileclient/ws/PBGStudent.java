package com.herosky.hackathon.hackathonedumobileclient.ws;

//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 4.1.7.1
//
// Created by Quasar Development at 01-08-2015
//
//---------------------------------------------------


import java.util.Hashtable;
import org.ksoap2.serialization.*;

public class PBGStudent extends AttributeContainer implements KvmSerializable
{
    
    public String FullName;
    
    public Integer StudentId;

    public PBGStudent ()
    {
    }

    public PBGStudent (java.lang.Object paramObj,PBGExtendedSoapSerializationEnvelope __envelope)
    {
	    
	    if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;


        SoapObject soapObject=(SoapObject)inObj;  
        if (soapObject.hasProperty("FullName"))
        {	
	        java.lang.Object obj = soapObject.getProperty("FullName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.FullName = j.toString();
                }
	        }
	        else if (obj!= null && obj instanceof String){
                this.FullName = (String)obj;
            }    
        }
        if (soapObject.hasProperty("StudentId"))
        {	
	        java.lang.Object obj = soapObject.getProperty("StudentId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.StudentId = Integer.parseInt(j.toString());
                }
	        }
	        else if (obj!= null && obj instanceof Integer){
                this.StudentId = (Integer)obj;
            }    
        }


    }

    @Override
    public java.lang.Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==0)
        {
            return this.FullName!=null?this.FullName:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.StudentId!=null?this.StudentId:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "FullName";
            info.namespace= "http://schemas.datacontract.org/2004/07/WCFApp.DTO";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "StudentId";
            info.namespace= "http://schemas.datacontract.org/2004/07/WCFApp.DTO";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

    @Override
    public String getInnerText() {
        return null;
    }

    @Override
    public void setInnerText(String s) {

    }
}
