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

            function getPerson(id) {
                console.log('Fetching Person with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.PERSON_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Person with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Person with id :'+id);
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

            function updatePerson(Person, id) {
                console.log('Updating Person with id '+id);
                var deferred = $q.defer();
                $http.put(urls.PERSON_SERVICE_API + id, Person)
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

            function removePerson(id) {
                console.log('Removing Person with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.PERSON_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllPersons();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Person with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);