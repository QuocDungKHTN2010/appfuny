(function(module) {
    var localStorage = function ($window) {
        // Mostly available in HTML5;
        var store = $window.localStorage;
        var get = function(key) {
            var value = store.getItem(key);
            if (value) {
                value = angular.fromJson(value);
            }
            return value;
        };

        var add = function(key, value) {
            value = angular.toJson(value);
            store.setItem(key, value);
        };

        var remove = function(key) {
            store.removeItem(key);
        };

        return {
            get: get,
            add: add,
            remove: remove
        };
    };

    module.factory("localStorage", localStorage);
}(angular.module("common")))