using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WCFApp.DAO;
using WCFApp.DTO;

namespace WCFApp.BUS
{
    public class AppBUS
    {
        private AppDAO dao = new AppDAO();
        public Teacher checkLoginTeacher(string Username, string Password)
        {
            return dao.checkLoginTeacher(Username, Password);
        }
        public List<string> sendMessage(string TeacherId, string Message, List<string> listNumberPhone, List<string> listParents, string StatusPriority, int TypeMessageId, DateTime TimeSend, DateTime TimeNeed)
        {
            return dao.sendMessage(TeacherId, Message, listNumberPhone, listParents, StatusPriority, TypeMessageId, TimeSend, TimeNeed);

        }

        public List<Teacher_Student_Mapping> getStudent_Parent_ByTeacherId(int TeacherId)
        {
            return dao.getStudent_Parent_ByTeacherId(TeacherId);
        }

        public List<History> getHistoryByTeacherId(int TeacherId)
        {
            return dao.getHistoryByTeacherId(TeacherId);

        }

        public List<Teacher> getAllTeacher()
        {
            return dao.getAllTeacher();
        }

        public List<Class> getAllClass()
        {
            return dao.getAllClass();
        }

        public bool updateClassAndTeacher(int classId, int teacherId)
        {
            return dao.updateClassAndTeacher(classId, teacherId);
        }
    }
}