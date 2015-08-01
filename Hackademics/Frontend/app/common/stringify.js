(function(module) {
    module.factory("stringify", stringify);

    function stringify() {
        return {
            trimLeft: trimLeft
        };
        function trimLeft(input, c) {
            if (input) {
                while (input.charAt(0) === c) {
                    input = input.slice(1);
                }
                return input;
            }

            return "";
        }
    }
}(angular.module("common")))