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
                insertHistory(TeacherId, r, listParents);
            }
            return listRes;
        }

        //save message for parents
        public List<bool> insertHistory(string TeacherId,int MessageId,List<string> listParents)
        {
            List<bool> listRe = new List<bool>();
            for (int i = 0; i < listParents.Count; i++)
            {
                string namePro1 = "insertHistory";
                SqlParameter[] para = new SqlParameter[3];
                para[0] = new SqlParameter("@TeacherId", TeacherId);
                para[1] = new SqlParameter("@MessageId", MessageId);
                para[2] = new SqlParameter("@ParentId", listParents[i]);

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

            string namePro1 = "sp_InsertContentMessage";
            SqlParameter[] para = new SqlParameter[5];
            para[0] = new SqlParameter("@Content", Content);
            para[1] = new SqlParameter("@StatusPriority", StatusPriority);
            para[2] = new SqlParameter("@TypeMessageId", TypeMessageId);
            para[3] = new SqlParameter("@TimeSend", TimeSend);
            para[4] = new SqlParameter("@TimeNeed", TimeNeed);

            try
            {
                int res = DataProviderApp.executeStoreProcedureNonQuery1(namePro1, para);

                return res;
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
    }
}