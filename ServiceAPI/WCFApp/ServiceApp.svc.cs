﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using WCFApp.BUS;
using WCFApp.DTO;

namespace WCFApp
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select Service1.svc or Service1.svc.cs at the Solution Explorer and start debugging.
    public class ServiceApp : IServiceApp
    {
        private AppBUS cusproductBus = new AppBUS();
        public Teacher checkLoginTeacher(string Username, string Password)
        {
            return cusproductBus.checkLoginTeacher(Username, Password);
        }

        public List<string> sendMessage(string TeacherId, string Message, List<string> listNumberPhone, List<string> listParents, string StatusPriority, int TypeMessageId, DateTime TimeSend, DateTime TimeNeed)
        {
            return cusproductBus.sendMessage(TeacherId, Message, listNumberPhone, listParents, StatusPriority, TypeMessageId, TimeSend, TimeNeed);
        }
        public List<Teacher_Student_Mapping> getStudent_Parent_ByTeacherId(int TeacherId)
        {
            return cusproductBus.getStudent_Parent_ByTeacherId(TeacherId);
        }
        public List<History> getHistoryByTeacherId(int TeacherId)
        {
            return cusproductBus.getHistoryByTeacherId(TeacherId);

        }

        public List<Teacher> getAllTeacher()
        {
            return cusproductBus.getAllTeacher();
        }

        public List<Class> getAllClass()
        {
            return cusproductBus.getAllClass();
        }

        public bool updateClassAndTeacher(int classId, int teacherId)
        {
            return cusproductBus.updateClassAndTeacher(classId, teacherId);
        }
        public void test1()
        { }
        public void test2()
        { }
        public void test3()
        { }
        public void test4()
        { }
        public void test5()
        { }
        public void test6()
        { }
        public void test7()
        { }

    }
}
