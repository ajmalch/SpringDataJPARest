'use strict';

angular.module('crudApp').factory('PersonService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllPersons: loadAllPersons,
                getAllPersons: getAllPersons,
                getPerson: getPerson,
                createPerson: createPerson,
                updatePerson: updatePerson,
                removePerson: removePerson
            };

            return factory;

            function loadAllPersons() {
                console.log('Fetching all Persons');
                var deferred = $q.defer();
                $http.get(urls.PERSON_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all Persons');
                            $localStorage.Persons = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading Persons');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllPersons(){
                return $localStorage.Persons;
            }

            function getPerson(link) {
                console.log('Fetching Person :'+ link);
                var deferred = $q.defer();
                $http.get(link)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Person :'+ link);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Person :'+ link);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createPerson(Person) {
                console.log('Creating Person');
                var deferred = $q.defer();
                $http.post(urls.PERSON_SERVICE_API, Person)
                    .then(
                        function (response) {
                            loadAllPersons();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Person : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updatePerson(Person, link) {
                console.log('Updating Person with link '+link);
                var deferred = $q.defer();
                $http.put(link, Person)
                    .then(
                        function (response) {
                            loadAllPersons();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Person with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removePerson(link) {
                console.log('Removing Person with link '+link);
                var deferred = $q.defer();
                $http.delete(link)
                    .then(
                        function (response) {
                            loadAllPersons();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Person :'+link);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);