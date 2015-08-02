using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WCFApp.DTO
{
    public class Teacher
    {
        public int TeacherId { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public string PhoneNumber { get; set; }
        public string FullName { get; set; }
        public string Status { get; set; }
        public string CMND { get; set; }
        public string Email { get; set; }
        public string ImagePath { get; set; }
        public string Address { get; set; }
        public int TypeId { get; set; }

    }
}