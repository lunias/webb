'use strict';

angular.module('cardsOauthApp').controller('EditUserModalController', 
		function($timeout, $scope, $modal, $modalInstance, user, field, availableRoles, availableClients, userClients) {		
	
	$scope.user = {};
	angular.extend($scope.user, user);
	
	$scope.field = field;	
	$scope.availableRoles = availableRoles;
    $scope.availableClients = availableClients;	
    $scope.userClients = userClients;

    $scope.addClient = function() {
    	
      console.log($scope.userClients);            
    };

    $scope.settings = {
      bootstrap2: false,
      filterClear: 'Clear filter',
      filterPlaceHolder: 'Filter',
      moveSelectedLabel: 'Add selected only',
      moveAllLabel: 'Add all',
      removeSelectedLabel: 'Remove selected only',
      removeAllLabel: 'Remove all',
      moveOnSelect: false,
      preserveSelection: 'moved',
      selectedListLabel: 'Clients',
      nonSelectedListLabel: 'Available clients',
      postfix: '_helperz',
      selectMinHeight: 130,
      filter: true,
      infoAll: 'Showing all {0}',
      infoFiltered: '<span class="label label-warning">Filtered</span> {0} of {1}',
      infoEmpty: 'Empty',
      filterValues: false
    };    	       
    
    var addDoubleClickHandlers = function() {    	    
    	
        angular.forEach(angular.element('.bootstrap-duallistbox-container select option'), function(value, key) {
        	
      	  var option = angular.element(value);      	  
      	  option.dblclick(function (event) {
      		  console.log("popup client details modal for: " + event.target.label);
      	  });
      	  
        });    	
    };
    
    $scope.$watch('userClients', function() {   
    	
    	$timeout(function() {
        	addDoubleClickHandlers();
    	}, 100);
    });    
	
	$scope.save = function () {
		
		$modalInstance.close($scope.user);
	};

	$scope.delete = function () {		
		
		var confirmModalInstance = $modal.open({			
			templateUrl: 'scripts/app/user/deleteUserConfirmModal.html',
			controller: 'DeleteUserConfirmModalController',
			size: 'sm',
			resolve: {
				user: function() {
					return $scope.user;
				}
			}
		});
		
		confirmModalInstance.result.then(function() {
			
			// close modal and pass back user okay'ed for deletion
			$modalInstance.close(
					{
						user: $scope.user, 
						remove: true
					}
			);
		});
				
	};	
	
	$scope.cancel = function () {
		
		$modalInstance.dismiss('cancel');
	};		
	
});