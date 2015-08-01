(function () {
    function modalService($modal) {
        var self = this;

        self.open = function (option) {
            modalInstance = $modal.open(angular.extend(option, {
                windowClass: option.windowClass || "",
                backdrop: option.backdrop || 'static',
            }));

            return modalInstance;
        };

        self.confirm = function (option,title, text) {
            var modalHtml = '<div class="modal-header"><h3 class="modal-title">' + title + '</h3></div><div class="modal-body">' + text + '</div>';
            modalHtml += '<div class="modal-footer"><button class="btn btn-primary" ng-click="$close()">Yes</button><button class="btn btn-warning" ng-click="$dismiss()">No</button></div>';
            modalInstance = $modal.open(angular.extend(option, {
                windowClass: option.windowClass || "",
                backdrop: option.backdrop || 'static',
                template: modalHtml
            }));
            return modalInstance;
        }
    };

    angular.module('common').service('modalService', ['$modal', modalService]);
}())