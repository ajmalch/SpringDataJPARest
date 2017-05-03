'use strict';

angular.module('crudApp').controller('PersonController',
    ['PersonService', '$scope',  function( PersonService, $scope) {

        var self = this;
        self.person = {};
        self.persons=[];

        self.submit = submit;
        self.getAllPersons = getAllPersons;
        self.createPerson = createPerson;
        self.updatePerson = updatePerson;
        self.removePerson = removePerson;
        self.editPerson = editPerson;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.edit = false;
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.person.id === undefined || self.person.id === null) {
                console.log('Saving New Person', self.person);
                createPerson(self.person);
            } else {
                updatePerson(self.person, self.person.id);
                console.log('Person updated with id ', self.person.id);
            }
        }

        function createPerson(Person) {
            console.log('About to create Person');
            PersonService.createPerson(Person)
                .then(
                    function (response) {
                        console.log('Person created successfully');
                        self.successMessage = 'Person created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.person={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Person');
                        self.errorMessage = 'Error while creating Person: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updatePerson(Person, id){
            console.log('About to update Person');
            PersonService.updatePerson(Person, id)
                .then(
                    function (response){
                        console.log('Person updated successfully');
                        self.successMessage='Person updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Person');
                        self.errorMessage='Error while updating Person '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removePerson(id){
            console.log('About to remove Person with id '+id);
            PersonService.removePerson(id)
                .then(
                    function(){
                        console.log('Person '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing Person '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllPersons(){
            return PersonService.getAllPersons();
        }

        function editPerson(id) {
            self.successMessage='';
            self.errorMessage='';
            PersonService.getPerson(id).then(
                function (Person) {
                    self.person = Person;
                    self.edit = true;
                },
                function (errResponse) {
                    console.error('Error while removing Person ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.edit = false;
            self.person={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);