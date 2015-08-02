using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WCFApp.DTO
{
    public class MessageContent
    {
        public int Id { get; set; }
        public string Content { get; set; }
        public string StatusPriority { get; set; }
        public int TypeMessageId { get; set; }
        public DateTime TimeSend { get; set; }
        public DateTime TimeNeed { get; set; }

        public TypeMessage typeM { get; set; }

    }
}