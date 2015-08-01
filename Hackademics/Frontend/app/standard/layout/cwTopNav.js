(function (module) {
    module.directive("cwTopNav", topNavDirective);
    module.controller("TopNavController", ["$translate","$scope","i18nService", "$location", "events", "currentUser", TopNavController]);

    function topNavDirective() {
        return {
            restrict: 'AE',
            controller: "TopNavController",
            templateUrl: "app/standard/layout/cwTopNav.html"
        };
    }

    function TopNavController($translate, $scope, i18nService, $location, events, currentUser) {
        $scope.user = currentUser.profile;
       // $scope.model.changeLanguage = changeLanguage;
        $translate.use('da');
        $scope.home = home;

        //function changeLanguage(langKey) {
           
        //    $translate.use(langKey)
        //        .then(function () {
        //        location.reload(true);
        //    });

        //}
        $scope.logOut = function() {
            currentUser.logOut();
            document.location.href ="login";
        };

        function home() {
            $location.path("/");
        }

     
    }
})(angular.module("app.layout"));