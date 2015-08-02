using ProjectServer.ServiceApp;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ProjectServer
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            ServiceApp.ServiceAppClient client = new ServiceApp.ServiceAppClient();
            List<Teacher> listTeacher = client.getAllTeacher().ToList();
            for(int i=0;i<listTeacher.Count;i++)
            {
                List<History> listHis= client.getHistoryByTeacherId(listTeacher[i].TeacherId).ToList();
                for(int j=0;j<listHis.Count;j++)
                {
                    if(listHis[j].StatusMessage==0)
                    {
                        //check time
                        if(DateTime.Compare(DateTime.Now,listHis[j].messageContent.TimeNeed)==0)
                        {
                            //send message
                            //client.sendMessage(listTeacher[i].TeacherId, listHis[j].messageContent.Content, listHis[j].parent.PhoneNumber, listHis[j].parent.PhoneNumber)
                        }
                    }
                }
            }
           
        }
    }
}
