(function (module) {
    module.factory('loginRedirect', ['$q', '$location', 'currentUser', 'localStorage', 'appConfig', loginRedirect]);

    module.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('loginRedirect');
    }]);

    function loginRedirect($q, $location, currentUser, localStorage, appConfig) {
        var lastPath = "/";
        return {
            request: _request,
            responseError: responseError,
            redirectPostLogin: redirectPostLogin
        };

        function _request(config) {
            // check if local storage data has existed
                config.headers = config.headers || {};
                var authData = localStorage.get('authorizationData');
                if (authData) {
                    //config.headers.Authorization = 'Bearer ' + authData.token;
                    config.headers.Authorization = 'Bearer ' + authData.token;
                }
                return config;
        }
        function responseError(response) {

            if (response.status == 401 || response.data == null) {
                if (!appConfig.useADFS) {
                    currentUser.logOut();
                    lastPath = $location.path();
                    document.location.href = 'login/#' + encodeURIComponent(lastPath);
                } else {
                    currentUser.logOut();
                    //try to reset current User
                    // if user doesn't have the permission to connect web api
                    //var externalProviderUrl = "https://r2.2012r2.org/adfs/oauth2/authorize?response_type=code&resource=https://api.casewhere.local:44309/&client_id=hello&redirect_uri=https://api.casewhere.local:44309/api/v0.1/tokenhandler";
                    window.authCompletedCB = _authCompletedCB;
                    var oauthWindow = window.open(appConfig.externalProviderUrl, "Authenticate Account", "location=0,status=0,width=600,height=750");
                    //currentUser.logOut();                
                    return;
                    //return $q.rejet(response);
                }
            }
            return $q.reject(response);
        };

        function _authCompletedCB(fragment) {
            localStorage.add('authorizationData', { token: fragment.access_token });
            currentUser.setProfile({ username: fragment.userName });
            document.location.href = '/';
            //$location.path("/");
        }

        function redirectPostLogin() {
            $location.path(lastPath ? lastPath : "/");
            lastPath = "/";
        };

    }

}(angular.module("common")))

