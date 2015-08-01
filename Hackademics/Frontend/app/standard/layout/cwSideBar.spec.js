describe('Casewhere worker sidebar', function () {
    var element, $compile,
        scope;

    // Load the app.layout module, which contains the directive
    beforeEach(module('app.layout'));
    beforeEach(module('layout/cwSideBar.html'));

    //Ignore GET ... use html2js
    var $httpBackend;
    beforeEach(inject(function ($injector) {
        $httpBackend = $injector.get('$httpBackend');
        $httpBackend.whenGET('/app/standard/layout/cwSideBar.html').respond(200, '');
    }));

    // Store references to $rootScope and $compile
    // so they are available to all tests in this describe block
    beforeEach(inject(function (_$compile_, _$rootScope_) {
        // The injector unwraps the underscores (_) from around the parameter names when matching
        $compile = _$compile_;
        scope = _$rootScope_;
    }));

    it('the directives controller should be injected correctly', function () {

        scope.items = [{
            id: 'testid',
            name: "testname"
        }];

        // Create an instance of the directive
        element = angular.element('<cw-side-bar items="{{items}}"></cw-side-bar>');
        $compile(element)(scope); // Compile the directive
        scope.$digest(); // Update the HTML
        var scp = element.isolateScope() || element.scope();
        expect(scp.items).toBeDefined();
        expect(scp.items.length).toEqual(1);

        // Check that the compiled element contains the templated content
        //expect(element.html()).toContain("testname");
    });
});