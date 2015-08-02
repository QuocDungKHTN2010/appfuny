using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WCFApp.DTO
{
    public class History
    {
        public int Id { get; set; }
        public int TeacherId { get; set; }
        public int MessageId { get; set; }
        public int ParentId { get; set; }
        public int StatusMessage { get; set; }

        public Parent parent { get; set; }
        public MessageContent messageContent { get; set; }
    }
}