// Code goes here
'use strict';

App.controller('ModalDemoCtrl', function ($uibModal, $log) {
  var pc = this;
  pc.data = "Rager Name Test"; 

  pc.open = function (size) {
    var modalInstance = $uibModal.open({
      animation: true,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'html/modal.html',
      controller: 'ModalInstanceCtrl',
      controllerAs: 'pc',
      size: size,
      resolve: {
        data: function () {
          return pc.data;
        }
      }
    });

    modalInstance.result.then(function () {
      alert("now I'll close the modal");
    });
  };
});

App.controller('ModalInstanceCtrl', function ($uibModalInstance, data) {
  var pc = this;
  pc.data = data;
  
  pc.ok = function () {
    //{...}
    alert("You clicked the ok button."); 
    $uibModalInstance.close();
  };

  pc.cancel = function () {
    //{...}
    alert("You clicked the cancel button."); 
    $uibModalInstance.dismiss('cancel');
  };
});
