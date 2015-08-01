(function(module) {
    module.factory("appConfig", ['stringify', appConfig]);

    function appConfig(stringify) {
        var config = {
            endpoint: "http://api.casewhere.local/api/v0.1/",
            tokenEndpoint: "http://api.casewhere.local/token"
        };
        //var config = {
        //    endpoint: "https://api.casewhere.local:44309/api/v0.1/",
        //    tokenEndpoint: "https://api.casewhere.local:44309/token"
        //};
        return {
            url: url,
            tokenEndpoint: tokenEndpoint,
            useADFS: false,
            externalProviderUrl: 'https://r2.2012r2.org/adfs/oauth2/authorize?response_type=code&resource=https://api.casewhere.local:44309/&client_id=hello&redirect_uri=https://api.casewhere.local:44309/api/v0.1/tokenhandler'
        };

        function url(path) {
            ///<summary>Build the full url for a specific path/endpoint</summary>
            ///<param name="path">The relative path to the endpoint. If the path starts with /, will be trim out</param>
            
            path = stringify.trimLeft(path, "/");
            return config.endpoint + path;
        }


        function tokenEndpoint() {
            return config.tokenEndpoint;
        }
    }
}(angular.module("common")))