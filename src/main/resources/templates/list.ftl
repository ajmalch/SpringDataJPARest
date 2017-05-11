<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Person</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
					<div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.edit" />
	                <div class="row">
						<div class="form-group col-md-12" ng-class="{ 'has-error' : myForm.clientId.$invalid && !myForm.clientId.$pristine }">
							<label class="col-md-2 control-lable" for="clientid">Client ID</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.person.clientId" name="clientId" id="clientId" class="form-control input-sm" placeholder="Enter Client ID" required ng-minlength="3" ng-maxlength="10"/>
                                <p ng-show="myForm.clientId.$invalid && !myForm.clientId.$pristine" class="help-block">Client ID is required</p>
	                        </div>
						</div>
	                </div>

	                <div class="row">
                        <div class="form-group col-md-12" ng-class="{ 'has-error' : myForm.firstname.$invalid && !myForm.firstname.$pristine }">
                            <label class="col-md-2 control-lable" for="firstname">First Name</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.person.firstname" name="firstname" id="firstname" class="form-control input-sm" placeholder="Enter your First Name" required ng-minlength="3"/>
                                <p ng-show="myForm.firstname.$invalid && !myForm.firstname.$pristine" class="help-block">Firstname is required</p>
                            </div>
                        </div>
	                    <div class="form-group col-md-12" ng-class="{ 'has-error' : myForm.lastname.$invalid && !myForm.lastname.$pristine }">
	                        <label class="col-md-2 control-lable" for="lastname">Last Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.person.lastname" name="lastname" id="lastname" class="form-control input-sm" placeholder="Enter your Last Name" required ng-minlength="3"/>
                                <p ng-show="myForm.lastname.$invalid && !myForm.lastname.$pristine" class="help-block">Lastname is required</p>
                            </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12" ng-class="{ 'has-error' : myForm.sex.$invalid && !myForm.sex.$pristine }">
	                        <label class="col-md-2 control-lable" for="Sex">Sex</label>
	                        <div class="col-md-7">
								<input type="radio" name="sex" ng-model="ctrl.person.sex" value="MALE" required> Male
                                <input type="radio" name="sex" ng-model="ctrl.person.sex" value="FEMALE" required> Female
                                <p ng-show="myForm.sex.$invalid" class="help-block">Sex is required</p>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.edit ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine && myForm.$invalid ">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Persons </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
						<th>CLIENT ID</th>
		                <th>FIRST NAME</th>
		                <th>LAST NAME</th>
		                <th>Sex</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllPersons()._embedded.persons">
		                <td>{{u.clientId}}</td>
		                <td>{{u.firstname}}</td>
		                <td>{{u.lastname}}</td>
		                <td>{{u.sex}}</td>
		                <td><button type="button" ng-click="ctrl.editPerson(u.lastname)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removePerson(u.lastname)" class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>