(function (module) {
    module.factory("oauth", ['$http', 'formEncode', oauth]);

    function oauth ($http, formEncode) {
        return {
            login: login
        };

        function login(username, password) {
            // Since posting with oauth, it expects urlencoded data not JSON format as other api
            var config = {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                }
            };

            var data = formEncode({
                username: username,
                password: password,
                grant_type: "password"
            });

            return $http.post("/login", data, config);
        };
    };
}(angular.module("common")))