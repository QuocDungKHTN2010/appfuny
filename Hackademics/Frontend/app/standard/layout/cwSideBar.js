(function (module) {
    module.directive("cwSideBar", sideBarDirective);

    function sideBarDirective() {
        var directive = {
            link: link,
            restrict: "E",
            controller: ["$scope", "$http", "$location", "$route", "appConfig", SideBarController],
            templateUrl: "app/standard/layout/cwSideBar.html"
        };
        return directive;

        function link(scope, element, attrs, controllers) {
            var $dropdownElement = element.find('.sidebar-dropdown a'),
                $sidebarInner = element.find('.sidebar-inner');
            activate();

            function activate() {
                element.addClass('sidebar');
                $dropdownElement.click(dropdown);
            }

            function dropdown(e) {
                var dropClass = 'dropy';
                e.preventDefault();
                if (!$dropdownElement.hasClass(dropClass)) {
                    hideAllSidebars();
                    $sidebarInner.slideDown(350);
                    $dropdownElement.addClass(dropClass);
                } else if ($dropdownElement.hasClass(dropClass)) {
                    $dropdownElement.removeClass(dropClass);
                    $sidebarInner.slideUp(350);
                }

                function hideAllSidebars() {
                    $sidebarInner.slideUp(350);
                    $('.sidebar-dropdown a').removeClass(dropClass);
                }
            }
        }
    }

    function SideBarController($scope, $http, $location, $route, appConfig) {
        $scope.currentItem = null;
        $scope.isCurrent = isCurrent;
        $scope.nav = nav;
        
        activate();

        function activate() {
            var url = appConfig.url("list-configs");
            $http.get(url)
                .success(function(data) {
                    $scope.items = data;
                    if (data.length > 0 && $route.current.originalPath == '/') {
                        var caseList = $scope.items[0];
                        $location.path("case-list/" + caseList.name);
                    }
                });
        }

        function isCurrent(item) {
            if ($scope.currentItem != null)
                return $scope.currentItem === item;
            return item.name === decodeURIComponent($route.current.params.name);
        };
        function nav(item) {
            ///<summary>Navigate to the selected navigation item when user clicks on the side bar item</summary>
            ///<param name="item">The id of configuration item.</param>
            $scope.currentItem = item;
            switch (item.objectType) {
                case "Case":
                    $location.path("case-list/" + item.name);
                    break;
                case "Workflow":
                    $location.path("workflow-list/" + item.name);
                    break;
                default:
                    break;
            }
            
        }
    }
})(angular.module("app.layout"));