(function(module) {
    var formEncode = function() {
        return function(data) {
            var pairs = [];
            for (var name in data) {
                var value = encodeValue(data[name]);
                pairs.push(encodeURIComponent(name) + "=" + encodeURIComponent(value));
            }
            return pairs.join("&").replace(/%20/g, '+');
        };

        function encodeValue(value) {
            if (value !== null && value !== undefined) {
                if (typeof value == "object") {
                    return  JSON.stringify(value);
                } else if (typeof value == "function") {
                    var rawValue = value();
                    return encodeValue(rawValue);
                }
                return value;
            }
            else {
                return  "";
            }
        }
    };

    module.factory("formEncode", formEncode);
}(angular.module("common")))