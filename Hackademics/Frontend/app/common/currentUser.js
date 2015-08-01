(function (module) {
    module.factory("currentUser", ['localStorage', currentUser]);

    function currentUser (localStorage) {
        var USERKEY = "utoken",
            profile = getProfile();

        return {
            profile: profile,
            setProfile: setProfile,
            logOut: logOut,
            isLoggedIn: isLoggedIn
        };

        function setProfile(data) {
            profile.username = data.username;
            profile.token = data.token;
            localStorage.add(USERKEY, profile);
        }

        function logOut() {
            profile.username = "";
            profile.token = "";
            localStorage.remove(USERKEY);
            //remove for adfs token
            localStorage.remove('authorizationData');
        }

        function getProfile() {
            var user = {
                username: "",
                token: ""
            };
            var localUser = localStorage.get(USERKEY);
            if (localUser) {
                user.username = localUser.username;
                user.token = localUser.token;
            }
            return user;
        }

        function isLoggedIn() {
            return !!(profile && profile.username && profile.token);
        }
    };
}(angular.module("common")))