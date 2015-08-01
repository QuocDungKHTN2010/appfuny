using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WCFApp.DTO
{
    public class Teacher_Student_Mapping
    {
        public int Id { get; set; }
        public int TeacherId { get; set; }
        public int StudentId { get; set; }

        public Student student { get; set; }
        public Parent parent { get; set; }
    }
}