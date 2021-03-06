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

public class PBGTeacher_Student_Mapping extends AttributeContainer implements KvmSerializable
{
    public boolean isCheck = false;
    public Integer Id;
    
    public Integer StudentId;
    
    public Integer TeacherId;
    
    public PBGParent parent;
    
    public PBGStudent student;

    public PBGTeacher_Student_Mapping ()
    {
    }

    public PBGTeacher_Student_Mapping (java.lang.Object paramObj,PBGExtendedSoapSerializationEnvelope __envelope)
    {
	    
	    if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;


        SoapObject soapObject=(SoapObject)inObj;  
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
        if (soapObject.hasProperty("TeacherId"))
        {	
	        java.lang.Object obj = soapObject.getProperty("TeacherId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.TeacherId = Integer.parseInt(j.toString());
                }
	        }
	        else if (obj!= null && obj instanceof Integer){
                this.TeacherId = (Integer)obj;
            }    
        }
        if (soapObject.hasProperty("parent"))
        {	
	        java.lang.Object j = soapObject.getProperty("parent");
	        this.parent = (PBGParent)__envelope.get(j,PBGParent.class);
        }
        if (soapObject.hasProperty("student"))
        {	
	        java.lang.Object j = soapObject.getProperty("student");
	        this.student = (PBGStudent)__envelope.get(j,PBGStudent.class);
        }


    }

    @Override
    public java.lang.Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==0)
        {
            return this.Id!=null?this.Id:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.StudentId!=null?this.StudentId:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.TeacherId!=null?this.TeacherId:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.parent!=null?this.parent:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.student!=null?this.student:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 5;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "Id";
            info.namespace= "http://schemas.datacontract.org/2004/07/WCFApp.DTO";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "StudentId";
            info.namespace= "http://schemas.datacontract.org/2004/07/WCFApp.DTO";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "TeacherId";
            info.namespace= "http://schemas.datacontract.org/2004/07/WCFApp.DTO";
        }
        if(propertyIndex==3)
        {
            info.type = PBGParent.class;
            info.name = "parent";
            info.namespace= "http://schemas.datacontract.org/2004/07/WCFApp.DTO";
        }
        if(propertyIndex==4)
        {
            info.type = PBGStudent.class;
            info.name = "student";
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
