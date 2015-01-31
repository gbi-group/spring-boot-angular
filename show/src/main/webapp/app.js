var springFun = angular.module('springFun', ['ngRoute', 'springFunControllers']);

springFun.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when("/list", {
            templateUrl: 'views/message/list.html',
            controller: 'MessagesController'
        }).
        when('/index', {
            templateUrl: 'views/hello/hello.html'
        }).
        when('/about', {
            templateUrl: 'views/hello/about.html'
        }).
        otherwise({
            redirectTo: '/index'
        });
}]);