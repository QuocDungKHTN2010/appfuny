using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using WCFApp.DTO;
using System;
using Twilio;
using System.Data.SqlClient;

namespace WCFApp.DAO
{
    public class AppDAO
    {
        //send Message 
        public List<string> sendMessage(string TeacherId,string Message,List<string> listNumberPhone,List<string> listParents,string StatusPriority,int TypeMessageId,DateTime TimeSend,DateTime TimeNeed)
        {
            //send cho nguoi dung
            List<string> listRes = new List<string>();
            
            string AccountSid = "AC14ff1979a124977a9962b9060f976a7c";
            string AuthToken = "b40e3816f5d67a1966549073eca8d473";
            var twilio = new TwilioRestClient(AccountSid, AuthToken);
            listRes.Clear();
            for (int i=0;i<listNumberPhone.Count;i++)
            {
                string numberTemp = listNumberPhone[i];
                var message = twilio.SendMessage("+14155992671", numberTemp, Message);
                //neu khac null la gui thanh cong
                if (message.Sid != null)
                {
                    listRes.Add("success");
                }
                else
                {
                    listRes.Add(message.RestException.ToString());
                }
            }
            //save content Message
            int r = insertContentMessage(Message, StatusPriority, TypeMessageId, TimeSend, TimeNeed);
            //save Message For Parent
            if(r!=-1)
            {
                insertHistory(TeacherId, r, listParents,listRes);
            }
            return listRes;
        }

        //save message for parents
        public List<bool> insertHistory(string TeacherId,int MessageId,List<string> listParents,List<string> listSendMess)
        {
            List<bool> listRe = new List<bool>();
            for (int i = 0; i < listParents.Count; i++)
            {
                string namePro1 = "insertHistory";
                SqlParameter[] para = new SqlParameter[4];
                para[0] = new SqlParameter("@TeacherId", TeacherId);
                para[1] = new SqlParameter("@MessageId", MessageId);
                para[2] = new SqlParameter("@ParentId", listParents[i]);
                if(!listSendMess[i].Equals("success"))
                    para[3] = new SqlParameter("@StatusMessage", 2);
                else
                    para[3] = new SqlParameter("@StatusMessage", 1);

                try
                {
                    if (DataProviderApp.executeStoreProcedureNonQuery(namePro1, para) != 1)
                        listRe.Add(false);
                    else
                    {
                        listRe.Add(true);
                    }
                }
                catch (Exception ex)
                {
                    listRe.Add(false);
                }
            }
            return listRe;
        }


        //save history
        public int insertContentMessage(string Content, string StatusPriority,int TypeMessageId,DateTime TimeSend,DateTime TimeNeed)
        {
            DataTable dt;
            int n;

            string namePro1 = "sp_InsertContentMessage";
            SqlParameter[] para = new SqlParameter[5];
            para[0] = new SqlParameter("@Content", Content);
            para[1] = new SqlParameter("@StatusPriority", StatusPriority);
            para[2] = new SqlParameter("@TypeMessageId", TypeMessageId);
            para[3] = new SqlParameter("@TimeSend", TimeSend);
            para[4] = new SqlParameter("@TimeNeed", TimeNeed);

            try
            {
                dt = DataProviderApp.executeStoreProcedureQuery(namePro1, para);
                n = dt.Rows.Count;
                if (n > 0)
                {
                    return Convert.ToInt32(dt.Rows[0]["ReturnValue"].ToString());
                }
                return -1;
            }
            catch (Exception ex)
            {
                return -1;
            }
        }


        //checkLogin
        public Teacher checkLoginTeacher(string Username, string Password)
        {
            Teacher Cus = new Teacher();
            string strSql = "SELECT * FROM Teacher Where Username='" + Username + "' and Password='"+Password+"'";
            DataTable dt = DataProviderApp.executeQuery(strSql);
            int n = dt.Rows.Count;
            if (n > 0)
            {
                Cus.TeacherId = Convert.ToInt32(dt.Rows[0]["TeacherId"].ToString());
                Cus.Email = dt.Rows[0]["Email"].ToString();
                Cus.Username = dt.Rows[0]["Username"].ToString();
                Cus.Password = dt.Rows[0]["Password"].ToString();
                Cus.PhoneNumber = dt.Rows[0]["PhoneNumber"].ToString();
                Cus.FullName = dt.Rows[0]["FullName"].ToString();
                Cus.Status = dt.Rows[0]["Status"].ToString();
                Cus.CMND = dt.Rows[0]["CMND"].ToString();
                Cus.ImagePath = dt.Rows[0]["ImagePath"].ToString();
                Cus.Address = dt.Rows[0]["Address"].ToString();
                Cus.TypeId = Convert.ToInt32(dt.Rows[0]["TypeId"].ToString());
            }
            else
            {
                Cus = null;
            }
            return Cus;
        }


        //get Parent Of Teacher
        public List<Teacher_Student_Mapping> getStudent_Parent_ByTeacherId(int TeacherId)
        {

            List<Teacher_Student_Mapping> listRes = new List<Teacher_Student_Mapping>();
            DataTable dt;
            int n;

            string nameProc = "getStudent_Parent_ByTeacherId";
            SqlParameter[] para = new SqlParameter[1];
            para[0] = new SqlParameter("@TeacherId", TeacherId);
          

            dt = DataProviderApp.executeStoreProcedureQuery(nameProc, para);
            n = dt.Rows.Count;
            if (n > 0)
            {
                for (int i = 0; i < n; i++)
                {
                    Teacher_Student_Mapping temp = new Teacher_Student_Mapping();
                    temp.StudentId= Convert.ToInt32(dt.Rows[i]["StudentId"].ToString());
                    temp.TeacherId = TeacherId;

                    temp.student = new Student();
                    temp.student.StudentId= Convert.ToInt32(dt.Rows[i]["StudentId"].ToString());
                    temp.student.FullName = dt.Rows[i]["StudentFullName"].ToString();

                    temp.parent = new Parent();
                    temp.parent.StudentId= Convert.ToInt32(dt.Rows[i]["StudentId"].ToString()); 
                    temp.parent.Address= dt.Rows[i]["Address"].ToString();
                    temp.parent.CMND = dt.Rows[i]["CMND"].ToString();
                    temp.parent.Email = dt.Rows[i]["Email"].ToString();
                    temp.parent.FullName = dt.Rows[i]["FullName"].ToString();
                    temp.parent.ParentId = Convert.ToInt32(dt.Rows[i]["ParentId"].ToString());
                    temp.parent.PhoneNumber = dt.Rows[i]["PhoneNumber"].ToString();
                    temp.parent.Status = dt.Rows[i]["Status"].ToString();


                    listRes.Add(temp);

                }

            }
            return listRes;
        }

        public List<History> getHistoryByTeacherId(int TeacherId)
        {

            List<History> listRes = new List<History>();
            DataTable dt;
            int n;

            string nameProc = "getHistoryByTeacherId";
            SqlParameter[] para = new SqlParameter[1];
            para[0] = new SqlParameter("@TeacherId", TeacherId);


            dt = DataProviderApp.executeStoreProcedureQuery(nameProc, para);
            n = dt.Rows.Count;
            if (n > 0)
            {
                for (int i = 0; i < n; i++)
                {
                    History temp = new History();
                    temp.StatusMessage = Convert.ToInt32(dt.Rows[i]["StatusMessage"].ToString());
                    temp.TeacherId = TeacherId;
                    temp.MessageId= Convert.ToInt32(dt.Rows[i]["MessageId"].ToString());
                    temp.ParentId= Convert.ToInt32(dt.Rows[i]["ParentId"].ToString());

                    temp.messageContent = new MessageContent();
                    temp.messageContent.Content = dt.Rows[i]["Content"].ToString();
                    temp.messageContent.Id= Convert.ToInt32(dt.Rows[i]["Id"].ToString());
                    temp.messageContent.StatusPriority = dt.Rows[i]["StatusPriority"].ToString();
                    temp.messageContent.TypeMessageId = Convert.ToInt32(dt.Rows[i]["TypeMessageId"].ToString());
                    temp.messageContent.TimeNeed = Convert.ToDateTime(dt.Rows[i]["TimeNeed"].ToString());
                    temp.messageContent.TimeSend = Convert.ToDateTime(dt.Rows[i]["TimeSend"].ToString());
                    temp.messageContent.typeM = new TypeMessage();
                    temp.messageContent.typeM.DescriptionTypeMessage = dt.Rows[i]["DescriptionTypeMessage"].ToString();

                    temp.parent = new Parent();
                    temp.parent.StudentId = Convert.ToInt32(dt.Rows[i]["StudentId"].ToString());
                    temp.parent.Address = dt.Rows[i]["Address"].ToString();
                    temp.parent.CMND = dt.Rows[i]["CMND"].ToString();
                    temp.parent.Email = dt.Rows[i]["Email"].ToString();
                    temp.parent.FullName = dt.Rows[i]["FullName"].ToString();
                    temp.parent.ParentId = Convert.ToInt32(dt.Rows[i]["ParentId"].ToString());
                    temp.parent.PhoneNumber = dt.Rows[i]["PhoneNumber"].ToString();
                    temp.parent.Status = dt.Rows[i]["Status"].ToString();

                    listRes.Add(temp);

                }

            }
            return listRes;
        }

        public List<Teacher> getAllTeacher()
        {
            List<Teacher> listTeacher = new List<Teacher>();
            string strSql = "SELECT * FROM Teacher";
            DataTable dt = DataProviderApp.executeQuery(strSql);
            int n = dt.Rows.Count;
            if (n > 0)
            {
                for (int i = 0; i < n; i++)
                {
                    Teacher Cus = new Teacher();
                    Cus.TeacherId = Convert.ToInt32(dt.Rows[i]["TeacherId"].ToString());
                    Cus.Email = dt.Rows[i]["Email"].ToString();
                    Cus.Username = dt.Rows[i]["Username"].ToString();
                    Cus.Password = dt.Rows[i]["Password"].ToString();
                    Cus.PhoneNumber = dt.Rows[i]["PhoneNumber"].ToString();
                    Cus.FullName = dt.Rows[i]["FullName"].ToString();
                    Cus.Status = dt.Rows[i]["Status"].ToString();
                    Cus.CMND = dt.Rows[i]["CMND"].ToString();
                    Cus.ImagePath = dt.Rows[i]["ImagePath"].ToString();
                    Cus.Address = dt.Rows[i]["Address"].ToString();
                    Cus.TypeId = Convert.ToInt32(dt.Rows[i]["TypeId"].ToString());
                    listTeacher.Add(Cus);
                }
            }
          
            return listTeacher;
        }

        public List<Class> getAllClass()
        {
            List<Class> listTeacher = new List<Class>();
            string strSql = "SELECT * FROM Class";
            DataTable dt = DataProviderApp.executeQuery(strSql);
            int n = dt.Rows.Count;
            if (n > 0)
            {
                for (int i = 0; i < n; i++)
                {
                    Class Cus = new Class();
                    Cus.Id = Convert.ToInt32(dt.Rows[i]["Id"].ToString());
                    Cus.Name = dt.Rows[i]["Name"].ToString();
                    Cus.StatusClass = Convert.ToInt32(dt.Rows[i]["StatusClass"].ToString());
                   
                    listTeacher.Add(Cus);
                }
            }

            return listTeacher;

        }

        public bool insertClassTeacherMapping(int teacherId,int studentId)
        {
            DataTable dt;
            int n;

            string namePro1 = "insertClassTeacherMapping";
            SqlParameter[] para = new SqlParameter[2];
            para[0] = new SqlParameter("@TeacherId", teacherId);
            para[1] = new SqlParameter("@StudentId", studentId);

            try
            {
                if (DataProviderApp.executeStoreProcedureNonQuery(namePro1, para) != 1)
                    return false;
                else
                    return true;
            }
            catch (Exception ex)
            {
                return false;
            }
        }
        public bool updateClassAndTeacher(int classId,int teacherId)
        {
            Teacher Cus = new Teacher();
            string strSql = "SELECT * FROM Class_Student_Mapping Where ClassId=" + classId;
            DataTable dt = DataProviderApp.executeQuery(strSql);
            int n = dt.Rows.Count;
            List<bool> listB = new List<bool>();
            if (n > 0)
            {
                for (int i = 0; i < n; i++)
                {
                    int tempId = Convert.ToInt32(dt.Rows[i]["StudentId"].ToString());
                    if (insertClassTeacherMapping(teacherId, tempId))
                        listB.Add(true);
                    else
                        listB.Add(false);
                }
                if (listB.Count == n)
                    return true;
                else
                    return false;
            }
            else
            {
                return false;
            }
        }
    }
}