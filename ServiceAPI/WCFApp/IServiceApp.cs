using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using WCFApp.DTO;

namespace WCFApp
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IServiceApp
    {

        [OperationContract]
        Teacher checkLoginTeacher(string Username, string Password);

        [OperationContract]
        List<string> sendMessage(string TeacherId, string Message, List<string> listNumberPhone, List<string> listParents, string StatusPriority, int TypeMessageId, DateTime TimeSend, DateTime TimeNeed);

        [OperationContract]
        List<Teacher_Student_Mapping> getStudent_Parent_ByTeacherId(int TeacherId);
        // TODO: Add your service operations here
    }


    // Use a data contract as illustrated in the sample below to add composite types to service operations.
   
}
