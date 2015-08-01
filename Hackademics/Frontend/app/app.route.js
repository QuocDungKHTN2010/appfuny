(function (module) {
    module.config(['$routeProvider', routeProviderConfig]);

    function routeProviderConfig($routeProvider) {
	    $routeProvider
            //.when("/login/:returnPath?", {
            //    templateUrl: "app/standard/account/login.html",
            //    controller: "LoginController",
            //    controllerAs: "model"
            //})
            .when("/case/new/:workdlowDefinitionId", {
                templateUrl: "app/standard/case/new-case.html",
                controller: "StartCaseWorkflowController",
                controllerAs: "model"
            })
            .when("/", {
                templateUrl: "app/standard/layout/home.html"
            })
            .when("/case-list/:name", {
                templateUrl: "app/standard/case/case-list.html",
                controller: "CaseListController",
                controllerAs: "model"
            })
            .when("/workflow-list/:name", {
                templateUrl: "app/standard/workflow/workflow-list.html",
                controller: "WorkflowListController",
                controllerAs: "model"
            })
            .when("/case/:name/:id/:caseTypeId", {
                templateUrl: "app/standard/case/case-page.html",
                controller: "CasePageController",
                controllerAs: "model"
            })
            //.when("/claims", {
            //    templateUrl: "token.html",
            //    controller:"ClaimsController"
	        //})
            .otherwise({
            	redirectTo: "/"
            });
	}
}(angular.module("app")))