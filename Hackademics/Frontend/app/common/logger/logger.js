(function(module) {
    module.factory("logger", ['$log', 'toastr', logger]);

    function logger($log, toastr) {
        var title = "Case worker";

        return {
            error: error,
            info: info,
            warning: warning,
            success: success
        };

        function error(message) {
            $log.error(message);
            toastr.error(message, title);
        }

        function info(message) {
            $log.info(message);
            toastr.info(message, title);
        }

        function warning(message) {
            $log.warn(message);
            toastr.warning(message, title);
        }

        function success(message) {
            toastr.success(message);
        }
    }
}(angular.module("common")))