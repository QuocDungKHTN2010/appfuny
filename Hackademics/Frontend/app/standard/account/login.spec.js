
describe('app.case: login controller', function () {
    beforeEach(module('app.account'));
    beforeEach(module('common'));

    var ctrl, scope,
        mockFormEncode;
    beforeEach(function () {

        mockFormEncode = {};

        module(function ($provide) {
            $provide.value('formEncode', mockFormEncode);
            $provide.constant('appConfig', {});
            $provide.constant('events', {});
            $provide.value('toastr', {});
            //$provide.service('accountService', function () {
            //    this.login = jasmine.createSpy('login').andCallFake(function (num) {
            //        //a fake implementation
            //    });
            //});
        });

    });

    beforeEach(inject(function ($controller) {
        scope = {};
        
        ctrl = $controller('LoginController', {
            $scope: scope,
            $routeParams: { returnPath: '/' }
        });

    }));

    it('should have scope defined', function () {
        expect(scope).toBeDefined();
    });

    it('should have needValidate property with false as default', function () {
        expect(scope.needValidate).toBeFalsy();
    });

    it('should have login function', function () {
        expect(scope.login).not.toBeNull();
    });

    it('should contain a accountService', inject(function (accountService) {
        expect(accountService).not.toBeNull();
    }));

});