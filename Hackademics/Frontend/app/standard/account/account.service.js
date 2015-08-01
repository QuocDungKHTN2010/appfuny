(function (module) {
    module.factory("accountService", ["$http", "$q", "$timeout", "formEncode", "appConfig", "currentUser", accountService]);

    function accountService($http, $q, $timeout, formEncode, appConfig, currentUser) {
        return {
            login: login,
            logout: logout
        };

        function login(option) {
            var config = {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                }
            };

            var data = formEncode({
                username: option.username,
                password: option.password,
                grant_type: "password"
            });

            return $http.post(appConfig.tokenEndpoint(), data, config);
        }

        function logout() {
            currentUser.logout();
        };
    }
}(angular.module("app.account")))