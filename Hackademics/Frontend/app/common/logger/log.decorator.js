(function(module) {
    module.config(['$provide', function($provide) {
        $provide.decorator('$log', ["$delegate", function ($delegate) {
            return $delegate;
        }]);
    }]);
}(angular.module('common')))