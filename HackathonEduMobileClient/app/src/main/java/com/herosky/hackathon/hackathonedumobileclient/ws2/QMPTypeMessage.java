package com.herosky.hackathon.hackathonedumobileclient.ws2;

//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 4.1.7.1
//
// Created by Quasar Development at 02-08-2015
//
//---------------------------------------------------


import java.util.Hashtable;
import org.ksoap2.serialization.*;

public class QMPTypeMessage extends AttributeContainer implements KvmSerializable
{
    
    public String DescriptionTypeMessage;
    
    public Integer Id;

    public QMPTypeMessage ()
    {
    }

    public QMPTypeMessage (java.lang.Object paramObj,QMPExtendedSoapSerializationEnvelope __envelope)
    {
	    
	    if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;


        SoapObject soapObject=(SoapObject)inObj;  
        if (soapObject.hasProperty("DescriptionTypeMessage"))
        {	
	        java.lang.Object obj = soapObject.getProperty("DescriptionTypeMessage");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.DescriptionTypeMessage = j.toString();
                }
	        }
	        else if (obj!= null && obj instanceof String){
                this.DescriptionTypeMessage = (String)obj;
            }    
        }
        if (soapObject.hasProperty("Id"))
        {	
	        java.lang.Object obj = soapObject.getProperty("Id");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.Id = Integer.parseInt(j.toString());
                }
	        }
	        else if (obj!= null && obj instanceof Integer){
                this.Id = (Integer)obj;
            }    
        }


    }

    @Override
    public java.lang.Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==0)
        {
            return this.DescriptionTypeMessage!=null?this.DescriptionTypeMessage:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.Id!=null?this.Id:SoapPrimitive.NullSkip;
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
            info.name = "DescriptionTypeMessage";
            info.namespace= "http://schemas.datacontract.org/2004/07/WCFApp.DTO";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "Id";
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