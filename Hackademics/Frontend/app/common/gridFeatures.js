
(function () {
    'use strict';
    /**
     *  @ngdoc overview
     *  @name cw.grid.autoResize
     *
     *  @description 
     *
     *  #cw.grid.autoResize
     *  This module provides auto-resizing functionality to ui-grid
     *
     */
    var module = angular.module('ui.grid.autoResize', ['ui.grid']);


    module.directive('cwGridAutoResize', ['$timeout', 'gridUtil', function ($timeout, gridUtil) {
        return {
            require: 'uiGrid',
            scope: false,
            link: function ($scope, $elm, $attrs, uiGridCtrl) {
                var prevGridWidth, prevGridHeight;

                function getDimensions() {
                    prevGridHeight = gridUtil.elementHeight($elm);
                    prevGridWidth = gridUtil.elementWidth($elm);
                }

                // Initialize the dimensions
                getDimensions();

                var resizeTimeoutId;
                function startTimeout() {
                    clearTimeout(resizeTimeoutId);

                    resizeTimeoutId = setTimeout(function () {
                        var newGridHeight = gridUtil.elementHeight($elm);
                        var newGridWidth = gridUtil.elementWidth($elm);
                        var tabContents = document.getElementsByClassName('tab-content');
                        if (tabContents && tabContents.length > 0) {
                            var gridWrappers = document.getElementsByClassName('grid-in-tab');
                            for (var i = 0; i < gridWrappers.length; i++) {
                                var gridWrapper = gridWrappers[i];
                                var offsetTop = gridWrapper.offsetTop;
                                if (offsetTop > 0) {
                                    newGridHeight = $(window).height() - offsetTop - 67;
                                }
                                if (newGridHeight !== prevGridHeight) {
                                    angular.element(gridWrapper).css('height', newGridHeight + 'px');
                                    $('.ui-grid-viewport').height(newGridHeight - 67); //67 is paging heigh
                                }
                            }
                        }
                        else {
                            $('.ui-grid-viewport').height(newGridHeight - 67);
                        }
                        if (newGridHeight !== prevGridHeight || newGridWidth !== prevGridWidth) {
                            uiGridCtrl.grid.gridHeight = newGridHeight;
                            uiGridCtrl.grid.gridWidth = newGridWidth;

                            $scope.$apply(function () {

                                uiGridCtrl.grid.refresh()
                                  .then(function () {
                                      getDimensions();
                                      startTimeout();
                                  });
                            });
                        }
                        else {
                            startTimeout();
                        }
                    }, 250);
                }

                startTimeout();

                $scope.$on('$destroy', function () {
                    clearTimeout(resizeTimeoutId);
                });
            }
        };
    }]);
})();