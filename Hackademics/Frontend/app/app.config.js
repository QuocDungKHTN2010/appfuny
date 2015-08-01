(function (module) {
    module.constant('events', {
        userLoggedIn: "userLoggedIn",
        userLoggedOut: "userLoggedOut"
    });

    // application common settings
    module.value('appSettings', {
        dateFormat: "dd-MM-yyyy",
        dateTimeFormat: "dd-MM-yyyy h:mma"
    });

    // config toastr
    module.config(['toastrConfig', function (toastrConfig) {
        angular.extend(toastrConfig, {
            closeButton: true,
            closeHtml: '<button>&times;</button>',
            timeOut: 4000,
            positionClass: 'toast-bottom-right'
        });
    }]);

    // config block ui
    module.config(['blockUIConfig', function (blockUIConfig) {
        // Change the default overlay message
        blockUIConfig.message = '';
        blockUIConfig.autoBlock = true;

        blockUIConfig.requestFilter = function (config) {
            if (config.notBlockUI || config.url.indexOf('.html') > 0) {
                return false; // ... don't block it.
            }
            return true;
        };
    }]);


    // config translate-angular
    module.config(['$translateProvider', function ($translateProvider) {

        $translateProvider.useStaticFilesLoader({
            prefix: 'languages/language-',
            suffix: '.json'

        });
       
        $translateProvider.preferredLanguage('da');
       // $translateProvider.useLocalStorage();

    }]);
    // config ui-grid
    module.config(['$provide', function ($provide) {
        $provide.decorator('GridOptions', function ($delegate) {
            var gridOptions;

            gridOptions = angular.copy($delegate);
            gridOptions.initialize = function (options) {
                var defaultOptions = {
                    paginationPageSizes: [25, 50, 75, 100],
                    paginationPageSize: 25,
                    multiSelect: false,
                    modifierKeysToMultiSelect: false,
                    noUnselect: true,
                    
                    useExternalSorting: true,
                    useExternalFiltering: true,
                    enableRowSelection: true,
                    enableRowHeaderSelection: false,
                    enableSelectAll: false,
                    enablePinning: true,
                    enableHorizontalScrollbar: 2,
                };
                angular.extend(defaultOptions, options);
                return $delegate.initialize(defaultOptions);
            };

            return gridOptions;
        });
    }]);

    module.run(['$location', '$rootScope', function ($location, $rootScope) {
        $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
            $rootScope.title = "Case Worker"; // current.$$route.title;
        });
    }]);

    module.filter('true_false', function () {
        return function(text, length, end) {
            if (text) {
                return 'Ja';
            }
            return 'Nej';
        };
    });

}(angular.module("app")))
