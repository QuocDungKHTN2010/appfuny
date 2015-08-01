(function (module) {
    module.factory('addToken', ['$q', 'currentUser', addToken]);
    module.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('addToken');
    }]);
    function addToken($q, currentUser) {
        return {
            request: function (config) {
                if (currentUser && currentUser.profile && currentUser.profile.token) {
                    config.headers.Authorization = "Bearer " + currentUser.profile.token;
                }
                return $q.when(config);
            }
        };
    }
}(angular.module("common")))