var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080',
    PERSON_SERVICE_API : 'http://localhost:8080/api/people/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: 'partials/list',
                controller:'PersonController',
                controllerAs:'ctrl',
                resolve: {
                    persons: function ($q, PersonService) {
                        console.log('Load all persons');
                        var deferred = $q.defer();
                        PersonService.loadAllPersons().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/home');
    }]);

