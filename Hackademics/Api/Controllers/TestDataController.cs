using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Text.RegularExpressions;
using System.Web.Http;

namespace Api.Controllers
{
    [RoutePrefix(Routing.RoutePrefix)]
    public class TestDataController : BaseController
    {
        public TestDataController()
        {

        }
        [Route("testdata/")]
        [HttpGet]
        public IHttpActionResult testdata()
        {
            var data = new
            {
                message = "succes"
            };

            return Ok(data);
        }
    }
}