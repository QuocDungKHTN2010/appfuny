(function () {
    angular.module("app", [
        // Angular modules 
        'ngAnimate',        // animations
        'ngRoute',          // routing
        'ngSanitize',       // sanitizes html bindings (ex: sidebar.js)
        'ngTouch',
        // 3rd party modules
        'ui.bootstrap',      // ui-bootstrap (ex: carousel, pagination, dialog),
        'toastr',
        'ui.grid',
        'ui.grid.pagination',
        'ui.grid.selection',
        'ui.grid.autoResize',
        'ui.mask',
        'ui.select',
        'ui.validate',
        'blockUI',
        'pascalprecht.translate',   // translation-Angular (Reference : http://angular-translate.github.io/ )
        'ngCookies',
        'angular-inview',
        // application modules
        'common',
        'app.case',
        'app.workflow',
        'app.layout',
        'app.account'
    ]);
}())
