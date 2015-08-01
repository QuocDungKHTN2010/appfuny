(function (module) {
    module.controller("LoginController", ["$rootScope", "$translate", "$scope", "$routeParams", "$location", "accountService", "logger", "currentUser", "events", "blockUI", LoginController]);

    function LoginController($rootScope, $translate, $scope, $routeParams, $location, accountService, logger, currentUser, events, blockUI) {
        var model = this,
            returnPath = $routeParams.returnPath;

        model.needValidate = false;
        model.account = {};
        model.isLoggedIn = currentUser.isLoggedIn();

        model.login = login;
        model.back = back;

        model.textResources = {};


        function login(data) {
            var form = $scope.loginForm;
            model.needValidate = !form.$valid;

            // Get text resources 
            $translate(['Login_Please_complete_all_required_fields', 'Logged_in_successfully', 'Login_Cannot_verify_your_login_info'])
                  .then(function (tranlations) {
                      model.textResources = tranlations;
                  });
          
            if (model.needValidate) {
                logger.warning(model.textResources.Login_Please_complete_all_required_fields);
                return;
            }
            accountService.login(model.account)
                .then(
                    function (result) {
                        currentUser.setProfile({
                            username: model.account.username,
                            token: result.data.access_token
                        });   
                        logger.success(model.textResources.Logged_in_successfully);
                        $scope.$emit(events.userLoggedIn);
                        $location.path(returnPath ? returnPath : "/");
                    },
                    function () {
                        logger.error(model.textResources.Login_Cannot_verify_your_login_info);
                    });
        }

        function back() {
            $location.path(returnPath ? returnPath : "/");
        }
    }
}(angular.module("app.account")))